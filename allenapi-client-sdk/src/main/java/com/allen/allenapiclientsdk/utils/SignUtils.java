package com.allen.allenapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * Created with IntelliJ IDEA.
 * Description:签名工具
 * User: Allen
 * Date: 2023-01-04
 * Time: 22:05
 */
public class SignUtils {
    /**
     * 生成签名
     * @param body
     * @param secretKey
     * @return
     */
    public static String getSign(String body, String secretKey){
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body + "." +secretKey;
        return md5.digestHex(content);
    }
}
