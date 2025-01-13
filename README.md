let params = {
            'salt': salt,
            'iv': iv,
            'data': aesUtil.encrypt(salt, iv, passphrase, JSON.stringify(payload)),
        }
