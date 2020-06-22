import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 22,908 KB
 * 시간 : 316 ms
 * 코드길이 : 2,520 B
 * 소요시간 : 3H
 */

public class Main_B_12100_2048Easy {

	static int N, map[][], max;
	static boolean[][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		visited = new boolean[N][N];
		map = new int[N][N];
		max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input

		dfs(0);
		System.out.println(max);
	}

	private static void dfs(int cnt) {
		int[][] temp = new int[N][N];
		copyMap(temp, map);

		if (cnt == 5) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (max < map[i][j])
						max = map[i][j];
				}
			}
			return;
		}

		for (int i = 0; i < delta.length; i++) {
			moveDir(i);
			dfs(cnt + 1);
			copyMap(map, temp);
		}
	}

	private static void moveDir(int dir) {
		switch (dir) {
		case 0: // 상
			for (int j = 0; j < N; j++) {
				for (int i = 1; i < N; i++) {
					moveGame(i, j, 0);
				}
			}
			break;
		case 1: // 우
			for (int i = 0; i < N; i++) {
				for (int j = N - 2; j >= 0; j--) {
					moveGame(i, j, 1);
				}
			}
			break;
		case 2: // 하
			for (int j = 0; j < N; j++) {
				for (int i = N - 2; i >= 0; i--) {
					moveGame(i, j, 2);
				}
			}
			break;
		case 3: // 좌
			for (int i = 0; i < N; i++) {
				for (int j = 1; j < N; j++) {
					moveGame(i, j, 3);
				}
			}
			break;
		}

		for (boolean[] row : visited) {
			Arrays.fill(row, false);
		}
	}

	private static void moveGame(int x, int y, int d) {
		int curX = x + delta[d][0];
		int curY = y + delta[d][1];

		if (curX < 0 || curX >= N || curY < 0 || curY >= N || visited[x][y] || visited[curX][curY])
			return;

		if (map[x][y] != 0 && map[x][y] == map[curX][curY]) {
			map[curX][curY] = map[x][y] * 2;
			map[x][y] = 0;
			visited[curX][curY] = true;
		} else if (map[curX][curY] == 0) {
			map[curX][curY] = map[x][y];
			map[x][y] = 0;
		}

		moveGame(curX, curY, d);
	}

	private static void copyMap(int[][] temp, int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = map[i][j];
			}
		}
	}

}
