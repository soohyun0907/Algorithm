import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 19,960 KB
 * 시간 : 1,452 ms
 * 코드길이 : 1,420 B
 * 소요시간 : 20M
 */

public class Main_B_1238_파티 {

	static int INF = Integer.MAX_VALUE;
	static int N, M, X;
	static int[][] road;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		road = new int[N][N];

		for (int i = 0; i < N; i++) {
			Arrays.fill(road[i], INF);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			road[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = Integer.parseInt(st.nextToken());
		} // end input

		// 플로이드 워셜
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (k == i) continue;
				for (int j = 0; j < N; j++) {
					if (k == j || i == j) continue;
					if (road[i][k] != INF && road[k][j] != INF && road[i][j] > road[i][k] + road[k][j]) {
						road[i][j] = road[i][k] + road[k][j];
					}
				}
			}
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			int sum = road[i][X - 1] + road[X - 1][i];
			max = Math.max(max, sum);
		}

		System.out.println(max);

	} // end main

}
