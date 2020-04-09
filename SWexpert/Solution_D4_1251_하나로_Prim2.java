
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 49,284 KB 
 * 실행시간 : 251 ms 
 * 코드길이 : 2,029 B 
 * 소요시간 : 40M
 * 프림 알고리즘 사용
 */

public class Solution_D4_1251_하나로_Prim2 {

	static long[][] island;
	static long[][] graph;
	static int[] parents;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			answer.append('#').append(tc).append(' ');
			int N = Integer.parseInt(in.readLine());
			island = new long[N][2];
			StringTokenizer stX = new StringTokenizer(in.readLine(), " ");
			StringTokenizer stY = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				island[i][0] = Long.parseLong(stX.nextToken());
				island[i][1] = Long.parseLong(stY.nextToken());
			}

			double E = Double.parseDouble(in.readLine()); // 입력완료
			graph = new long[N][N];
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					graph[i][j] = graph[j][i] = (long) (Math.pow(island[i][0] - island[j][0], 2)
							+ Math.pow(island[i][1] - island[j][1], 2));
				}
			}

			boolean[] check = new boolean[N];
			long[] key = new long[N];
			int[] p = new int[N];

			Arrays.fill(key, Long.MAX_VALUE);

			p[0] = -1;
			key[0] = 0;

			for (int i = 0; i < N - 1; i++) {
				long min = Long.MAX_VALUE;
				int index = -1;
				for (int j = 0; j < N; j++) {
					if (!check[j] && key[j] < min) {
						index = j;
						min = key[j];
					}
				}

				check[index] = true;
				for (int j = 0; j < N; j++) {
					if (!check[j] && graph[index][j] > 0 && key[j] > graph[index][j]) {
						p[j] = index;
						key[j] = graph[index][j];
					}
				}
			}
			double result = 0;
			for (int i = 0; i < N; i++) {
				result += key[i];
			}
			result *= E;
			answer.append(Math.round(result)).append('\n');
		} // end test-case

		System.out.print(answer);
		in.close();
	}
}
