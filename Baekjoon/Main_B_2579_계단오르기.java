import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author soohyun
 * 메모리 : 13,036 KB
 * 실행시간 : 76 ms
 * 코드길이 : 948 B
 * 소요시간 : 2H
 */

public class Main_B_2579_계단오르기 {

	static int n, result;
	static int[] stair;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		stair = new int[n + 1];
		int[] dp = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			stair[i] = Integer.parseInt(in.readLine());
		}
		if (n == 1) {
			dp[1] = stair[1];
		} else if (n == 2) {
			dp[2] = stair[1] + stair[2];
		} else if (n == 3) {
			dp[3] = Math.max(stair[1], stair[2]) + stair[3];
		} else {
			dp[1] = stair[1];
			dp[2] = stair[1] + stair[2];
			dp[3] = Math.max(stair[1], stair[2]) + stair[3];
			for (int i = 4; i < n + 1; i++) {
				dp[i] = Math.max(dp[i - 3] + stair[i - 1], dp[i - 2]) + stair[i];
			}
		}

		System.out.println(dp[n]);
		in.close();
	}

}
