import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author soohyun
 * 메모리: 31,048 KB
 * 실행시간 : 288 ms
 * 코드길이 : 1,113 B
 */

public class Main_B_16916_부분문자열 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String origin = in.readLine();
		String pattern = in.readLine();

		if (kmp(origin, pattern))
			System.out.println(1);
		else
			System.out.println(0);
	}

	private static boolean kmp(String origin, String pattern) {
		int pi[] = getPi(pattern);

		int j = 0;
		for (int i = 0; i < origin.length(); i++) {
			while (j > 0 && origin.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			if (origin.charAt(i) == pattern.charAt(j)) {
				if (j == pattern.length() - 1) {
					return true;
				} else
					j++;
			}
		}

		return false;
	}

	private static int[] getPi(String pattern) {
		int len = pattern.length();
		int pi[] = new int[len];
		int j = 0;
		for (int i = 1; i < len; i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j))
				j = pi[j - 1];
			if (pattern.charAt(i) == pattern.charAt(j))
				pi[i] = ++j;
		}
		return pi;
	}

}
