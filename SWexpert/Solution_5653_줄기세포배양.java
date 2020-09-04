import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 94,044 KB
 * 실행시간 : 345 ms
 * 코드길이 : 2,955 B
 * 소요시간 : 1H
 */

public class Solution_5653_줄기세포배양 {

	static int size = 400, N, M, K, res;
	static int[][] map;
	static List<ArrayList<Data>> cells;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();

		int TC = Integer.parseInt(in.readLine());
		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[size][size];
			cells = new ArrayList<>();
			res = 0;

			for (int i = 0; i < 10; i++) {
				cells.add(new ArrayList<Data>());
			}

			for (int i = size / 2; i < N + size / 2; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = size / 2; j < M + size / 2; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 0)
						cells.get(map[i][j] - 1).add(new Data(i, j, map[i][j], map[i][j], K, false));
				}
			}

			bfs();

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (map[i][j] > 0)
						res++;
				}
			}

			answer.append('#').append(t).append(' ').append(res).append('\n');
		} // end test-case

		System.out.println(answer);
	} // end main

	private static void bfs() {
		Queue<Data> queue = new LinkedList<Data>();

		for (int i = 9; i >= 0; i--) {
			if (cells.get(i).size() > 0) {
				for (Data data : cells.get(i)) {
					queue.offer(data);
				}
			}
		} // end for cells

		Data cur;
		while (!queue.isEmpty()) {
			cur = queue.poll();

			if (cur.life == 0 && cur.isActive) {
				map[cur.i][cur.j] = -1;
				continue;
			}

			if (cur.k == 0)
				continue;

			if (cur.life > 0) {
				queue.offer(new Data(cur.i, cur.j, cur.x, cur.life - 1, cur.k - 1, cur.isActive));
				continue;
			}

			queue.offer(new Data(cur.i, cur.j, cur.x, cur.x, cur.k, true));

			for (int d = 0; d < delta.length; d++) {
				int curX = cur.i + delta[d][0];
				int curY = cur.j + delta[d][1];

				if (map[curX][curY] == 0) {
					map[curX][curY] = cur.x;
					queue.offer(new Data(curX, curY, cur.x, cur.x, cur.k - 1, false));
				}
			}
		} // end while
	} // end bfs

	static class Data {
		int i;
		int j;
		int x; // 줄기세포 생명력

		int life; // 줄기세포 생명력
		int k; // 배양시간 K
		boolean isActive; // 활성상태 ture, 비활성상태 false

		public Data(int i, int j, int x, int life, int k, boolean isActive) {
			this.i = i;
			this.j = j;
			this.x = x;
			this.life = life;
			this.k = k;
			this.isActive = isActive;
		}
	}
}
