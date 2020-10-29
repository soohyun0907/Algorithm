import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 16,320 KB
 * 시간 : 156 ms
 * 코드길이 : 2,410 B
 * 소요시간 : 2H 20M
 */

public class Main_B_1175_배달 {

	static int N, M;
	static char[][] room;
	static Point[] points;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new char[N][M];
		points = new Point[3];

		int cnt = 1;
		for (int i = 0; i < N; i++) {
			room[i] = in.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (room[i][j] == 'S')
					points[0] = new Point(i, j);
				else if (room[i][j] == 'C')
					points[cnt++] = new Point(i, j);
			}
		} // end input

		System.out.println(bfs(points[0]));
	}

	private static int bfs(Point start) {
		int time = -1;
		Queue<Info> queue = new LinkedList<Info>();
		boolean[][][][] visited = new boolean[N][M][4][3];
		queue.add(new Info(start.x, start.y, 4, 0, 0));

		Info cur;
		int curX, curY;
		while (!queue.isEmpty()) {
			cur = queue.poll();

			if (cur.x == points[1].x && cur.y == points[1].y) {
				if (cur.status != 1)
					cur.status += 1;
			} else if (cur.x == points[2].x && cur.y == points[2].y) {
				if (cur.status < 2)
					cur.status += 2;
			}

			if (cur.status == 3) {
				time = cur.time;
				break;
			}

			for (int d = 0; d < delta.length; d++) {
				if (cur.dir == d) continue;
				curX = cur.x + delta[d][0];
				curY = cur.y + delta[d][1];

				if (curX < 0 || curX >= N || curY < 0 || curY >= M || room[curX][curY] == '#'
						|| visited[curX][curY][d][cur.status])
					continue;

				queue.add(new Info(curX, curY, d, cur.time + 1, cur.status));
				visited[curX][curY][d][cur.status] = true;
			}
		}

		return time;
	}

	private static class Info {
		int x;
		int y;
		int dir;
		int time;
		int status; // 0:아무곳도 배달X, 1: c1만 배달, 2: c2만 배달

		public Info(int x, int y, int dir, int time, int status) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.time = time;
			this.status = status;
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
