package com.shuixiaofei.lockutils;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import sun.misc.BASE64Decoder;

import com.shuixiaofei.utils.AppConstant;


/**
 * DES通用类
 *
 * @author gaozhenhai
 * @version 1.0.0_1
 * @since 2013.01.15
 */
public class DESUtil {
    /**
     * 生成密钥方法
     *
     * @param seed 密钥种子
     * @return 密钥 BASE64
     * @throws Exception
     */
    public static String generateKey(String seed) throws Exception {
        byte[] seedBase64DecodeBytes = BASE64.decode(seed);

        SecureRandom secureRandom = new SecureRandom(seedBase64DecodeBytes);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] bytes = secretKey.getEncoded();

        String keyBase64EncodeString = BASE64.encode(bytes);

        return FormatUtil.stringBlank(keyBase64EncodeString);
    }

    /**
     * 加密方法
     *
     * @param text    明文
     * @param key     密钥 BASE64
     * @param charset 字符集
     * @return 密文
     * @throws Exception
     */
    public static String encrypt(String text, String key, String charset) throws Exception {
        byte[] keyBase64DecodeBytes = BASE64.decode(key);//base64解码key
        DESKeySpec desKeySpec = new DESKeySpec(keyBase64DecodeBytes);//前8个字节做为密钥
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] textBytes = text.getBytes(charset);//明文UTF-8格式
        byte[] bytes = cipher.doFinal(textBytes);//DES加密

        String encryptBase64EncodeString = BASE64.encode(bytes);//base64编码

        return encryptBase64EncodeString;
    }

    /**
     *
     * @param text 明文
     * @param key 8字节秘钥
     * @param charset 字符集
     * @return
     * @throws Exception
     */
    public static String encrypt(String text, byte[] key, String charset) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(key);//前8个字节做为密钥
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] textBytes = text.getBytes(charset);//明文UTF-8格式
        byte[] bytes = cipher.doFinal(textBytes);//DES加密

        String encryptBase64EncodeString = BASE64.encode(bytes);//base64编码

        return encryptBase64EncodeString;
    }

    /**
     * 解密方法
     *
     * @param text    密文
     * @param key     密钥 BASE64
     * @param charset 字符集
     * @return 明文
     * @throws Exception
     */
    public static String decrypt(String text, String key, String charset) throws Exception {
        byte[] keyBase64DecodeBytes = BASE64.decode(key);

        DESKeySpec desKeySpec = new DESKeySpec(keyBase64DecodeBytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] textBytes = BASE64.decode(text);
        byte[] bytes = cipher.doFinal(textBytes);

        String decryptString = new String(bytes, charset);

        return FormatUtil.stringBlank(decryptString);
    }

    /**
     * @param text    密文
     * @param key     8字节秘钥
     * @param charset 字符集
     * @return
     * @throws Exception
     */
    public static String decrypt(String text, byte[] key, String charset) throws Exception {

        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] textBytes = BASE64.decode(text);
        byte[] bytes = cipher.doFinal(textBytes);

        String decryptString = new String(bytes, charset);

        return FormatUtil.stringBlank(decryptString);
    }
	public static byte[] decryptBASE64(String key) throws Exception {
			return (new BASE64Decoder()).decodeBuffer(key);
		}

    
    public static void main(String[] args) throws Exception{
    	String signKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANzgu4TBXRnCwhOjxa14TgC14FIrT87tUy96+oypA1pNDec7kEUR0kEIpVsLviJkZf3w7nDmkeMOZquytH7N7bSzfuKEg8tJ6coVGAylfkm9W7Snd/L5+rrrBWLBWDTiqPQw7/tuYUEV6OnwC5zpybbT4wQLHmcwF8kXerHG7+0dAgMBAAECgYEAl7rZlgyuw++CGrO6K7wk+V/3RPrzzBZDuq4kR1QjNXkRP/Mu3AOuB3bFAJJEfqOxXIyRf4UkWnGeHfyY+TLFQsFtvtYhnWsG3XKGZTpLLCpA4GOdzrwB1YRQ6ZROLRfpk+Nvi3a8wy/0x0HSXhV4m9BtGE5MdHbW39N0uGtm/hECQQD8qByzhkUcxMBTCO+8fPPacqRnwVp2u+KT2Y3c4jYdW1g0mWYPvfwBW/9+MpC5NHjxhZRCapCLtJ1XZohqT0fLAkEA38z3ye/J1yR9UdtM2oluJcyAvrvtdOPPI53AtdWKdhxvTtEBdz8/Rq5+F+cyIU4zTT2ckr73zxtqpqVv/FlxtwJAFCelAn03ji/z16vi0/pzyX1TuCMogEhErt6v/mrdPkJHKwsBpUfWjpU0MHEAMoYJD9tEFHhDx6FE2/AF+dUROQJBAI8e7ivG3CZo4HfTrd9n2ff0QzU2y1FgSlx1iwL979E+t/QId06CNP4Hov24TXe9IdO4ngO+eo2I8/hkfiw8KxsCQQCUMURT3mwRFyunPZuvb0K1zLKwzwHxuKiFsZ11w/rEFA1qbF+1QcnsD4bOm6I3bRzAI2rNmowRswsxj25BAU+5";
    	Map<String,String> map = new HashMap<String, String>();
    	map.put("name", "你好啊");
    	String signStr = SignUtil.sign(map,signKey); // .getPayRSAPrivateKey());
		String  sd = "0011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";

    	String desKey = AppConstant.desKey;
    	String as = DESUtil.encrypt(
				(sd), desKey, "UTF-8");
    	
    	String pd = "7777777";
    	System.out.println(DESUtil.encrypt(
				pd, desKey, "UTF-8"));
    	
    	System.out.println(as);
    	System.out.println("122");
    	
    	byte[] rsaKey = decryptBASE64(desKey);
        String decryptArr = DESUtil.decrypt(as, rsaKey, "utf-8");
        System.out.println("11111111111");
    	System.out.println(decryptArr);
//		String test = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxDSElOQUJBTks+DQogICAgPFZFUlNJT04+MS4yLjA8L1ZFUlNJT04+DQogICAgPE1FUkNIQU5UPjIyMzEyNzgxPC9NRVJDSEFOVD4NCiAgICA8VEVSTUlOQUw+MDAwMDAwMDE8L1RFUk1JTkFMPg0KICAgIDxEQVRBPkJiQ2NzeVUxL3kyMjlIeXZ4RFQ1Rm1SVnVjSFRXUFJqaGhyWmViRW1wTVAvL2xjdW04cjBRdVhGcjZPeDY5NXdNdEYwblMwWHhjYVYKOVNoMWdrYU16cXBhWllKSGdUQ3dYREVnMUFXNVNUZFkybEh4UXRXOWJaMElqdWpDczJWRk4xaml6Z044SDJYb3Z4bVZNa0RTWkJpaApQK3RnK2ZmRElGVkx3NVdKRjJTeFNDbUhJcGVoc0tvSkNZY0Fwa1BjVTUxYTRSSU9lWkQrWDhNVFhtbjlkYXdVbERpYktYRnBwVUFqClc0U0J6Y3VqYUZiUjlOam44bW11cjFWSTZPMTY3TWhTUjEwWFQrYVBKUTlRdXAzYnVrcmdOWXY3YVZPUlB3SngzRDlzQW9pK1A4R2EKMFd4N2hJQjJRZDRwVS9hTy9HdmxORjhYb0Y4RHZqL3hEaWdyTjJncjdpb1dtdFJMSWNDUkdtSnN0dGVUT0N3N2MvTzMxL2JHUHBVMwpOOGI5VFpBanN4RTEyb3lpanFuaDJteE92dWUraE1aUWQxaXZqcy9CR1M3eWxNRGN2L1MwbDBwQVFMUFIxU3pqZCsrVjFKdXIrYmUzCkh5N2tZSWEzTlEyRFJKRlVzL1FiYzQ4T2JxdFJSTk90MkJiR3hhOXQxWXplRkpqUy9oU0xkVFlDRkJrOUluWklINnZIVXFwNVRyTjUKZkF5ZmpUeGtzK2h3cSs0OGw4cTl4alJwQktiVnA3c2w4OFZscWQ5YU4zSDlHTkdOL3RHM0o1dz08L0RBVEE+DQogICAgPFNJR04+NmFlNWZlOWFlOTZjNzQ4OGZlZjQxY2ViODM1YjFkNmE8L1NJR04+DQo8L0NISU5BQkFOSz4NCg0K";
//		byte[] a = "PD94bWwg".getBytes();
//		try {
//			System.out.println(decrypt(as,a,"utf-8"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
    	
	}
    
    

}