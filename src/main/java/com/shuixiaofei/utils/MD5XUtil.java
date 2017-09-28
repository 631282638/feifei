package com.shuixiaofei.utils;

import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class MD5XUtil {

	/*
	 * /// <summary> /// DES解密字符串 /// </summary> /// <param
	 * name="decryptString">待解密的字符串</param> /// <param
	 * name="decryptKey">解密密钥,要求为8位,和加密密钥相同</param> ///
	 * <returns>解密成功返回解密后的字符串，失败返源串</returns> public static string
	 * DecryptDES(string decryptString, string decryptKey) { try { String key =
	 * "final@#A" ;
	 * 
	 * byte[] rgbKey = Encoding.UTF8.GetBytes(decryptKey); byte[] rgbIV = Keys;
	 * byte[] inputByteArray = Convert.FromBase64String(decryptString);
	 * DESCryptoServiceProvider DCSP = new DESCryptoServiceProvider();
	 * MemoryStream mStream = new MemoryStream(); CryptoStream cStream = new
	 * CryptoStream(mStream, DCSP.CreateDecryptor(rgbKey, rgbIV),
	 * CryptoStreamMode.Write); cStream.Write(inputByteArray, 0,
	 * inputByteArray.Length); cStream.FlushFinalBlock(); return
	 * Encoding.UTF8.GetString(mStream.ToArray()); } catch { return
	 * decryptString; } }
	 */
	/*
	 * /// <summary> /// DES加密字符串 /// </summary> /// <param
	 * name="encryptString">待加密的字符串</param> /// <param
	 * name="encryptKey">加密密钥,要求为8位</param> ///
	 * <returns>加密成功返回加密后的字符串，失败返回源串</returns>
	 * 
	 * public static string DesEncrypt(string inputString, string encryptKey) {
	 * byte[] byKey = null; byte[] IV = { 0x12, 0x34, 0x56, 0x78, 0x90, 0xAB,
	 * 0xCD, 0xEF }; try { byKey =
	 * System.Text.Encoding.UTF8.GetBytes(encryptKey.Substring(0, 8));
	 * DESCryptoServiceProvider des = new DESCryptoServiceProvider(); byte[]
	 * inputByteArray = Encoding.UTF8.GetBytes(inputString); MemoryStream ms =
	 * new MemoryStream(); CryptoStream cs = new CryptoStream(ms,
	 * des.CreateEncryptor(byKey, IV), CryptoStreamMode.Write);
	 * cs.Write(inputByteArray, 0, inputByteArray.Length); cs.FlushFinalBlock();
	 * return Convert.ToBase64String(ms.ToArray()); } catch (System.Exception
	 * error) { //return error.Message; return null; } }
	 */
	
	/**
	 * 加密算法
	 * @param decryptString
	 * @param decryptKey final@#A
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String decryptString, String decryptKey) throws Exception {	
		byte[] IV = { 0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB,
				(byte) 0xCD, (byte) 0xEF };
		AlgorithmParameterSpec iv = null;// 加密算法的参数接口
		iv = new IvParameterSpec(IV);// 设置向量
		byte rawKeyData[] = decryptKey.getBytes();
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte data[] = decryptString.getBytes("UTF-8");
		byte encryptedData[] = cipher.doFinal(data);	
		String a = null;
		if (encryptedData != null){
		BASE64Encoder b = new sun.misc.BASE64Encoder();
			a = b.encode(encryptedData);
		}
		return a;
	}
	/**
	 * 解密算法
	 * @param decryptString
	 * @param decryptKey final@#A
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String decryptString, String decryptKey) throws Exception {	
		byte[] IV = { 0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB,
				(byte) 0xCD, (byte) 0xEF };
		AlgorithmParameterSpec iv = null;// 加密算法的参数接口
		iv = new IvParameterSpec(IV);// 设置向量
		byte rawKeyData[] = decryptKey.getBytes();
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] b =null;
		if (decryptString != null){
		BASE64Decoder decoder = new BASE64Decoder();
		b = decoder.decodeBuffer(decryptString);
		}
		byte decryptedData[] = cipher.doFinal(b);
		return new String(decryptedData, "UTF8");
	}

	public static String base64Encode(byte[] s) {
		if (s == null)
			return null;
		BASE64Encoder b = new sun.misc.BASE64Encoder();
		return b.encode(s);
	}
	public static void main(String[] args) throws Exception {
	    System.out.println(MD5XUtil.encrypt("000000","Allin@#2"));
		System.out.println(MD5XUtil.decrypt("33QYoLKY8Hw==","final@#A"));
		System.out.println(MD5XUtil.decrypt("3A1sqZ0qmgU==","final@#A"));
	}
	

}
	
	
	
	
	
	
	
	
	
	
	
	
