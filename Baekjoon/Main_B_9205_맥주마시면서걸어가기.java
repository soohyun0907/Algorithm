import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 14,096 KB
 * 시간 : 116 ms
 * 코드길이 : 1,734 B
 * 소요시간 : 46m
 */

public class Main_B_9205_맥주마시면서걸어가기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(in.readLine());

			Point[] map = new Point[n + 2];
			Boolean[] visited = new Boolean[n + 2];
			for (int i = 0; i < n + 1; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				map[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			st = new StringTokenizer(in.readLine(), " ");
			map[n + 1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			// bfs
			Arrays.fill(visited, false);
			String res = "sad";
			Queue<Point> stores = new LinkedList<Point>();
			stores.offer(map[0]);
			visited[0] = true;
			Point cur;
			while (!stores.isEmpty()) {
				cur = stores.poll();

				if (cur == map[n + 1]) {
					res = "happy";
					break;
				}
				for (int i = 1; i < n + 2; i++) {
					int dis = Math.abs(cur.x - map[i].x) + Math.abs(cur.y - map[i].y);
					if (!visited[i] && dis < 1001) {
						visited[i] = true;
						stores.offer(map[i]);
					}
				}
			}
			answer.append(res).append('\n');
		} // end test-case
		System.out.println(answer);
	} // end main

	private static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
