import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 21,944 KB
 * 실행시간 : 123 ms
 * 코드길이 : 1,194 B
 * 소요시간 : 20M
 */

public class Solution_D3_5215_햄버거다이어트_DP {

	private static int N, L, score, cal;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		// 1. 테스트 케이스 T 입력 받기
		StringTokenizer st;
		int T = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			// 2. 재료의 수 N과 제한 칼로리 L 입력받기
			st = new StringTokenizer(bf.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			int[] dp = new int[L + 1];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine(), " ");
				score = Integer.parseInt(st.nextToken());
				cal = Integer.parseInt(st.nextToken());

				for (int j = L; j >= cal; j--) {
					if (dp[j] < score + dp[j - cal])
						dp[j] = score + dp[j - cal];
				}
			}

			answer.append('#').append(test_case).append(' ').append(dp[L]).append('\n');
		}
		
		System.out.print(answer);
		bf.close();
	}

}
