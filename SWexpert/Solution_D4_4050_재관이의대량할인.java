import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 82,128 KB
 * 실행시간 : 246 ms
 * 코드길이 : 1,009 B
 * 소요시간 : 1H 10M
 */

public class Solution_D4_4050_재관이의대량할인 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			answer.append('#').append(tc + 1).append(' ');
			int N = Integer.parseInt(in.readLine());
			int[] num = new int[N];
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(num);
			int sum = 0;
			int n = 0;
			for (int i = N - 1; i >= 0; i--) {
				if (n == 2) {
					n = 0;
					continue;
				} else {
					sum += num[i];
					n++;
				}
			}
			answer.append(sum).append('\n');
		}

		System.out.print(answer);
		in.close();
	}

}
