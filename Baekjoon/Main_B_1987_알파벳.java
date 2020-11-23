import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 15,232 KB
 * 시간 : 880 ms
 * 코드길이 : 1,158 B
 * 소요시간 : 15M
 */

public class Main_B_1987_알파벳 {

	static int R, C, answer;
	static char[][] board;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		answer = 0;

		for (int i = 0; i < R; i++) {
			board[i] = in.readLine().toCharArray();
		}

		dfs(0, 0, 1, (0 | (1 << board[0][0] - 'A')));

		System.out.println(answer);
	}

	private static void dfs(int x, int y, int cnt, int flag) {
		answer = Math.max(answer, cnt);

		for (int d = 0; d < delta.length; d++) {
			int curX = x + delta[d][0];
			int curY = y + delta[d][1];

			if (curX < 0 || curX >= R || curY < 0 || curY >= C) continue;

			if ((flag & (1 << board[curX][curY] - 'A')) == 0) {
				dfs(curX, curY, cnt + 1, (flag | (1 << board[curX][curY] - 'A')));
			}
		}

	}

}
