import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 15,028 KB
 * 시간 : 128 ms
 * 코드길이 : 2,063 B
 * 소요시간 : 20m
 */

public class Main_B_1012_유기농배추 {

	static int M, N, K;
	static int[][] map;
	static boolean[][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(in.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			int ans = 0;

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(in.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[y][x] = 1;
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						bfs(i, j);
						ans++;
					}
				}
			}

			answer.append(ans).append('\n');
		} // end test-case

		System.out.println(answer);
	} // end main

	private static void bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(i, j));
		visited[i][j] = true;

		Point cur;
		while (!queue.isEmpty()) {
			cur = queue.poll();

			for (int d = 0; d < delta.length; d++) {
				int curX = cur.x + delta[d][0];
				int curY = cur.y + delta[d][1];

				if (curX < 0 || curX >= N || curY < 0 || curY >= M || visited[curX][curY]) continue;

				if (map[curX][curY] == 1 && !visited[curX][curY]) {
					queue.offer(new Point(curX, curY));
					visited[curX][curY] = true;
				}
			}
		}
	}

	private static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
