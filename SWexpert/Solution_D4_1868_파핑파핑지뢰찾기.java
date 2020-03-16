import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author soohyun
 * 메모리 : 44,816 kb
 * 실행시간 : 221 ms
 * 코드길이 : 2,544 B
 */

public class Solution_D4_1868_파핑파핑지뢰찾기 {

	static int[] deltaX = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 상, 상우, 우, 하우, 하, 좌하, 좌, 좌상
	static int[] deltaY = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int N;
	static char[][] mine;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer.append('#').append(tc).append(' ');
			N = Integer.parseInt(in.readLine());
			mine = new char[N][N];
			visited = new boolean[N][N];

			int count = 0;
			for (int i = 0; i < N; i++) {
				String str = in.readLine();
				for (int j = 0; j < N; j++) {
					mine[i][j] = str.charAt(j);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (mine[i][j] == '.') {
						checkMine(i, j);
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (mine[i][j] == '0' && !visited[i][j]) {
						visited[i][j] = true;
						findMine(i, j);
						count++;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && mine[i][j] != '*') {
						count++;
					}
				}
			}

			answer.append(count).append('\n');
		} // end test-case

		System.out.print(answer);
		in.close();
	}

	private static void checkMine(int i, int j) {
		int count = 0;
		for (int n = 0; n < 8; n++) {
			int curX = i + deltaX[n];
			int curY = j + deltaY[n];

			if (curX < 0 || curX >= N || curY < 0 || curY >= N)
				continue;

			if (mine[curX][curY] == '*')
				count++;
		}

		mine[i][j] = (char) (count + 48);
	}

	// bfs
	private static void findMine(int i, int j) {
		Queue<Mine> queue = new LinkedList<>();
		queue.offer(new Mine(i, j));

		while (!queue.isEmpty()) {
			Mine cur = queue.poll();
			i = cur.i;
			j = cur.j;

			for (int n = 0; n < 8; n++) {
				int curX = i + deltaX[n];
				int curY = j + deltaY[n];

				if (curX < 0 || curX >= N || curY < 0 || curY >= N || visited[curX][curY])
					continue;

				if (mine[curX][curY] == '0')
					queue.offer(new Mine(curX, curY));

				visited[curX][curY] = true;
			}
		}
	} // end findMine Method

	static class Mine {
		int i;
		int j;

		public Mine(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

}
