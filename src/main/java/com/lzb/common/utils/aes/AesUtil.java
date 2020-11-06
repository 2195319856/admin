package com.lzb.common.utils.aes;

import org.springframework.util.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AesUtil {
    //密匙16位
    private final static String AES="abcdabcdabcdabcd";
    /**
     * AES解密
     * @param encryptStr 密文
     *
     * @return 明文
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr) {
        if (StringUtils.isEmpty(encryptStr) || StringUtils.isEmpty(AES)) {
            return null;
        }

        byte[] encryptByte = Base64.getDecoder().decode(encryptStr);
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(AES.getBytes(), "AES"));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] decryptBytes = new byte[0];
        try {
            decryptBytes = cipher.doFinal(encryptByte);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(decryptBytes);
    }

    /**
     * AES加密
     * @param content 明文
     *
     * @return 密文
     * @throws Exception
     */
    public static String aesEncrypt(String content) throws Exception {
        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(AES)) {
            return null;
        }

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(AES.getBytes(), "AES"));

        byte[] encryptStr = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptStr);
    }
}
