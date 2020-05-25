import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 166,464 KB
 * 시간 : 608 ms
 * 코드길이 : 3,007 B
 * 소요시간 : 40M
 */

public class Main_B_2573_빙산 {

	static int N, M;
	static int[][] iceberg;
	static boolean[][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상, 우, 하, 좌

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		iceberg = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				iceberg[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input

		int cnt, result = 0;
		while (true) {
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (iceberg[i][j] > 0 && !visited[i][j]) {
						bfs(i, j);
					}
				}
			}
			// visited 배열 초기화
			initVisited();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (iceberg[i][j] > 0 && !visited[i][j]) {
						checkIceberg(i, j);
						cnt++;
					}
				}
			}

			initVisited();
			result++;

			if (cnt >= 2)
				break;
			else if (cnt == 0) {
				result = 0;
				break;
			}

		}
		System.out.println(result);
		in.close();
	} // end main

	private static void checkIceberg(int i, int j) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(i, j));
		visited[i][j] = true;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int n = 0; n < delta.length; n++) {
				int curX = cur.x + delta[n][0];
				int curY = cur.y + delta[n][1];

				if (curX < 0 || curX >= N || curY < 0 || curY >= M || visited[curX][curY] || iceberg[curX][curY] == 0)
					continue;

				queue.add(new Point(curX, curY));
				visited[curX][curY] = true;
			}
		}
	}

	private static void bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(i, j));
		visited[i][j] = true;
		int cnt;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			cnt = 0;

			for (int n = 0; n < delta.length; n++) {
				int curX = cur.x + delta[n][0];
				int curY = cur.y + delta[n][1];

				if (curX < 0 || curX >= N || curY < 0 || curY >= M || visited[curX][curY])
					continue;

				if (iceberg[curX][curY] > 0) {
					queue.add(new Point(curX, curY));
					visited[curX][curY] = true;
				} else // 0이면 빙산이 녹아야하므로 cnt++
					cnt++;
			}

			iceberg[cur.x][cur.y] -= cnt;
			if (iceberg[cur.x][cur.y] < 0)
				iceberg[cur.x][cur.y] = 0;
		}
	}

	private static void initVisited() {
		for (boolean[] row : visited) {
			Arrays.fill(row, false);
		}
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
