package br.com.sam9araujo.projetojava.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TripleDesCriptor {

	public static String cifrar(String valor) throws Exception {
		String m_key = load_hc_key();
		String m_codedtext = new TripleDesCriptor().DES3_encrypt(valor, m_key);
		return m_codedtext;
	}

	public static String decifrar(String valorCifrado) throws Exception {
		String m_key = load_hc_key();
		String m_decodedtext = new TripleDesCriptor().DES3_decrypt(valorCifrado, m_key);
		return m_decodedtext;
	}

	private String DES3_encrypt(String clear_message, String pwd) throws Exception {
		String text = paddInputString(clear_message);
		String texthex = asHex(text.getBytes());

		String k1 = null;
		String k2 = null;
		String k3 = null;
		if (pwd.length() == 32) {
			k1 = new String(pwd.substring(0, 16));
			k2 = new String(pwd.substring(16, 32));
			k3 = new String(k1);
		} else if (pwd.length() == 48) {
			k1 = new String(pwd.substring(0, 16));
			k2 = new String(pwd.substring(16, 32));
			k3 = new String(pwd.substring(32, 48));
		} else {
			System.out.println("INVALID: Key length !!");
			return "ERROR";
		}
		String e_codedtext = new TripleDesCriptor().DES_encrypt(texthex, k1);
		String e_decodedtext = new TripleDesCriptor().DES_decrypt(e_codedtext, k2);
		String e_codedtext2 = new TripleDesCriptor().DES_encrypt(e_decodedtext, k3);

		return e_codedtext2;
	}

	private String DES3_decrypt(String cipher_message, String pwd) throws Exception {
		String ciphered_text = paddInputString(cipher_message);

		String k1 = null;
		String k2 = null;
		String k3 = null;
		if (pwd.length() == 32) {
			k1 = new String(pwd.substring(0, 16));
			k2 = new String(pwd.substring(16, 32));
			k3 = new String(k1);
		} else if (pwd.length() == 48) {
			k1 = new String(pwd.substring(0, 16));
			k2 = new String(pwd.substring(16, 32));
			k3 = new String(pwd.substring(32, 48));
		} else {
			System.out.println("INVALID: Key length !!");
			return "ERROR";
		}
		String d_decodedtext = new TripleDesCriptor().DES_decrypt(ciphered_text, k1);
		String d_codedtext = new TripleDesCriptor().DES_encrypt(d_decodedtext, k2);
		String d_decodedtext2 = new TripleDesCriptor().DES_decrypt(d_codedtext, k3);

		String d_return = new String(hexToAscii(d_decodedtext2).trim());

		return d_return;
	}

	private String DES_encrypt(String message, String pwd) throws Exception {
		String algorithm = "DES";
		byte[] encBytes = hexStringToByteArray(pwd);
		setDESKeyParity(encBytes);
		SecretKey key = new SecretKeySpec(encBytes, algorithm);

		Cipher cipher = Cipher.getInstance("DES/ECB/Nopadding");
		cipher.init(1, key);

		byte[] plainTextBytes = hexStringToByteArray(message);
		byte[] cipherText = cipher.doFinal(plainTextBytes);

		return toHex(cipherText);
	}

	private String DES_decrypt(String message, String pwd) throws Exception {
		String algorithm = "DES";
		byte[] decBytes = hexStringToByteArray(pwd);
		setDESKeyParity(decBytes);
		SecretKey key = new SecretKeySpec(decBytes, algorithm);

		Cipher cipher = Cipher.getInstance("DES/ECB/Nopadding");
		cipher.init(2, key);

		byte[] cipheredTextBytes = hexStringToByteArray(message);
		byte[] plainText = cipher.doFinal(cipheredTextBytes);

		return toHex(plainText);
	}

	private byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[(i / 2)] = ((byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16)));
		}
		return data;
	}

	private static String toHex(byte[] stringBytes) {
		StringBuffer result = new StringBuffer(2 * stringBytes.length);
		for (int i = 0; i < stringBytes.length; i++) {
			result.append("0123456789ABCDEF".charAt(stringBytes[i] >> 4 & 0xF))
					.append("0123456789ABCDEF".charAt(stringBytes[i] & 0xF));
		}
		return result.toString();
	}

	private static String load_hc_key() {
		return "13FFFA94312342BCDEFCDEBBDD123EF1";
	}

	private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

	private static String asHex(byte[] buf) {
		char[] chars = new char[2 * buf.length];
		for (int i = 0; i < buf.length; i++) {
			chars[(2 * i)] = HEX_CHARS[((buf[i] & 0xF0) >>> 4)];
			chars[(2 * i + 1)] = HEX_CHARS[(buf[i] & 0xF)];
		}
		return new String(chars);
	}

	private static void setDESKeyParity(byte[] key) {
		byte[] partab = { 1, 1, 2, 2, 4, 4, 7, 7, 8, 8, 11, 11, 13, 13, 14, 14, 16, 16, 19, 19, 21, 21, 22, 22, 25, 25,
				26, 26, 28, 28, 31, 31, 32, 32, 35, 35, 37, 37, 38, 38, 41, 41, 42, 42, 44, 44, 47, 47, 49, 49, 50, 50,
				52, 52, 55, 55, 56, 56, 59, 59, 61, 61, 62, 62, 64, 64, 67, 67, 69, 69, 70, 70, 73, 73, 74, 74, 76, 76,
				79, 79, 81, 81, 82, 82, 84, 84, 87, 87, 88, 88, 91, 91, 93, 93, 94, 94, 97, 97, 98, 98, 100, 100, 103,
				103, 104, 104, 107, 107, 109, 109, 110, 110, 112, 112, 115, 115, 117, 117, 118, 118, 121, 121, 122, 122,
				124, 124, Byte.MAX_VALUE, Byte.MAX_VALUE };
		for (int i = 0; i < key.length; i++) {
			key[i] = partab[(key[i] & 0x7F)];
		}
	}

	private static String paddInputString(String str) {
		String temp = "";
		int fill_len = 0;
		if (str.length() % 8 != 0) {
			fill_len = 8 - str.length() % 8;
		}
		for (int i = 0; i < fill_len; i++) {
			temp = temp + ' ';
		}
		return str + temp;
	}

	private static String hexToAscii(String s) {
		int n = s.length();
		StringBuilder sb = new StringBuilder(n / 2);
		for (int i = 0; i < n; i += 2) {
			char a = s.charAt(i);
			char b = s.charAt(i + 1);
			sb.append((char) (hexToInt(a) << 4 | hexToInt(b)));
		}
		return sb.toString();
	}

	private static int hexToInt(char ch) {
		if (('a' <= ch) && (ch <= 'f')) {
			return ch - 'a' + 10;
		}
		if (('A' <= ch) && (ch <= 'F')) {
			return ch - 'A' + 10;
		}
		if (('0' <= ch) && (ch <= '9')) {
			return ch - '0';
		}
		throw new IllegalArgumentException(String.valueOf(ch));
	}
}