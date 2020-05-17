import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 15,556 KB
 * 시간 : 112 ms
 * 코드길이 : 2,095 B
 * 소요시간 : 30M
 */

public class Main_B_2636_치즈 {

	static int N, M, totalCnt;
	static int[][] cheese;
	static boolean[][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheese = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
				if (cheese[i][j] > 0)
					totalCnt++;
			}
		}

		int result = totalCnt; // 녹기전의 값 저장
		int time = 0; // 녹는데 걸리는 시간
		while (totalCnt != 0) {
			int melt = bfs();
			time++;
			result = totalCnt;
			totalCnt -= melt;
		}

		System.out.println(time + "\n" + result);
	} // end main

	private static int bfs() {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(0, 0));
		visited[0][0] = true;
		int cnt = 0;
		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int n = 0; n < delta.length; n++) {
				int curX = cur.x + delta[n][0];
				int curY = cur.y + delta[n][1];

				if (curX < 0 || curX >= N || curY < 0 || curY >= M) continue;

				if (!visited[curX][curY] && cheese[curX][curY] == 1) {
					cheese[curX][curY] = 0;
					visited[curX][curY] = true;
					cnt++;
				} else if (!visited[curX][curY] && cheese[curX][curY] == 0) {
					queue.offer(new Point(curX, curY));
					visited[curX][curY] = true;
				}
			}
		}

		for (boolean[] row : visited) {
			Arrays.fill(row, false);
		}

		return cnt;
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
