package com.baiye.Utils;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Baiye on 2016/10/10.
 */
public class SpiederUtil {

    public static final String text = "{\"username\": \"\", \"rememberLogin\": \"true\", \"password\": \"\"}";

    public static final String BASE_URL = "http://music.163.com/";

    private String aesEncrypt(String value, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("UTF-8"), "AES"), new IvParameterSpec(
                "0102030405060708".getBytes("UTF-8")));
        return Base64.encodeBase64String(cipher.doFinal(value.getBytes()));
    }

    public static void main(String[] args) throws Exception {

        SpiederUtil ut = new SpiederUtil();
        ut.getCommentCount("5179544","http://music.163.com/song?id=5179544");

    }

    private Long getCommentCount(String id,String baesURL) throws Exception {
        String secKey = new BigInteger(100, new SecureRandom()).toString(32).substring(0, 16);
        String encText = aesEncrypt(aesEncrypt(text, "0CoJUm6Qyw8W8jud"), secKey);
        String encSecKey = rsaEncrypt(secKey);
        Connection.Response response = Jsoup
                .connect("http://music.163.com/weapi/v1/resource/comments/R_SO_4_" + id + "/?csrf_token=")
                .method(Connection.Method.POST).header("Referer", baesURL)
                .data(ImmutableMap.of("params", encText, "encSecKey", encSecKey)).execute();


        System.out.println(response.body());

        return 1L;
    }


    public void test() throws Exception {
        String secKey = new BigInteger(100, new SecureRandom()).toString(32).substring(0, 16);
        String encText = aesEncrypt(aesEncrypt(text, "0CoJUm6Qyw8W8jud"), secKey);
        String encSecKey = rsaEncrypt(secKey);
        String key = ImmutableMap.of("params", encText, "encSecKey", encSecKey).toString();
        System.out.println(encText);
        System.out.println(encSecKey);
        System.out.println(key);
    }

    private String rsaEncrypt(String value) throws UnsupportedEncodingException {
        value = new StringBuilder(value).reverse().toString();
        BigInteger valueInt = hexToBigInteger(stringToHex(value));
        BigInteger pubkey = hexToBigInteger("010001");
        BigInteger modulus = hexToBigInteger("00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7");
        return valueInt.modPow(pubkey, modulus).toString(16);
    }

    private BigInteger hexToBigInteger(String hex) {
        return new BigInteger(hex, 16);
    }

    private String stringToHex(String text) throws UnsupportedEncodingException {
        return DatatypeConverter.printHexBinary(text.getBytes("UTF-8"));
    }



}
