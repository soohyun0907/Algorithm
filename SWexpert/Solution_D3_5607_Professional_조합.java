import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 28,044 KB 
 * 실행시간 : 390 ms 
 * 코드길이 : 1,238 B
 */

public class Solution_D3_5607_Professional_조합 {

	static long m = 1234567891;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			answer.append('#').append(tc).append(' ');
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			long A = 1, B = 1;
			// N!
			for (int i = 1; i < N + 1; i++) {
				A *= i;
				A %= m;
				// R! * (N-R)!
				if (i == R) B = A * B % m;
				if (i == N - R) B = A * B % m;
			}
			
			// ans = B^(m-2) mod m
			long ans = mod(B, m - 2, m);
			ans = (A * ans) % m; // (A*B) mod m = ((A mod m) * (B mod m)) mod m
			answer.append(ans).append('\n');
		} // end test-case

		System.out.print(answer);
		in.close();
	}

	// B^(m-2) mod m 구하기
	private static long mod(long B, long l, long m) {
		long ans = 1;
		while (l > 0) {
			if (l % 2 != 0) {
				ans *= B;
				ans %= m;
			}
			B *= B;
			B %= m;
			l /= 2;
		}
		return ans;
	}

}
