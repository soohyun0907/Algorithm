import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 134,864 KB
 * 시간 : 440 ms
 * 코드길이 : 4,063 B
 * 소요시간 : 3H
 */

public class Main_B_17144_미세먼지안녕 {

	static int R, C, T;
	static int[][] room;
	static Queue<Point> queue;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		room = new int[R][C];
		queue = new LinkedList<Point>();

		int n, cnt = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < C; j++) {
				n = Integer.parseInt(st.nextToken());
				if (n > 0) {
					queue.offer(new Point(i, j, n));
				} else { // 공기청정기가 설치된 위치
					room[i][j] = n;
				}
			}
		} // end input

		// bfs 시작
		Point cur;
		int ans = 0;
		while (--T >= 0) {
			// 1. 미세먼지 확산
			while (!queue.isEmpty()) {
				cur = queue.poll();
				cnt = 0;

				if (cur.n < 5) { // 5보다 작은 경우에는 확산이 되지 않음
					room[cur.x][cur.y] += cur.n;
					continue;
				}

				for (int d = 0; d < delta.length; d++) {
					int curX = cur.x + delta[d][0];
					int curY = cur.y + delta[d][1];

					if (curX < 0 || curX >= R || curY < 0 || curY >= C || room[curX][curY] == -1) continue;

					room[curX][curY] += cur.n / 5;
					cnt++;
				}

				room[cur.x][cur.y] += cur.n - (cur.n / 5) * cnt;
			}

			// 2. 공기청정기 작동
			for (int i = 0; i < R; i++) {
				if (room[i][0] < 0) {
					operAir(i, 0, false);
					operAir(i + 1, 0, true);
					break;
				}
			}

			// 3. 미세먼지 양 계산 및 room 초기화
			ans = calInit();
		}

		System.out.println(ans);
	} // end main

	private static int calInit() {
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] > 0) {
					cnt += room[i][j];
					queue.offer(new Point(i, j, room[i][j]));
					room[i][j] = 0;
				}
			}
		}

		return cnt;
	}

	private static void operAir(int i, int j, boolean clockwise) {
		int dir = 1;
		int curX = i + delta[dir][0];
		int curY = j + delta[dir][1];
		int curN = room[curX][curY];
		int next = curN;
		room[curX][curY] = 0;
		while (curY < C - 1) { // (공통) 우측으로 이동
			curX += delta[dir][0];
			curY += delta[dir][1];
			next = room[curX][curY];
			room[curX][curY] = curN;
			curN = next;
		}
		curY = C - 1;
		if (!clockwise) {
			dir = 0; // 위로 이동
			while (curX > 0) {
				curX += delta[dir][0];
				curY += delta[dir][1];
				next = room[curX][curY];
				room[curX][curY] = curN;
				curN = next;
			}
			curX = 0;
			dir = 3; // 좌로 이동
			while (curY > 0) {
				curX += delta[dir][0];
				curY += delta[dir][1];
				next = room[curX][curY];
				room[curX][curY] = curN;
				curN = next;
			}
			curY = 0;
			dir = 2; // 아래로 이동
			while (curX != i - 1) {
				curX += delta[dir][0];
				curY += delta[dir][1];
				next = room[curX][curY];
				room[curX][curY] = curN;
				curN = next;
			}
		} else {
			dir = 2; // 아래로 이동
			while (curX < R - 1) {
				curX += delta[dir][0];
				curY += delta[dir][1];
				next = room[curX][curY];
				room[curX][curY] = curN;
				curN = next;
			}
			curX = R - 1;
			dir = 3; // 좌로 이동
			while (curY > 0) {
				curX += delta[dir][0];
				curY += delta[dir][1];
				next = room[curX][curY];
				room[curX][curY] = curN;
				curN = next;
			}
			curX = R - 1;
			curY = 0;
			dir = 0; // 위로 이동
			while (curX != i + 1) {
				curX += delta[dir][0];
				curY += delta[dir][1];
				next = room[curX][curY];
				room[curX][curY] = curN;
				curN = next;
			}
		}
	}

	private static class Point {
		int x;
		int y;
		int n;

		public Point(int x, int y, int n) {
			this.x = x;
			this.y = y;
			this.n = n;
		}
	}

}
