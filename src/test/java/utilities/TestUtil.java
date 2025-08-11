package utilities;

import java.util.Random;

public class TestUtil {

public static String randomString(int length) {
		
		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			sb.append(s.charAt(random.nextInt(s.length())));
		}

		return sb.toString();

	}

	public static String randomNumber(int length) {
	
		String d = "0123456789";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			sb.append(d.charAt(random.nextInt(d.length())));
		}
		return sb.toString();

	}

	public static String randomAlphaNumeric() {

		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuilder alphaPart = new StringBuilder(4);
		for (int i = 0; i < 4; i++) {
			alphaPart.append(s.charAt(random.nextInt(s.length())));
		}

		String d = "0123456789";
		StringBuilder numberPart = new StringBuilder(3);
		for (int i = 0; i < 3; i++) {
			numberPart.append(d.charAt(random.nextInt(d.length())));
		}

		return alphaPart + "@" + numberPart;

	}


}
