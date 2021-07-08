package com.sf.bigscreen.utils;

import java.io.FileInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.crypto.CipherInputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Date;
import javax.crypto.Mac;
import java.util.Base64;
import java.util.Objects;
import java.net.URLEncoder;

/**
 * @Author: bi xuan
 * @Date: 2021/7/6 10:51
 * @Description:该方法拿到签名的函数
 **/
public class XinZhiWeatherUtil {
    String publicKey = "XXX";
    String secretKey = "XXX";
    public String getSignature() throws Exception{
        String query = "fields=precip_minutely&locations=29.5617:120.0962&public_key=".concat(publicKey).concat("&ts=").concat(Long.toString(new Date().getTime() / 1000));
        Mac hmac = Mac.getInstance("HmacSHA1");
        hmac.init(new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA1"));
        String sig = Base64.getEncoder().encodeToString(hmac.doFinal(query.getBytes("UTF-8")));
        return sig;
    }
}
