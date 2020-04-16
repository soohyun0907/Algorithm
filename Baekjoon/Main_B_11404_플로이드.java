
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 44,756 KB
 * 실행시간 : 352 ms
 * 코드길이 : 1,590 B
 */

public class Main_B_11404_플로이드 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int N = Integer.parseInt(in.readLine()); // 도시의 개수
		int m = Integer.parseInt(in.readLine()); // 버스의 개수
		int INF = Integer.MAX_VALUE;
		int[][] city = new int[N][N];

		for (int i = 0; i < N; i++) {
			Arrays.fill(city[i], INF);
		}
		
		// 버스의 개수만큼 출발 도시, 도착 도시, 비용이 주어진다
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int start = Integer.parseInt(st.nextToken()) - 1;
			int dest = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			city[start][dest] = Math.min(city[start][dest], cost);
		}

		// 플로이드 워셜 알고리즘
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (k == i) continue;
				for (int j = 0; j < N; j++) {
					if (k == j || i == j) continue;
					if (city[i][k] != INF && city[k][j] != INF && city[i][j] > city[i][k] + city[k][j])
						city[i][j] = city[i][k] + city[k][j];
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer.append((city[i][j] == INF ? 0 : city[i][j]) + " ");
			}
			answer.append('\n');
		}

		System.out.print(answer);
		in.close();
	}
}
