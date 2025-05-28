Vulnerability
Weak Encryption: Inadequate RSA Padding

Vulnerability Description in Detail
The method decryptKeyFile() in DecryptAscii.java performs public key RSA encryption without OAEP padding, which makes the encryption weak.


Likely Impact
The method doEncrypt() in RSA.java performs public key RSA encryption without OAEP padding, which makes the encryption weak.

Recommendation
To use RSA securely, you must use OAEP (Optimal Asymmetric Encryption Padding), using SHA-2 hash, when performing encryption.

code impacted :
 public static byte[] decryptKeyFile(PrivateKey key, byte[] ciphertext)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(ciphertext);
    }

    help me to resove this issue.
