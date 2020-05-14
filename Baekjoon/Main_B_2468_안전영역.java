import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 56,516 KB
 * 시간 : 292 ms
 * 코드길이 : 2,031 B
 * 소요시간 : 30M
 */

public class Main_B_2468_안전영역 {

	static int N;
	static boolean[][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		int[][] map = new int[N][N];
		int maxNum = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (maxNum < map[i][j])
					maxNum = map[i][j];
			}
		}

		int result = -1;
		visited = new boolean[N][N];
		while (maxNum-- >= 0) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] <= maxNum)
						visited[i][j] = true;
				}
			}

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						countSafeArea(i, j);
						cnt++;
					}
				}
			}

			if (result < cnt) {
				result = cnt;
			}

			for (boolean[] row : visited) {
				Arrays.fill(row, false);
			}
		}

		System.out.println(result);
	} // end main

	private static void countSafeArea(int i, int j) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(i, j));
		visited[i][j] = true;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int n = 0; n < delta.length; n++) {
				int curX = cur.x + delta[n][0];
				int curY = cur.y + delta[n][1];

				if (curX < 0 || curX >= N || curY < 0 || curY >= N || visited[curX][curY]) continue;

				queue.offer(new Point(curX, curY));
				visited[curX][curY] = true;
			}
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
