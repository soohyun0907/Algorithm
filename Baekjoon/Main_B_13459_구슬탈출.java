import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,064 KB
 * 시간 : 72 ms
 * 코드길이 : 3330 B
 * 소요시간 :1H 15M
 */

public class Main_B_13459_구슬탈출 {

	static char[][] board;
	static boolean[][][][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상, 우, 하, 좌

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		visited = new boolean[N][M][N][M];
		Point info = new Point();

		for (int i = 0; i < N; i++) {
			String tmp = in.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = tmp.charAt(j);
				if (board[i][j] == 'R') {
					info.rx = i;
					info.ry = j;
				} else if (board[i][j] == 'B') {
					info.bx = i;
					info.by = j;
				}
			}
		} // end input

		System.out.println(bfs(info));

	} // end main

	private static int bfs(Point info) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(info);

		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			visited[cur.rx][cur.ry][cur.bx][cur.by] = true;

			if (cur.cnt >= 10)
				return 0;
			for (int n = 0; n < delta.length; n++) {
				boolean flagRed = false;
				boolean flagBlue = false;

				if (cur.dir > 0 && n == ((cur.dir % 4) + 2) % 4)
					continue;
				int rx = cur.rx;
				int ry = cur.ry;
				while (true) {
					rx += delta[n][0];
					ry += delta[n][1];
					if (board[rx][ry] == '#') {
						rx -= delta[n][0];
						ry -= delta[n][1];
						break;
					} else if (board[rx][ry] == 'O') {
						cur.cnt += 1;
						flagRed = true;
						break;
					}
				}

				int bx = cur.bx;
				int by = cur.by;
				while (true) {
					bx += delta[n][0];
					by += delta[n][1];
					if (board[bx][by] == '#') {
						bx -= delta[n][0];
						by -= delta[n][1];
						break;
					} else if (board[bx][by] == 'O') {
						flagBlue = true;
						break;
					}
				}

				if (flagRed && !flagBlue && cur.cnt <= 10) {
					return 1;
				} else if (flagBlue) {
					continue;
				}
				if (cur.rx == rx && cur.ry == ry && cur.bx == bx && cur.by == by)
					continue;

				if (rx == bx && ry == by) {
					switch (n) {
					case 0: // 상
						if (cur.rx > cur.bx)
							rx += 1;
						else
							bx += 1;
						break;
					case 1: // 우
						if (cur.ry > cur.by)
							by -= 1;
						else
							ry -= 1;
						break;
					case 2: // 하
						if (cur.rx > cur.bx)
							bx -= 1;
						else
							rx -= 1;
						break;
					case 3: // 좌
						if (cur.ry > cur.by)
							ry += 1;
						else
							by += 1;
						break;
					}
				}
				if (!visited[rx][ry][bx][by])
					queue.offer(new Point(rx, ry, bx, by, n, cur.cnt + 1));
			} // end delta
		} // end while

		return 0;
	} // end bfs

	static class Point {
		int rx;
		int ry;
		int bx;
		int by;
		int dir;
		int cnt;

		public Point() {
			this.rx = 0;
			this.ry = 0;
			this.bx = 0;
			this.by = 0;
			this.dir = -1;
			this.cnt = 0;
		}

		public Point(int rx, int ry, int bx, int by, int dir, int cnt) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.dir = dir;
			this.cnt = cnt;
		}
	}
}
