import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;

	public Point(int r, int c) {
		this.x = r;
		this.y = c;
	}
}

/**
 * @author soohyun 
 * 메모리 : 27,656 KB 
 * 실행 시간 : 2,336 ms 
 * 코드 길이 : 1,849 B
 */

public class Solution_D4_7699_수지의수지맞는여행 {

	static int R, C, cnt, tmp;
	static char[][] island;
	static boolean[][] visited;

	static int[] alpha = new int[26];
	static int[] deltaX = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int[] deltaY = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			answer.append("#").append(t).append(" ");
			cnt = 0;
			tmp = 0;
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			island = new char[R + 1][C + 1];
			visited = new boolean[R + 1][C + 1];

			for (int i = 1; i <= R; i++) {
				String tmp = in.readLine();
				for (int j = 1; j <= C; j++) {
					island[i][j] = tmp.charAt(j - 1);
				}
			}
			dfs(1, 1);
			answer.append(cnt).append("\n");
		}

		System.out.print(answer);
		in.close();
	}

	private static void dfs(int r, int c) {
		tmp++;
		visited[r][c] = true;
		alpha[island[r][c] - 65]++;

		for (int n = 0; n < deltaX.length; n++) {
			int currentR = r + deltaX[n];
			int currentC = c + deltaY[n];

			if (currentR <= 0 || currentR > R || currentC <= 0 || currentC > C)
				continue;

			if (!visited[currentR][currentC] && alpha[island[currentR][currentC] - 65] == 0) {
				dfs(currentR, currentC);
			}
		}

		if (tmp > cnt)
			cnt = tmp;

		// 초기 상태로 되돌리기
		visited[r][c] = false;
		alpha[island[r][c] - 65] = 0;
		tmp--;
	}

}
