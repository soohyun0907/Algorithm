import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 92,696 KB
 * 실행시간 : 2,306 ms
 * 코드길이 : 1,611 B
 * 소요시간 : 45M
 */

public class Solution_D4_5643_키순서 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			int cnt = 0;
			int INF = 987654321;
			int N = Integer.parseInt(in.readLine());
			int M = Integer.parseInt(in.readLine());
			int[][] graph = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					graph[i][j] = i == j ? 0 : INF;
				}
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				graph[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
			}

			// 플로이드워셜
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if (k == i) continue;
					for (int j = 0; j < N; j++) {
						if (k == j || i == j) continue;
						if (graph[i][j] > graph[i][k] + graph[k][j]) {
							graph[i][j] = graph[i][k] + graph[k][j];
						}
					}
				}
			}

			for (int i = 0; i < N; i++) {
				boolean flag = true;
				for (int j = 0; j < N; j++) {
					if (graph[i][j] == INF && graph[j][i] == INF) {
						flag = false;
						break;
					}
				}
				if (flag) cnt++;
			}
			answer.append('#').append(tc + 1).append(' ').append(cnt).append('\n');
		} // end test case

		System.out.print(answer);
		in.close();
	} // end main

}
