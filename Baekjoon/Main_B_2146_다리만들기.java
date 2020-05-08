import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 18,056 KB
 * 시간 : 184 ms
 * 코드길이 : 3,056 B
 */

public class Main_B_2146_다리만들기 {

	static int N, islandIdx, result;
	static int[][] map;
	static boolean[][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌
	static ArrayList<Point> points;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input
		// 섬끼리 숫자 라벨링 해주기 (BFS)
		islandIdx = 2;
		points = new ArrayList<Point>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					findIsland(i, j);
					islandIdx++;
				}
			}
		}

		// 섬끼리 연결하는 다리 찾기 (BFS)
		result = Integer.MAX_VALUE;
		for (Point p : points) {
			makeBridge(p);
		}
		System.out.println(result);
		in.close();
	}// end main

	// BFS
	private static void makeBridge(Point p) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(p);
		for (boolean[] row : visited) {
			Arrays.fill(row, false);
		}
		visited[p.i][p.j] = true;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			if (cur.d >= result) {
				break;
			}

			for (int n = 0; n < delta.length; n++) {
				int curX = cur.i + delta[n][0];
				int curY = cur.j + delta[n][1];

				if (isIn(curX, curY) && !visited[curX][curY]) {
					visited[curX][curY] = true;
					if (map[curX][curY] == cur.idx)
						continue;
					else if (map[curX][curY] == 0)
						queue.offer(new Point(curX, curY, cur.d + 1, cur.idx));
					else if (map[curX][curY] != cur.idx) {
						result = Math.min(result, cur.d);
						return;
					}
				}
			}
		}
	}

	// BFS
	private static void findIsland(int i, int j) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(i, j, 0, islandIdx));
		points.add(new Point(i, j, 0, islandIdx));
		map[i][j] = islandIdx;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int n = 0; n < delta.length; n++) {
				int curX = cur.i + delta[n][0];
				int curY = cur.j + delta[n][1];

				if (isIn(curX, curY) && map[curX][curY] == 1) {
					map[curX][curY] = islandIdx;
					queue.offer(new Point(curX, curY, 0, islandIdx));
					points.add(new Point(curX, curY, 0, islandIdx));
				}
			}
		}
	}

	private static boolean isIn(int curX, int curY) {
		return curX >= 0 && curX < N && curY >= 0 && curY < N;
	}

	static class Point {
		int i;
		int j;
		int d;
		int idx;

		public Point(int i, int j, int d, int idx) {
			super();
			this.i = i;
			this.j = j;
			this.d = d;
			this.idx = idx;
		}
	}
}
