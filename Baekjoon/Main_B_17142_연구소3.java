import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 42,620 KB
 * 시간 : 308 ms
 * 코드길이 : 2,656 B
 * 소요시간 : 1H 30M
 */

public class Main_B_17142_연구소3 {

	static int N, M, size, min;
	static int[][] map, temp;
	static Point[] virus;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상, 우, 하, 좌

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		temp = new int[N][N];
		virus = new Point[10];
		size = 0;
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 1) {
					virus[size++] = new Point(i, j);
				}
			}
		} // end input
		permutation(0, 0, 0);
		min = (min == Integer.MAX_VALUE) ? -1 : min;
		System.out.println(min);
		in.close();
	} // end main

	private static void permutation(int index, int n, int flag) {
		if (index == M) {
			int time = calTime(flag);
			min = Math.min(min, time);
			return;
		}

		for (int i = n; i < size; i++) {
			if ((flag & (1 << i)) == 0) {
				permutation(index + 1, i, (flag | (1 << i)));
			}
		}
	}

	private static int calTime(int flag) {
		Queue<Point> queue = new LinkedList<Point>();
		// 원본 배열 복사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = map[i][j];
			}
		}
		for (int i = 0; i < size; i++) {
			if ((flag & (1 << i)) != 0) {
				queue.add(virus[i]);
				temp[virus[i].x][virus[i].y] = 9;
			}
		}

		int time = 0, n, curX, curY;
		Point cur;
		while (!queue.isEmpty()) {
			if (check()) {
				return time;
			}

			n = queue.size();
			while (--n >= 0) {
				cur = queue.poll();

				for (int i = 0; i < delta.length; i++) {
					curX = cur.x + delta[i][0];
					curY = cur.y + delta[i][1];

					if (curX < 0 || curX >= N || curY < 0 || curY >= N) continue;

					if (temp[curX][curY] == 0 || temp[curX][curY] == 2) {
						queue.add(new Point(curX, curY));
						temp[curX][curY] = 9;
					}
				}
			}
			time++;
		}

		if (!check())
			return Integer.MAX_VALUE;

		return time;
	}

	static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (temp[i][j] == 0)
					return false;
			}
		}
		return true;
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
