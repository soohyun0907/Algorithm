import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,116 KB
 * 실행시간 : 76 ms
 * 코드길이 : 3,476 B
 */

public class Main_B_13460_구슬탈출2 {

	static Point point;
	static int N, M;
	static char[][] board;
	static boolean[][][][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상, 우, 하, 좌

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		visited = new boolean[N][M][N][M];
		point = new Point();

		for (int i = 0; i < N; i++) {
			String tmp = in.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = tmp.charAt(j);
				if (board[i][j] == 'R') {
					point.rx = i;
					point.ry = j;
				} else if (board[i][j] == 'B') {
					point.bx = i;
					point.by = j;
				}
			}
		}

		System.out.println(move());
		in.close();
	} // end main

	// bfs
	private static int move() {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(point);

		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			visited[cur.rx][cur.ry][cur.bx][cur.by] = true;

			// 10회 이상이라면 더 돌릴 필요 없이 break;
			if (cur.cnt >= 10) {
				return -1;
			}

			for (int n = 0; n < delta.length; n++) {
				boolean flagRed = false;
				boolean flagBlue = false;
				// 이전에 이동했던 방향의 반대방향으로 이동하지 못하도록 예외처리
				if (cur.dir != -1 && n == ((cur.dir % 4) + 2) % 4)
					continue;
				int rx = cur.rx;
				int ry = cur.ry;

				// 벽이나 구멍을 만날때까지 무조건 이동
				while (true) {
					rx += delta[n][0];
					ry += delta[n][1];

					// 벽을 만나면 하나 빼서 .인 자리로 이동
					if (board[rx][ry] == '#') {
						rx -= delta[n][0];
						ry -= delta[n][1];
						break;
					}
					if (board[rx][ry] == 'O') {
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
					}
					if (board[bx][by] == 'O') {
						flagBlue = true;
						break;
					}
				}

				// 빨간 구슬만 빠졌다면 더 볼필요 없이 return
				if (flagRed && !flagBlue) {
					return cur.cnt + 1;
				} 
				// 파란 구슬만 빠진 경우 다시 돌면서 다른 경우 있나 탐색
				else if (flagBlue) {
					continue;
				}

				// 제자리라면 다시 continue
				if (rx == cur.rx && ry == cur.ry && bx == cur.bx && by == cur.by)
					continue;

				// 제자리가 아니고 다 굴러서 이동했는데 같은 자리라면
				if (rx == bx && ry == by) {
					switch (n) {
					case 0: // 상
						if (cur.rx > cur.bx) { // 초기 위치에서 빨간 구슬이 더 위에 있었다면
							rx += 1;
						} else {
							bx += 1;
						}
						break;
					case 1: // 우
						if (cur.ry > cur.by) { // 빨간 구슬이 더 우측에 존재했다면
							by -= 1;
						} else {
							ry -= 1;
						}
						break;
					case 2: // 하
						if (cur.rx > cur.bx) { // 빨간 구슬이 더 아래에 위치했다면
							bx -= 1;
						} else {
							rx -= 1;
						}
						break;
					case 3: // 좌
						if (cur.ry > cur.by) { // 빨간 구슬이 저 왼쪽에 위치했다면
							ry += 1;
						} else {
							by += 1;
						}
						break;
					}
				}
				// 방문하지 않았던 좌표라면
				// -> 빨간 구슬과 파란 구슬 모두를 체크하는 이유는
				// 빨간 구슬의 위치가 같은데 파란 구슬의 위치는 다른 경우도 존재하기 때문에!
				if (!visited[rx][ry][bx][by]) {
					queue.add(new Point(rx, ry, bx, by, cur.cnt + 1, n));
				}
			} // end for delta
		} // end while

		return -1;
	}

	static class Point {
		int rx;
		int ry;
		int bx;
		int by;
		int cnt;
		int dir;

		public Point() {
			this.cnt = 0;
			this.dir = -1;
		}

		public Point(int rx, int ry, int bx, int by, int cnt, int dir) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
			this.dir = dir;
		}
	}
}
