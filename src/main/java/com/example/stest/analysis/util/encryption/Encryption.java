package com.example.stest.analysis.util.encryption;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Map;

/*
*  加密工具类
*
* */
@Slf4j
public class Encryption {

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";    //"算法/模式/补码方式"
    /*
    * 生成token
    * map存放实体
    * uid 用户主键
    * password token解析密码
    * */
    public static String createtoken(Map map,String uid,long time,String password){
        String jwt =  Jwts.builder()
                .setClaims(map)
                .setSubject(uid) //用户名
                .setExpiration(new Date(System.currentTimeMillis() + time)) //token保留的时间
                .signWith(SignatureAlgorithm.HS512, password) //
                .compact();
        return jwt;
    }


     /**
      * MD5 + 盐，防止数据库被盗，第三方使用碰撞法解密用户账号（彩虹碰撞）
      * @param data
      * @param passwordSalt
      * @return
      */
     public static String encrytMD5(String data, String passwordSalt){
         if (data == null || data.trim() == null){
             return "";
         }
         if (passwordSalt == null){
             passwordSalt = "";
         }
         passwordSalt = passwordSalt.trim();
         data = passwordSalt + data.trim();
         try{
             //指定加密算法
             MessageDigest digest=MessageDigest.getInstance("MD5");
             digest.update(data.getBytes());
             return encryptMD5toString(digest.digest());
         }catch (Exception e){
             e.printStackTrace();
             return null;
         }
     }
     //将加密后的字节数组转化为固定长度的字符串
     private static String encryptMD5toString(byte[] data){
         if (data == null || data.length == 0){
             return "";
         }
         try{
             String str="";
             String str16;
             for(int i=0;i<data.length;i++){
                 //转换为16进制数据
                 //Integer.toHexString的参数是int，如果不进行&0xff，那么当一个byte会转换成int时，由于int是32位，而byte只有8位这时会进行补位，
                 //例如补码11111111的十进制数为-1转换为int时变为11111111111111111111111111111111好多1啊，呵呵！即0xffffffff但是这个数是不对的，这种补位就会造成误差。
                 //和0xff相与后，高24比特就会被清0了，结果就对了。
                 str16=Integer.toHexString(0xFF & data[i]);
                 if(str16.length()==1){
                     str=str+"0"+str16;
                 }else{
                     str=str+str16;
                 }
             }
             return str;
         }catch (Exception e){
             e.printStackTrace();
             return null;
         }
     }

    public static String enAesCode(String content, String key) {
        if (key == null || "".equals(key)) {
            log.info("key为空！");
            return null;
        }
        if (key.length() != 16) {
            log.info("key长度不是16位！");
            return null;
        }
        try {
            byte[] raw = key.getBytes();  //获得密码的字节数组
            SecretKeySpec skey = new SecretKeySpec(raw, "AES"); //根据密码生成AES密钥
            Cipher cipher = Cipher.getInstance(ALGORITHM);  //根据指定算法ALGORITHM自成密码器
            cipher.init(Cipher.ENCRYPT_MODE, skey); //初始化密码器，第一个参数为加密(ENCRYPT_MODE)或者解密(DECRYPT_MODE)操作，第二个参数为生成的AES密钥
            byte [] byte_content = content.getBytes("utf-8"); //获取加密内容的字节数组(设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte [] encode_content = cipher.doFinal(byte_content); //密码器加密数据
            return Base64.encodeBase64String(encode_content); //将加密后的数据转换为字符串返回
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*****************************************************
     * AES解密
     * @param content 加密密文
     * @param key 加密密码,由字母或数字组成
    此方法使用AES-128-ECB加密模式，key需要为16位
    加密解密key必须相同
     * @return 解密明文
     ****************************************************/

    public static String deAesCode(String content, String key) {
        if (key == null || "".equals(key)) {
            log.info("key为空！");
            return null;
        }
        if (key.length() != 16) {
            log.info("key长度不是16位！");
            return null;
        }
        try {
            byte[] raw = key.getBytes();  //获得密码的字节数组
            SecretKeySpec skey = new SecretKeySpec(raw, "AES"); //根据密码生成AES密钥
            Cipher cipher = Cipher.getInstance(ALGORITHM);  //根据指定算法ALGORITHM自成密码器
            cipher.init(Cipher.DECRYPT_MODE, skey); //初始化密码器，第一个参数为加密(ENCRYPT_MODE)或者解密(DECRYPT_MODE)操作，第二个参数为生成的AES密钥
            byte [] encode_content = Base64.decodeBase64(content); //把密文字符串转回密文字节数组
            byte [] byte_content = cipher.doFinal(encode_content); //密码器解密数据
            return new String(byte_content,"utf-8"); //将解密后的数据转换为字符串返回
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
