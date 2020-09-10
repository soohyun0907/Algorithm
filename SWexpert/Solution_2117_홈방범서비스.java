import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 87,456 KB
 * 실행시간 : 408 ms
 * 코드길이 : 2,216 B
 * 소요시간 : 30m
 */

public class Solution_2117_홈방범서비스 {

	static int N, M, ans;
	static int[][] map;
	static boolean[][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 도시의 크기
			M = Integer.parseInt(st.nextToken()); // 하나의 집이 지불할 수 있는 비용

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}

			answer.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		
		System.out.println(answer);
	}

	private static void bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<Point>();
		visited = new boolean[N][N];
		queue.offer(new Point(i, j));
		visited[i][j] = true;
		int K = 1;
		int size, cnt = 0;
		Point cur;
		while (!queue.isEmpty()) {

			size = queue.size();
			while (--size >= 0) {
				cur = queue.poll();
				if (map[cur.x][cur.y] == 1)
					cnt++;

				for (int d = 0; d < delta.length; d++) {
					int curX = cur.x + delta[d][0];
					int curY = cur.y + delta[d][1];

					if (curX < 0 || curX >= N || curY < 0 || curY >= N || visited[curX][curY]) continue;

					queue.offer(new Point(curX, curY));
					visited[curX][curY] = true;

				} // end delta
			} // end while-size

			if (M * cnt >= (K * K + (K - 1) * (K - 1)))
				ans = Math.max(ans, cnt);
			K++;
		} // end while-queue
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
