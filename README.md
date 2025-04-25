import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AESGCMFrontendCompatible {

    // Constants
    private static final String ALGORITHM = "AES";
    private static final String CIPHER_TRANSFORMATION = "AES/GCM/PKCS5Padding";
    private static final int GCM_TAG_LENGTH = 16; // in bytes (128 bits)
    private static final int ITERATIONS = 1000;
    private static final int KEY_LENGTH = 256;

    // Hardcoded values (same as frontend)
    private static final String BASE64_IV = "HHpJGrIv+FIx7uGu";
    private static final String BASE64_SALT = "d6PI1Fz7kVbn7Xw+cz1NwQ==";
    private static final String BASE64_PASSWORD = "juVI+XqX90tQSqYPAmtVxg==";

    public static void main(String[] args) throws Exception {
        // Input plain text
        String plainText = "This text will be encrypted and decrypted!";

        System.out.println("========== ENCRYPTION ==========");
        String encryptedBase64 = encrypt(plainText, BASE64_PASSWORD);
        System.out.println("Plain Text: " + plainText);
        System.out.println("Encrypted (Base64): " + encryptedBase64);
        System.out.println("IV (Base64): " + BASE64_IV);
        System.out.println("Salt (Base64): " + BASE64_SALT);

        System.out.println("\n========== DECRYPTION ==========");
        String decryptedText = decrypt("s5jrQ+AJgnJPT6eYChOmUYNokSD8NY/1wzJXTy+D28DReA==", BASE64_PASSWORD);
        System.out.println("Decrypted: " + decryptedText);
    }

    public static String encrypt(String plainText, String base64Password) throws Exception {
        byte[] iv = Base64.getDecoder().decode(BASE64_IV);
        byte[] salt = Base64.getDecoder().decode(BASE64_SALT);
        byte[] passwordBytes =  base64Password.getBytes(StandardCharsets.UTF_8);//Base64.getDecoder().decode(base64Password);

        SecretKeySpec key = deriveKey(passwordBytes, salt);

        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmSpec);

        byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String base64CipherText, String base64Password) throws Exception {
        byte[] iv = Base64.getDecoder().decode(BASE64_IV);
        byte[] salt = Base64.getDecoder().decode(BASE64_SALT);
        byte[] encryptedBytes = Base64.getDecoder().decode(base64CipherText);
        byte[] passwordBytes = base64Password.getBytes(StandardCharsets.UTF_8);//Base64.getDecoder().decode(base64Password);

        SecretKeySpec key = deriveKey(passwordBytes, salt);

        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, gcmSpec);

        byte[] decrypted = cipher.doFinal(encryptedBytes);
        return new String(decrypted, "UTF-8");
    }

    private static SecretKeySpec deriveKey(byte[] passwordBytes, byte[] salt) throws Exception {
        KeySpec spec = new PBEKeySpec(
                toCharArray(passwordBytes),
                salt,
                ITERATIONS,
                KEY_LENGTH
        );
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    // Helper: Convert byte[] to char[] for PBEKeySpec
    private static char[] toCharArray(byte[] bytes) {
        char[] chars = new char[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            chars[i] = (char) (bytes[i] & 0xff);
        }
        return chars;
    }
}


XXXXXXXXXX FORNT END 

const passphrase = "juVI+XqX90tQSqYPAmtVxg==";

async function decryptData(iv, salt, passphrase, encryptedData) {
    const encoder = new TextEncoder();
    const passphraseBuffer = encoder.encode(passphrase);

    const encryptedArray = base64ToArrayBuffer(encryptedData);
    const keyMaterial = await crypto.subtle.importKey(
        'raw',
        passphraseBuffer,
        {name: 'PBKDF2'},
        false,
        ['deriveKey']
    );

    salt = base64ToArrayBuffer(salt);
    iv = base64ToArrayBuffer(iv);

    try {
        const derivedKey = await crypto.subtle.deriveKey(
            {
                name: "PBKDF2",
                salt: salt,  //crypto.getRandomValues(new Uint8Array(16)),
                iterations: 1000,
                hash: "SHA-256"
            },
            keyMaterial,
            {
                name: "AES-GCM",
                length: 256
            },
            true,
            ["encrypt", "decrypt"]
        );

        //console.log('444444');


        const decryptedData = await crypto.subtle.decrypt({name: 'AES-GCM', iv}, derivedKey, encryptedArray);
        //console.log('5555555');
        const dec = new TextDecoder();

        //return { iv, salt, dec.decode(decryptedData)};
        return dec.decode(decryptedData)
    } catch (e) {
        console.error(e)
    }
}

function decrypt(iv, salt, data) {
    return decryptData(iv, salt, passphrase, data)
        .then(value => {
            try {
                return value;
            } catch (e) {
                console.error("decrypt  " + e);
            }
        })
        .catch(() => {
        });
}


function base64ToArrayBuffer(base64Text) {
    const binaryString = atob(base64Text);
    const len = binaryString.length;
    const bytes = new Uint8Array(len);
    for (let i = 0; i < len; i++) {
        bytes[i] = binaryString.charCodeAt(i);
    }
    return bytes.buffer;
}

module.exports = decrypt


XXXXXXXXX ENCRYPT

const passphrase = "juVI+XqX90tQSqYPAmtVxg==";

async function encryptData(iv, salt, passphrase, data) {
  const encoder = new TextEncoder();
  const passphraseBuffer = encoder.encode(passphrase);
  const keyMaterial = await crypto.subtle.importKey(
    "raw",
    passphraseBuffer,
    { name: "PBKDF2" },
    false,
    ["deriveKey"]
  );

  const derivedKey = await crypto.subtle.deriveKey(
    {
      name: "PBKDF2",
      salt: salt, //crypto.getRandomValues(new Uint8Array(16)),
      iterations: 1000,
      hash: "SHA-256",
    },
    keyMaterial,
    {
      name: "AES-GCM",
      length: 256,
    },
    true,
    ["encrypt", "decrypt"]
  );
  const encodedData = new TextEncoder().encode(data);
  const encryptedData = await crypto.subtle.encrypt(
    { name: "AES-GCM", iv },
    derivedKey,
    encodedData
  );

  return { iv, salt, encryptedData };
}

function encrypt(iv, salt, data) {
    return encryptData(iv, salt, passphrase, data)
        .then(value => {
            return btoa(String.fromCharCode.apply(null, new Uint8Array(value.encryptedData)));
        })
        .catch((e) => {
            console.error(e);
        });
}

export {encrypt}



