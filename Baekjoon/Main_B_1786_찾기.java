import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author soohyun
 * 메모리: 89,108 KB
 * 실행시간: 456 ms
 * 코드길이: 1,091 B
 * 소요시간: 25M
 */

public class Main_B_1786_찾기 {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String T = in.readLine();
		String P = in.readLine();
		int cnt = kmp(T, P);
		System.out.println(cnt + "\n" + sb);
	}

	private static int kmp(String t, String p) {
		int[] pi = getPi(p);
		int j = 0;
		int cnt = 0;
		for (int i = 0; i < t.length(); i++) {
			while (j > 0 && t.charAt(i) != p.charAt(j))
				j = pi[j - 1];
			if (t.charAt(i) == p.charAt(j)) {
				if (j == p.length() - 1) {
					cnt++;
					sb.append(i - p.length() + 2 + " ");
					j = pi[j];
				} else
					j++;
			}
		}
		return cnt;
	}

	private static int[] getPi(String p) {
		int len = p.length();
		int[] pi = new int[len];
		int j = 0;
		for (int i = 1; i < len; i++) {
			while (j > 0 && p.charAt(i) != p.charAt(j))
				j = pi[j - 1];
			if (p.charAt(i) == p.charAt(j))
				pi[i] = ++j;
		}
		return pi;
	}

}
