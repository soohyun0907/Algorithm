import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 28,256 KB
 * 시간 : 216 ms
 * 코드길이 : 2,871 B
 * 소요시간 : 50M
 */

public class Main_B_15683_감시 {

	static int N, M, min;
	static int[][] office;
	static ArrayList<Point> CCTV;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		office = new int[N][M];
		CCTV = new ArrayList<Point>();
		min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
				if (0 < office[i][j] && office[i][j] < 6) {
					CCTV.add(new Point(i, j, office[i][j]));
				}
			}
		} // end input
		dfs(0, office);
		System.out.println(min);
	} // end main

	private static void dfs(int index, int[][] map) {
		if (index == CCTV.size()) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 0)
						cnt++;
				}
			}

			min = Math.min(cnt, min);
			return;
		}

		int[][] temp = new int[N][M];
		Point cur = CCTV.get(index);

		switch (cur.type) {
		case 1:
			for (int d = 0; d < delta.length; d++) {
				copyMap(temp, map);
				cctv(cur.x, cur.y, d, temp);
				dfs(index + 1, temp);
			}
			break;
		case 2:
			for (int d = 0; d < 2; d++) {
				copyMap(temp, map);
				cctv(cur.x, cur.y, d, temp);
				cctv(cur.x, cur.y, d + 2, temp);
				dfs(index + 1, temp);
			}
			break;
		case 3:
			for (int d = 0; d < delta.length; d++) {
				copyMap(temp, map);
				cctv(cur.x, cur.y, d, temp);
				cctv(cur.x, cur.y, (d + 1) % 4, temp);
				dfs(index + 1, temp);
			}
			break;
		case 4:
			for (int d = 0; d < delta.length; d++) {
				copyMap(temp, map);
				cctv(cur.x, cur.y, d, temp);
				cctv(cur.x, cur.y, (d + 1) % 4, temp);
				cctv(cur.x, cur.y, (d + 2) % 4, temp);
				dfs(index + 1, temp);
			}
			break;
		case 5:
			copyMap(temp, map);
			for (int d = 0; d < delta.length; d++) {
				cctv(cur.x, cur.y, d, temp);
			}
			dfs(index + 1, temp);
			break;
		}
	}

	private static void cctv(int x, int y, int d, int[][] map) {
		while (true) {
			x += delta[d][0];
			y += delta[d][1];

			if (x < 0 || x >= N || y < 0 || y >= M || map[x][y] == 6)
				return;

			if (map[x][y] == 0)
				map[x][y] = 9;
		}
	}

	private static void copyMap(int[][] temp, int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
	}

	static class Point {
		int x, y, type;

		public Point(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}

	}
}