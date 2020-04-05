import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 19,160 KB
 * 실행시간 : 97 ms
 * 코드길이 : 1,548 B
 */

public class Solution_D4_4530_극한의청소작업 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			answer.append('#').append(tc).append(' ');
			long A = Long.parseLong(st.nextToken());
			long B = Long.parseLong(st.nextToken());
			A = toNine(A); // 9진수로 바꾸기.
			B = toNine(B);
			A = toDigit(A); // 10진수로 바꾸기.
			B = toDigit(B);
			long ans = B - A;
			if (A < 0 && B > 0)
				ans -= 1;

			answer.append(ans).append('\n');
		}

		System.out.print(answer);
		in.close();
	}

	private static long toDigit(long n) {
		boolean neg = false;
		if (n < 0)
			neg = true;
		String s = Math.abs(n) + "";
		Long res = (long) 0;
		Long cipher = (long) 0;

		for (int i = s.length() - 1; i >= 0; i--) {
			res += (s.charAt(i) - '0') * (long) Math.pow(9, cipher++);
		}
		if (neg) {
			res = res * -1;
		}
		return res;
	}

	private static long toNine(long n) {
		boolean neg = false;
		if (n < 0)
			neg = true;
		String str = Math.abs(n) + "";
		String num = "";
		if (neg)
			num += '-';
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) - '0' > 4)
				num += str.charAt(i) - '1';
			else
				num += str.charAt(i) - '0';
		}
		return Long.parseLong(num);
	}

}
