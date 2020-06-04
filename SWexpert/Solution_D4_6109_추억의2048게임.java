import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 29,760 KB
 * 실행시간 : 157ms
 * 코드길이 : 2,338 B
 * 소요시간 : 2H 이상
 */

public class Solution_D4_6109_추억의2048게임 {

	static int N;
	static int[][] game;
	static boolean[][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상, 우, 하, 좌

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc < T + 1; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			game = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					game[i][j] = Integer.parseInt(st.nextToken());
				}
			} // end input

			if (dir.equals("up")) { // 상
				for (int j = 0; j < N; j++) {
					for (int i = 1; i < N; i++) {
						moveGame(i, j, 0);
					}
				}
			} else if (dir.equals("right")) { // 우
				for (int i = 0; i < N; i++) {
					for (int j = N - 2; j >= 0; j--) {
						moveGame(i, j, 1);
					}
				}
			} else if (dir.equals("down")) { // 하
				for (int j = 0; j < N; j++) {
					for (int i = N - 2; i >= 0; i--) {
						moveGame(i, j, 2);
					}
				}
			} else { // 좌
				for (int i = 0; i < N; i++) {
					for (int j = 1; j < N; j++) {
						moveGame(i, j, 3);
					}
				}
			}

			answer.append('#').append(tc).append('\n');
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					answer.append(game[i][j]).append(' ');
				}
				answer.append('\n');
			}
		} // end test-case

		System.out.println(answer);
		in.close();
	} // end main

	private static void moveGame(int x, int y, int d) {
		int curX = x + delta[d][0];
		int curY = y + delta[d][1];

		if (curX < 0 || curX >= N || curY < 0 || curY >= N || visited[x][y] || visited[curX][curY])
			return;

		if (game[x][y] != 0 && game[x][y] == game[curX][curY]) {
			game[curX][curY] = game[x][y] * 2;
			game[x][y] = 0;
			visited[curX][curY] = true;
		} else if (game[curX][curY] == 0) {
			game[curX][curY] = game[x][y];
			game[x][y] = 0;
		}

		moveGame(curX, curY, d);
	}

}
