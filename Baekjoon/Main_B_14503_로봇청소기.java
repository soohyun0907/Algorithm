import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,408 KB
 * 시간 : 84 ms
 * 코드길이 : 2,176 B
 * 소요시간 : 2H
 */

public class Main_B_14503_로봇청소기 {

	static int N, M, r, c, d;
	static int[][] map;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 북/상(0), 동/우(1), 남/하(2), 서/좌(3)

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		st = new StringTokenizer(in.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input

		bfs();
	}

	private static void bfs() {
		Queue<Robot> queue = new LinkedList<Robot>();
		map[r][c] = 9; // 초기위치 청소
		queue.add(new Robot(r, c, d));

		int curD, curX, curY, rotate;
		while (!queue.isEmpty()) {
			Robot cur = queue.poll();
			curD = cur.curD;
			rotate = 0;
			while (rotate++ < 4) {
				curD = (curD + 3) % 4;
				curX = cur.x + delta[curD][0];
				curY = cur.y + delta[curD][1];

				if (map[curX][curY] == 0) {
					map[curX][curY] = 9;
					queue.add(new Robot(curX, curY, curD));
					break;
				}
			}

			if (queue.isEmpty()) {
				curX = cur.x - delta[cur.curD][0];
				curY = cur.y - delta[cur.curD][1];

				if (map[curX][curY] != 1) {
					queue.add(new Robot(curX, curY, cur.curD));
				} else { // 벽일 경우
					break;
				}
			}

		}

		count();
	}

	private static void count() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 9) {
					cnt++;
				}
			}
		}

		System.out.println(cnt);
	}

	private static class Robot {
		int x;
		int y;
		int curD;

		public Robot(int x, int y, int curD) {
			this.x = x;
			this.y = y;
			this.curD = curD;
		}

	}

}
