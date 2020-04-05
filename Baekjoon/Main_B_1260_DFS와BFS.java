import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 23,712 KB
 * 실행시간 : 240 ms
 * 코드길이 : 1,617 B
 * 소요시간 : 30M
 */

public class Main_B_1260_DFS와BFS {

	static int N, M;
	static int[][] graph;
	static boolean[] visited;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		graph = new int[N + 1][N + 1];
		visited = new boolean[N + 1];

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(in.readLine(), " ");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			graph[i][j] = graph[j][i] = 1;
		}

		dfs(V);
		answer.append('\n');
		Arrays.fill(visited, false);
		bfs(V);
		answer.append('\n');

		System.out.print(answer);
	}

	private static void dfs(int v) {
		visited[v] = true;
		answer.append(v).append(" ");
		for (int j = 1; j < N + 1; j++) {
			if (graph[v][j] > 0 && !visited[j])
				dfs(j);
		}
	}

	private static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<>();
		visited[v] = true;
		queue.offer(v);

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			answer.append(cur).append(" ");
			for (int j = 1; j < N + 1; j++) {
				if (graph[cur][j] > 0 && !visited[j]) {
					visited[j] = true;
					queue.offer(j);
				}
			}
		}
	}

}
