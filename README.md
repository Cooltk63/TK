  private static String AES_ALGO = "AES";
    private static String AES_CIPHER = "AES/CBC/PKCS5Padding";
    

    public static String decryptPayload(String encryptedText, String secretKey) throws Exception {
        try {
            
            byte[] encryptedTextByte = Base64.getDecoder().decode(encryptedText);
            byte[] keybyte = secretKey.getBytes(StandardCharsets.UTF_8);
            byte[] ivKey = Arrays.copyOf(keybyte, 16);
            IvParameterSpec iv = new IvParameterSpec(ivKey);
            SecretKey secretKeyObject = new SecretKeySpec(keybyte, AES_ALGO);
            Cipher cipher = Cipher.getInstance(AES_CIPHER);
            cipher.init(2, secretKeyObject, iv);
            byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
            String decryptedText = new String(decryptedByte);
            return decryptedText;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException var10) {
            throw new Exception();
        }
    }
