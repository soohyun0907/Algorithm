import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 15,012 KB
 * 시간 : 128 ms
 * 코드길이 : 1,750 B
 * 소요시간 : 50M
 */

public class Main_B_1261_알고스팟 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] maze = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		int[][] delta = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		String tmp;
		for (int i = 0; i < N; i++) {
			tmp = in.readLine();
			for (int j = 0; j < tmp.length(); j++) {
				maze[i][j] = tmp.charAt(j) - '0';
			}
		}

		int ans = 0, curX, curY;
		PriorityQueue<Point> queue = new PriorityQueue<Point>();
		queue.add(new Point(0, 0, 0));

		Point cur;
		while (!queue.isEmpty()) {
			cur = queue.poll();

			if (visited[cur.x][cur.y])
				continue;
			visited[cur.x][cur.y] = true;

			if (cur.x == N - 1 && cur.y == M - 1) {
				ans = cur.cnt;
				break;
			}

			for (int d = 0; d < delta.length; d++) {
				curX = cur.x + delta[d][0];
				curY = cur.y + delta[d][1];

				if (curX < 0 || curX >= N || curY < 0 || curY >= M || visited[curX][curY])
					continue;

				if (maze[curX][curY] > 0)
					queue.add(new Point(curX, curY, cur.cnt + 1));
				else
					queue.add(new Point(curX, curY, cur.cnt));
			}
		}

		System.out.println(ans);
	}

	static class Point implements Comparable<Point> {
		int x;
		int y;
		int cnt;

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			return this.cnt - o.cnt;
		}
	}
}
