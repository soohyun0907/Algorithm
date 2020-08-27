import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 19,440 KB
 * 실행시간 : 116ms
 * 코드길이 : 2,895 B
 * 소요시간 : 43m
 */

public class Solution_D5_7793_오나의여신님_재풀이 {

	static int N, M;
	static int res;
	static char[][] map;
	static boolean[][] visited;
	static Queue<Point> SY;
	static Queue<Point> devil;
	static boolean isArrived;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상,우,하,좌

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new char[N][M];
			visited = new boolean[N][M];
			SY = new LinkedList<Point>();
			devil = new LinkedList<Point>();
			isArrived = false;
			res = 0;

			for (int i = 0; i < N; i++) {
				String tmp = in.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = tmp.charAt(j);

					if (map[i][j] == 'S') {
						SY.add(new Point(i, j));
						visited[i][j] = true;
					}
					if (map[i][j] == '*')
						devil.add(new Point(i, j));
				}
			}

			while (true) {
				int devilSize = devil.size();
				while (--devilSize >= 0) {
					bfsDevil(devil.poll());
				}

				int sySize = SY.size();
				while (--sySize >= 0) {
					bfsSY(SY.poll());
				}

				res++;

				if (isArrived || SY.isEmpty())
					break;
			}

			if (!isArrived) {
				answer.append('#').append(t).append(" GAME OVER\n");
				continue;
			}
			answer.append('#').append(t).append(' ').append(res).append('\n');
		} // end test-case

		System.out.println(answer);
	} // end main

	private static void bfsSY(Point cur) {
		for (int d = 0; d < delta.length; d++) {
			int curX = cur.x + delta[d][0];
			int curY = cur.y + delta[d][1];

			if (isOut(curX, curY) || map[curX][curY] == '*' || visited[curX][curY]) continue;

			if (map[curX][curY] == 'D') {
				isArrived = true;
				return;
			}

			SY.add(new Point(curX, curY));
			visited[curX][curY] = true;
		}
	}

	private static void bfsDevil(Point cur) {
		for (int d = 0; d < delta.length; d++) {
			int curX = cur.x + delta[d][0];
			int curY = cur.y + delta[d][1];

			if (isOut(curX, curY) || map[curX][curY] == 'D') continue;

			if (map[curX][curY] == '.' || map[curX][curY] == 'S') {
				map[curX][curY] = '*';
				devil.add(new Point(curX, curY));
			}
		}
	}

	private static boolean isOut(int curX, int curY) {
		if (curX < 0 || curX >= N || curY < 0 || curY >= M || map[curX][curY] == 'X')
			return true;
		return false;
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
