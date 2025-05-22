Vulnerability	Vulnerability Description in Detail
Insecure SSL: Server Identity Verification Disabled	Server identity verification is disabled when making SSL connections.


Likely Impact: Server identity verification is disabled when making SSL connections.
Recommendation :Do not forgo server verification checks when making SSL connections. Make sure to verify server identity before establishing a connection.


Code Impact :

public  Object consumeService(Object eisRequest, String urlType,String refNo) throws ConfigurationException {


        Object object=null;
        EisBglRequest eisBglRequest = null;
        EisCommonRequest eisCommonRequest= null;
        String encryptedrsa= "";
        EncryptedRequest encryptedRequest= null;
        if(urlType.equalsIgnoreCase("B")){
            eisBglRequest= (EisBglRequest) eisRequest;

        } else {
            eisCommonRequest= (EisCommonRequest) eisRequest;

        }

        URL url = null;
        try {


            String https_url;
            // Create a context that doesn't check certificates.
            SSLContext ssl_ctx = SSLContext.getInstance("TLSV1.2");
//            TrustManager[ ] trust_mgr = get_trust_mgr();
            TrustManager[ ] trust_mgr = null;
            ssl_ctx.init(null,                // key manager
                    trust_mgr,           // trust manager
                    new SecureRandom()); // random number generator
            HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());




            if (urlType.equalsIgnoreCase("D"))
                https_url= CommonConstant.eisProdURL+CommonConstant.eisDeposit;
            else if (urlType.equalsIgnoreCase("L"))
                https_url= CommonConstant.eisProdURL+CommonConstant.eisLoan;
            else if (urlType.equalsIgnoreCase("C"))
                https_url= CommonConstant.eisProdURL+CommonConstant.eisContingent;
            else
                https_url= CommonConstant.eisProdURL+CommonConstant.eisBGL;

            //log.info("EIS url: "+https_url);

            // url = new URL(null,https_url,new sun.net.www.protocol.https.Handler());
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();

            // Guard against "bad hostname" errors during handshake.
            con.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String host, SSLSession sess) {
                    return true;
                }
            });

            Configuration config = new PropertiesConfiguration("common.properties");
            String homeBasePath = (String) config.getProperty("REPORT_HOME_DIR");
            ObjectMapper mapper = new ObjectMapper();
            RSA rsa = new RSA(CommonConstant.rsaKeySize, CommonConstant.rsaAlgorithm);

            if(urlType.equalsIgnoreCase("B")){

                String plainRequestValue = mapper.writeValueAsString(eisBglRequest);
                //log.info("PLain request: " + plainRequestValue);
                encryptedRequest = new EncryptedRequest(eisBglRequest, CommonConstant.aesKeySize, CommonConstant.aesAlgorithm);
                encryptedRequest.setRequestReferenceNumber(refNo);
                encryptedrsa = rsa.encrypt(homeBasePath + CommonConstant.encryptionCertificatePath, encryptedRequest.getKey());
            }
            else {

                String plainRequestValue = mapper.writeValueAsString(eisCommonRequest);
                //log.info("PLain request: " + plainRequestValue);
                encryptedRequest = new EncryptedRequest(eisCommonRequest, CommonConstant.aesKeySize, CommonConstant.aesAlgorithm);
                encryptedRequest.setRequestReferenceNumber(refNo);
                encryptedrsa = rsa.encrypt(homeBasePath + CommonConstant.encryptionCertificatePath, encryptedRequest.getKey());
            }


            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/json");
            con.setRequestProperty("Accept","application/json");
            con.setRequestProperty("AccessToken",encryptedrsa);
            OutputStream os= con.getOutputStream();

            //dumpl all cert info


            String requestString = mapper.writeValueAsString(encryptedRequest);
            os.write(requestString.getBytes());
            //log.info("Sending request with given parameters : "+requestString);

           /* String k = response.readEntity(String.class);
            //log.info("Response : "+k);
            EncryptedResponse encryptedResponse = mapper.readValue(k, EncryptedResponse.class);*/



            String contentType= con.getContentType();

            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
            String readLine= br.readLine();
            ArrayList<String> lineList= new ArrayList<String>();





            EncryptedResponse encryptedResponse = mapper.readValue(readLine,EncryptedResponse.class);
            //log.info("war updated....");

            try {

                //log.info("Response from EIS: "+encryptedRequest.decrypt(encryptedResponse.getResponse()));
                //log.info("Response from EIS: "+encryptedRequest.decrypt(encryptedResponse.getResponse()));
                if (urlType.equalsIgnoreCase("D")){
                    DepositResponse depositResponse= mapper.readValue(encryptedRequest.dcrrypt(encryptedResponse.getResponse()),DepositResponse.class);
                    depositResponse.setResponseDate(encryptedResponse.getResponseDate());

                    object= depositResponse;
                } else if (urlType.equalsIgnoreCase("L")){
                    LoanResponse loanResponse= mapper.readValue(encryptedRequest.dcrrypt(encryptedResponse.getResponse()),LoanResponse.class);
                    loanResponse.setResponseDate(encryptedResponse.getResponseDate());
                    object= loanResponse;
                } else if (urlType.equalsIgnoreCase("C")){
                    ContingentResponse contingentResponse= mapper.readValue(encryptedRequest.dcrrypt(encryptedResponse.getResponse()),ContingentResponse.class);
                    contingentResponse.setResponseDate(encryptedResponse.getResponseDate());
                    object= contingentResponse;
                } else{
                    BGLResponse bglResponse= mapper.readValue(encryptedRequest.dcrrypt(encryptedResponse.getResponse()),BGLResponse.class);
                    bglResponse.setResponseDate(encryptedResponse.getResponseDate());
                    object= bglResponse;
                }
            }catch (Exception e){

              //log.info("Here in exception...");

            }



            //dump all the content


        } catch (MalformedURLException e) {
            //log.info("Here in exception...");
        } catch (IOException e) {
            log.error("Exception Occurred " +e.getMessage());
            //log.info("Here in exception...");
        }catch (NoSuchAlgorithmException e) {
            //log.info("Here in exception...");

        }catch (KeyManagementException e) {
            //log.info("Here in exception...");
        } catch (CertificateException e) {
            //log.info("Here in exception...");
        } catch (InvalidKeyException e) {
            //log.info("Here in exception...");
        } catch (IllegalBlockSizeException e) {
            //log.info("Here in exception...");
        } catch (BadPaddingException e) {
            //log.info("Here in exception...");
        } catch (NoSuchPaddingException e) {
            //log.info("Here in exception...");
        }

        return object;
    }
