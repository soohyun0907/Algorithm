import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 279,928 KB
 * 시간 : 664 ms
 * 코드길이 : 2,938 B
 * 소요시간 : 1H
 */

public class Main_B_16234_인구이동 {

	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상, 우, 하, 좌

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		ArrayList<ArrayList<Point>> total = new ArrayList<ArrayList<Point>>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input

		int cnt = 0;
		
		while (true) {
			// 1. 연합을 이루는 나라 구해서 하나로 묶어주기.
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					if (!visited[i][j]) {
						total.add(bfs(new Point(i, j)));
					}
				}
			}
			// 2. 인구이동.
			// 연합된 나라 수가 초기 나라의 수와 같다면 더 이상 인구이동 X
			int size = total.size();
			if (size == N * N) {
				break;
			} else {
				for (int i = 0; i < size; i++) {
					ArrayList<Point> cur = total.get(i);
					int sum = 0;
					int len = cur.size();
					if(len == 1)
						continue;
					for (int j = 0; j < len; j++) {
						sum += map[cur.get(j).x][cur.get(j).y];
					}
					for (int j = 0; j < len; j++) {
						map[cur.get(j).x][cur.get(j).y] = sum / len;
					}
				}
			}
			cnt++;
			// 3. 연합 해제 및 초기화 후 다시 1번으로 반복.
			total.clear();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited[i][j] = false;
				}
			}
		}
		System.out.println(cnt);
		in.close();
	} // end main

	private static ArrayList<Point> bfs(Point po) {
		ArrayList<Point> union = new ArrayList<>();
		Queue<Point> queue = new LinkedList<Point>();
		visited[po.x][po.y] = true;
		queue.offer(po);
		union.add(po);

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int n = 0; n < delta.length; n++) {
				int x = cur.x + delta[n][0];
				int y = cur.y + delta[n][1];

				if (x < 0 || x >= N || y < 0 || y >= N || visited[x][y])
					continue;

				int sub = Math.abs(map[cur.x][cur.y] - map[x][y]);
				if (sub >= L && sub <= R) {
					visited[x][y] = true;
					queue.offer(new Point(x, y));
					if (!union.contains(new Point(x, y)))
						union.add(new Point(x, y));
				}
			}
		}

		return union;
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
