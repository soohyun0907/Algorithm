import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 40,616 KB
 * 시간 : 220 ms
 * 코드길이 : 1,878 B
 * 소요시간 : 30M
 */

public class Main_B_2638_치즈 {

	static int N, M, total;
	static int[][] cheese;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheese = new int[N][M];

		total = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
				if (cheese[i][j] > 0)
					total++;
			}
		} // end input

		int answer = 0;
		while (total != 0) {
			bfs();
			answer++;
		}
		System.out.println(answer);
	} // end main

	private static void bfs() {
		Queue<Point> queue = new LinkedList<Point>();
		boolean[][] visited = new boolean[N][M];
		queue.offer(new Point(0, 0));
		visited[0][0] = true;
		Point cur;
		while (!queue.isEmpty()) {
			cur = queue.poll();

			for (int d = 0; d < delta.length; d++) {
				int curX = cur.x + delta[d][0];
				int curY = cur.y + delta[d][1];

				if (curX < 0 || curX >= N || curY < 0 || curY >= M) continue;

				if (!visited[curX][curY] && cheese[curX][curY] == 0) {
					queue.offer(new Point(curX, curY));
					visited[curX][curY] = true;
				} else if (visited[curX][curY] && cheese[curX][curY] > 0) {
					cheese[curX][curY] = 0;
					total--;
				} else if (!visited[curX][curY] && cheese[curX][curY] > 0) {
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
