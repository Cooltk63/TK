This is encryption code here

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



xxxxxxxxxxxxxx

This is decryption code here

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
