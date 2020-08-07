package com.innovate.modules.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.Test;

/**
 * @ProjectName innovate-admin
 * @Author 麦奇
 * @Email biaogejiushibiao@outlook.com
 * @Date 3/15/20 5:00 PM
 * @Version 1.0
 * @Description:
 **/

public class JasyptUtils {

    /**
     * 加密
     * @throws Exception
     */
    @Test
    public void EncryptCode() throws Exception {

        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        config.setAlgorithm("PBEWithMD5AndDES");          // 加密的算法，这个算法是默认的
        config.setPassword("panda");                        // 加密的密钥
        standardPBEStringEncryptor.setConfig(config);
//        String plainText = "your need encrypt string";
//        String plainText = "Panda15677408298";
        String plainText = "root";
        String encryptedText = standardPBEStringEncryptor.encrypt(plainText);
        System.out.println(encryptedText);
    }

    /**
     * 解密
     * @throws Exception
     */
    @Test
    public void DeCode() throws Exception {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword("panda");
        standardPBEStringEncryptor.setConfig(config);
        String encryptedText = "Nve3rwvAuhO04Wmvht932gtGPdfhNhr5";
        String plainText = standardPBEStringEncryptor.decrypt(encryptedText);
        System.out.println(plainText);
    }

}
