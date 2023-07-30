package com.mgrm.sbiepay;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES256Bit {
	// Initialization Vector for CBC mode
	private static byte[] iv = null;

	public AES256Bit() {
	}

	public static void main(String[] args) {
		// Check for command line arguments
		if (args.length < 1) {
			System.out.println("Please provide a command");
			return;
		}

		// Get the command
		String command = args[0];

		switch (command) {
		case "generateNewKey":
			System.out.println(generateNewKey());
			break;
		case "encrypt":
			// Check for sufficient arguments for encryption
			if (args.length < 3) {
				System.out.println("Please provide a text and a key");
				return;
			}
			SecretKeySpec key = readKeyBytes(args[2]);
			System.out.println(encrypt(args[1], key));
			break;
		case "decrypt":
			// Check for sufficient arguments for decryption
			if (args.length < 3) {
				System.out.println("Please provide a text and a key");
				return;
			}
			key = readKeyBytes(args[2]);
			System.out.println(decrypt(args[1], key));
			break;
		default:
			System.out.println("Unknown command");
		}
	}

	// Encrypt a string using AES 256-bit encryption algorithm
	public static String encrypt(String plainText, SecretKeySpec keySpec) {
		String cipherText = "";

		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
			byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
			cipherText = Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (Exception ex) {
			// Handle exception
		}

		return cipherText;
	}

	// Decrypt a string using AES 256-bit encryption algorithm
	public static String decrypt(String cipherText, SecretKeySpec keySpec) {
		String decryptedText = "";

		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
			byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
			byte[] decryptedBytes = cipher.doFinal(decodedBytes);
			decryptedText = new String(decryptedBytes);
		} catch (Exception ex) {
			// Handle exception
		}

		return decryptedText;
	}

	// Convert a byte array to a hexadecimal string
	public static String asHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder();

		for (int i = 0; i < bytes.length; ++i) {
			hexString.append(Integer.toHexString(256 + (bytes[i] & 255)).substring(1));
		}

		return hexString.toString();
	}

	// Create a secret key specification from a string
	public static SecretKeySpec readKeyBytes(String keyString) {
		SecretKeySpec keySpec = null;
		byte[] keyBytes = new byte[16];

		try {
			byte[] inputBytes = keyString.getBytes("UTF8");

			for (int i = 0; i < 16; ++i) {
				if (i < inputBytes.length) {
					keyBytes[i] = inputBytes[i];
				}
			}

			iv = keyBytes;
			keySpec = new SecretKeySpec(keyBytes, "AES");
		} catch (Exception ex) {
			// Handle exception
		}

		return keySpec;
	}

	// Convert a byte to a hexadecimal string
	public static String byteToHex(byte byteValue) {
		char[] hexDigits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
				'f' };
		char[] hexChars = new char[] { hexDigits[byteValue >> 4 & 15], hexDigits[byteValue & 15] };
		return new String(hexChars);
	}

	// Generate a new AES key
	public static String generateNewKey() {
		String newKey = null;

		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(256);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBytes = secretKey.getEncoded();
			newKey = Base64.getEncoder().encodeToString(keyBytes);
			newKey = newKey.replace("+", "/");
		} catch (Exception ex) {
			// Handle exception
		}

		return newKey;
	}
}
