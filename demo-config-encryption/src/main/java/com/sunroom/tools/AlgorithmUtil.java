package com.sunroom.tools;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class AlgorithmUtil {
    private final String salt = "ENN";
    private static StandardPBEStringEncryptor encryptor;

    AlgorithmUtil() {
        if (encryptor == null) {
            encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword(salt);
        }
    }
    /**
     * 加密数据
     */
    public String encryptData(String orgValue) {
        return encryptor.encrypt(orgValue);
    }

    /**
     * 解密数据
     */
    public String decryptData(String encData) {
        return encryptor.decrypt(encData);
    }
}
