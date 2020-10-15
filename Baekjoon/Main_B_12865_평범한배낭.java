import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,564 KB
 * 시간 : 120 ms
 * 코드길이 : 919 B
 * 소요시간 : 30M
 */

public class Main_B_12865_평범한배낭 {

	static int N, K;
	static int[][] obj;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // 최대 무게
		obj = new int[N][2];
		dp = new int[100001];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			obj[i][0] = Integer.parseInt(st.nextToken()); // W
			obj[i][1] = Integer.parseInt(st.nextToken()); // V
		}

		for (int i = 0; i < N; i++) {
			for (int j = K; j - obj[i][0] >= 0; j--) {
				dp[j] = Math.max(dp[j], dp[j - obj[i][0]] + obj[i][1]);
			}
		}

		System.out.println(dp[K]);
	}

}
