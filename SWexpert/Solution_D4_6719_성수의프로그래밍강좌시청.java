import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 28,264 KB
 * 실행시간 : 134 ms
 * 코드길이 : 1,073 B
 */

public class Solution_D4_6719_성수의프로그래밍강좌시청 {

	static double A, max;
	static int N, K;
	static int M[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			answer.append('#').append(tc).append(' ');
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			M = new int[N + 1];
			max = Double.MIN_VALUE;
			A = 0;

			st = new StringTokenizer(in.readLine(), " ");
			for (int n = 1; n < N + 1; n++) {
				M[n] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(M);
			while (--K >= 0) {
				A = (A + M[N - K]) / 2;
			}
			answer.append(A).append('\n');
		}

		System.out.print(answer);
		in.close();
	}
}
