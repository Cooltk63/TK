Vulnerability :: Vulnerability Description in Detail	Likely Impact	
Recommendation :: Weak Cryptographic Hash: Missing Required Step	On line 191 of DecryptAscii.java, the code invokes the method digest() prior to invoking a required step after the call to getInstance() on line 181.	On line 191 of DecryptAscii.java, the code invokes the method digest() prior to invoking a required step after the call to getInstance() on line 181.	Implement all steps required in the generation of a cryptographic hash. Where possible, explicitly specify the parameters used to ensure that the strength of hash is not compromised.

Source Code ::

  public static byte[] decryptKeyFile(PrivateKey key, byte[] ciphertext)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(ciphertext);
    }

    Tell me how to resolved this issue
