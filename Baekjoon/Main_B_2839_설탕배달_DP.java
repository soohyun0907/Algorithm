import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author soohyun
 * 메모리 : 12,956 KB
 * 시간 : 76 ms
 * 코드길이 : 730 B
 */

public class Main_B_2839_설탕배달_DP {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());

		int[] memo = new int[N + 1];
		for (int i = 0; i < memo.length; i++) { // 3kg
			if (i % 3 == 0) {
				memo[i] = i / 3;
			} else {
				memo[i] = Integer.MAX_VALUE;
			}
		}

		for (int i = 5; i < memo.length; i++) { // 5kg
			if (memo[i - 5] != Integer.MAX_VALUE && memo[i] > memo[i - 5] + 1) {
				memo[i] = memo[i - 5] + 1;
			}
		}

		System.out.println(memo[N] == Integer.MAX_VALUE ? -1 : memo[N]);
	}

}
