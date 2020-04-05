import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,732 KB
 * 실행시간 : 92 ms
 * 코드길이 : 1,790 B
 * 소요시간 : 30M
 */

public class Main_B_2178_미로탐색 {

	static int N, M;
	static int[][] maze;
	static boolean[][] visited;
	static int[] deltaX = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int[] deltaY = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];

		for (int i = 1; i < N + 1; i++) {
			String str = in.readLine();
			for (int j = 1; j < M + 1; j++) {
				maze[i][j] = str.charAt(j-1)-'0';
			}
		}

		System.out.print(bfs(1, 1));

		in.close();
	}

	private static int bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(x, y));
		visited[x][y] = true;
		int cnt = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (--size >= 0) {
				Point cur = queue.poll();
				int i = cur.x;
				int j = cur.y;

				if (i == N && j == M) {
					queue.clear();
					break;
				}

				for (int n = 0; n < deltaX.length; n++) {
					int curX = i + deltaX[n];
					int curY = j + deltaY[n];

					if (curX < 1 || curX > N || curY < 1 || curY > M)
						continue;

					if (maze[curX][curY] > 0 && !visited[curX][curY]) {
						visited[curX][curY] = true;
						queue.offer(new Point(curX, curY));
					}
				}
			}
			cnt++;
		}

		return cnt;
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
