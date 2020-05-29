import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 25,960 KB 
 * 실행시간 : 141 ms 
 * 코드길이 : 2,399 B
 */

public class Solution_1953_탈주범검거_수업_DFS {

	static int N, M, R, C, L, ans;
	static int[][] map;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상(0), 우(1), 하(2), 좌(3)
	static int[][] block_hole = { 
			{}, // 0번 블럭 없음
			{ 1, 1, 1, 1 }, 
			{ 1, 0, 1, 0 }, 
			{ 0, 1, 0, 1 }, 
			{ 1, 1, 0, 0 }, 
			{ 0, 1, 1, 0 }, 
			{ 0, 0, 1, 1 },
			{ 1, 0, 0, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // end input

			// dfs 사용시
			visited2 = new int[N][M];
			for (int[] row : visited2) {
				Arrays.fill(row, Integer.MAX_VALUE);
			}

			dfs(R, C, 1);
			ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited2[i][j] != Integer.MAX_VALUE)
						ans++;
				}
			}
			answer.append('#').append(tc).append(' ').append(ans).append('\n');
		} // end test-case

		System.out.print(answer);
		in.close();
	} // end main

	static int[][] visited2;

	private static void dfs(int i, int j, int time) {
		if (time == L + 1)
			return;

		visited2[i][j] = time;
		int block = map[i][j];

		for (int d = 0; d < 4; d++) {
			// 현재 서 있는 블럭에 방향 d가 뚫려있는지
			if (block_hole[block][d] == 1) {
				int ni = i + delta[d][0];
				int nj = j + delta[d][1];

				if (ni >= 0 && ni < N && nj >= 0 && nj < M && map[ni][nj] > 0 && (time < visited2[ni][nj])
						&& block_hole[map[ni][nj]][(d + 2) % 4] == 1) {
					dfs(ni, nj, time + 1);
				}
			}
		}
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
