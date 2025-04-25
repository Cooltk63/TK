import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Main {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/GCM/PKCS5Padding";
    private static final int GCM_TAG_LENGTH = 16; // 128 bits
    private static final int ITERATIONS = 1000;
    private static final int KEY_LENGTH = 256;

    public static void main(String[] args) throws Exception {
        String password = "juVI+XqX90tQSqYPAmtVxg=="; // Use your password here
        String iv = "muWlNQ9H3DjYTsVI";
        String salt = "4Q0V9q2/XWsN4JjWFPGstw==";
        String encryptedData = "JW6eYVUn81ZowUmviQTYmzLy8GwIqugYkf1SM8cXELfarg==";

        byte[] ivBytes = Base64.getDecoder().decode(iv);
        byte[] saltBytes = Base64.getDecoder().decode(salt);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

        SecretKey secretKey = generateSecretKey(password, saltBytes);
        byte[] decryptedData = decrypt(encryptedBytes, secretKey, ivBytes);

        System.out.println("Decrypted Data: " + new String(decryptedData));
    }

    private static SecretKey generateSecretKey(String password, byte[] salt) throws Exception {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] key = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(key, ALGORITHM);
    }


    private static byte[] decrypt(byte[] encryptedData, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        return cipher.doFinal(encryptedData);
    }
}





===================================================================


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
