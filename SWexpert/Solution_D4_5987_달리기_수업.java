import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 55,772 kb
 * 실행시간 : 154 ms
 * 코드길이 : 1,441 B
 */

public class Solution_D4_5987_달리기_수업 {

	static int N, M;
	static int count;
	static int[] needs;
	static long[] memo;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			answer.append('#').append(t).append(' ');
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int f, s;
			needs = new int[N];
			memo = new long[1 << N];

			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(in.readLine(), " ");
				f = Integer.parseInt(st.nextToken()) - 1;
				s = Integer.parseInt(st.nextToken()) - 1;
				needs[f] |= (1 << s);
			}

			count = 0;
			long r = dfs(0);
			answer.append(r).append('\n');
		} // end test-case

		System.out.print(answer);
		in.close();
	}

	private static long dfs(int flag) {
		if (flag == (1 << N) - 1) {
			return 1;
		}
		
		if (memo[flag] > 0) {
			return memo[flag];
		}
		
		// 순열 만들기
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) == 0) {
				if ((flag & needs[i]) == needs[i]) {
					memo[flag] += dfs(flag | 1 << i);
				}
			}
		}

		return memo[flag];
	}

}
