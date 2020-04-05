import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 22,036 KB
 * 실행시간 : 110 ms
 * 코드길이 : 1,571 B
 * 
 * 문제 자체를 이해하는데 오래 걸림.
 */

public class Solution_D4_9659_다항식계산 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			answer.append('#').append(tc).append(' ');
			int N = Integer.parseInt(in.readLine());
			long[] Fi = new long[N + 1];
			int[] ti = new int[N + 1];
			int[] ai = new int[N + 1];
			int[] bi = new int[N + 1];

			for (int n = 2; n < N + 1; n++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				ti[n] = Integer.parseInt(st.nextToken());
				ai[n] = Integer.parseInt(st.nextToken());
				bi[n] = Integer.parseInt(st.nextToken());
			}

			int M = Integer.parseInt(in.readLine());
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int i = 1; i < M + 1; i++) {
				int xi = Integer.parseInt(st.nextToken());
				Fi[0] = 1;
				Fi[1] = xi;
				for (int n = 2; n < N + 1; n++) {
					switch (ti[n]) {
					case 1:
						Fi[n] = (Fi[ai[n]] + Fi[bi[n]]) % 998244353;
						break;
					case 2:
						Fi[n] = (ai[n] * Fi[bi[n]]) % 998244353;
						break;
					case 3:
						Fi[n] = (Fi[ai[n]] * Fi[bi[n]]) % 998244353;
						break;
					}
				}
				answer.append(Fi[N] % 998244353).append(' ');
			}
			answer.append('\n');
		} // end test-case

		System.out.print(answer);
		in.close();
	}

}
