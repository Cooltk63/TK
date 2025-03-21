InputStream keyStream = getClass().getClassLoader().getResourceAsStream("SSO_HRMS_KEYS/PrivateKey.der");
if (keyStream == null) {
    throw new FileNotFoundException("Private key file not found");
}

byte[] keyBytes = StreamToByteArray.convertStreamToByteArray(keyStream);
System.out.println("Key Bytes: " + keyBytes.length);