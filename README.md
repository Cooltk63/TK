 public static String generatingDigitalSignature(String payloadData) {
        try {
            log.info("remobe bc Begin: ");
            java.security.Security.removeProvider(
                    String.valueOf(new BouncyCastleProvider())
            );
            for (Provider provider : Security.getProviders()) {
//                System.out.println("===========================================================" + provider.getName());
//                provider.getServices().forEach(service -> System.out.println("  " + service.getAlgorithm()));
            }


            log.info("generatingDigitalSignature Begin: "); 
            // Exception Message : java.security.InvalidKeyException: IOException : algid parse error, not a sequence
            java.security.Security.addProvider(
                    new org.bouncycastle.jce.provider.BouncyCastleProvider()
            );
            log.info("generatingDigitalSignature End: ");


            for (Provider provider : Security.getProviders()) {
//                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + provider.getName());
//                provider.getServices().forEach(service -> System.out.println("  " + service.getAlgorithm()));
            }


            //String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
            //log.info("rootPath : " + rootPath);

            log.info("Before PRIVATE KEY");
            // SHA256-RSA algorithm has to be used to sign the plain request payload with the help of sender’s Private Key.
            String tcsPrivateKeyPath = SSOCommonConstants.TCS_PRIVATE_KEY;   // "/media/CRS/SSO_HRMS_KEYS/PrivateKey.der";
            //String tcsPrivateKeyPath = rootPath + "SSO_HRMS_KEYS/PrivateKey.der";
            log.info("tcsPrivateKeyPath : " + tcsPrivateKeyPath); 

            //String m3 = Thread.currentThread().getContextClassLoader().getResource("SSO_HRMS_KEYS/PrivateKey.der").getPath();
            //log.info("m3 : " + m3);
           // File file = new ClassPathResource("SSO_HRMS_KEYS/PrivateKey.der").getFile();
            //log.info("m2 : " + file.getAbsolutePath());
            

            PrivateKey tcsPrivateKey = ClientUtility.getPrivateKey(tcsPrivateKeyPath);

            return ClientUtility.signSenderTokenHash(tcsPrivateKey, payloadData);
        } catch (InvalidKeySpecException e) {
            log.info("GeneratingDigitalSignature Exception Message : " + e.getMessage());
            log.info("GeneratingDigitalSignature Exception Cause : " + e.getCause());
        } catch (Exception ex) {
            log.info("Exception >>>>> " + ex.toString());
        }
        return null;
    }
    
