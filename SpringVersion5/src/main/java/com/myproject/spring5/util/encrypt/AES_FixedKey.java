package com.myproject.spring5.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author gykim
 * 
 */
public class AES_FixedKey {

	private static final String KEY = "mobilebp9292#M@a";

	/**
	 * <pre>
	 * 16진수 문자열을 바이트 배열로 변환한다.
	 * </pre>
	 * 
	 * @author redhot
	 * @param String
	 * @return byte[]
	 */
	private static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() == 0) {
			return null;
		}
		byte[] byteArray = new byte[hex.length() / 2];
		for (int i = 0; i < byteArray.length; i++) {
			byteArray[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return byteArray;
	}

	/**
	 * <pre>
	 * 바이트 배열을 16진수 문자열로 바꾼다.
	 * </pre>
	 * 
	 * @author redhot
	 * @param byte[]
	 * @return String
	 */
	private static String byteArrayToHex(byte[] ba) {
		if (ba == null || ba.length == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer(ba.length * 2);
		String hexNumber;
		for (int x = 0; x < ba.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		return sb.toString();
	}

	/**
	 * <pre>
	 * AES128 방식의 암호화
	 * </pre>
	 * 
	 * @author redhot
	 * @param String
	 * @return String
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String encrypt(String message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// use key coss2
		SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
		// Instantiate the cipher
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = null;
		try {
			encrypted = cipher.doFinal(message.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return byteArrayToHex(encrypted);
	}

	/**
	 * <pre>
	 * AES128 방식의 복호화
	 * </pre>
	 * 
	 * @author redhot
	 * @param message
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws UnsupportedEncodingException 
	 */
	public static String decrypt(String encrypted) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] original = cipher.doFinal(hexToByteArray(encrypted));
		String originalString = new String(original);
		return originalString;
	}

}