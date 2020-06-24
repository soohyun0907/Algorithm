import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 280,380 KB
 * 시간 : 704 ms
 * 코드길이 : 2,765 B
 * 소요시간 : 50M
 */

public class Main_B_14502_연구소 {

	static int N, M, size = 3, max, cnt;
	static int[][] map, temp;
	static Point[] wall = new Point[size];
	static ArrayList<Point> virus;
	static ArrayList<Point> list; // 벽이 될 수 있는 빈칸 리스트
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상우하좌

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		temp = new int[N][M];
		virus = new ArrayList<Point>();
		list = new ArrayList<Point>();
		max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus.add(new Point(i, j));
				} else if (map[i][j] == 0) {
					list.add(new Point(i, j));
				}
			}
		} // end input

		makeWall(0, 0);
		System.out.println(max);
	} // end main

	private static void makeWall(int index, int flag) {
		if (index == size) {
			makeMap(map, temp);
			spreadVirus();
			cnt = countSafeArea();
			if (max < cnt)
				max = cnt;
			return;
		}

		for (int i = 0; i < list.size(); i++) {
			if ((flag & (1 << i)) == 0) {
				wall[index] = list.get(i);
				makeWall(index + 1, (flag | (1 << i)));
			}
		}
	}

	private static int countSafeArea() {
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (temp[i][j] == 0)
					cnt++;
			}
		}

		return cnt;
	}

	private static void spreadVirus() {
		Queue<Point> queue = new LinkedList<Point>();
		for (int i = 0; i < virus.size(); i++) {
			queue.offer(virus.get(i));
		}

		Point cur;
		int curX, curY;
		while (!queue.isEmpty()) {
			cur = queue.poll();

			for (int i = 0; i < delta.length; i++) {
				curX = cur.x + delta[i][0];
				curY = cur.y + delta[i][1];

				if (curX < 0 || curX >= N || curY < 0 || curY >= M) continue;

				if (temp[curX][curY] == 0) {
					temp[curX][curY] = 2;
					queue.offer(new Point(curX, curY));
				}
			}
		}
	} // end spreadVirus()

	private static void makeMap(int[][] map, int[][] temp) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < size; i++) {
			temp[wall[i].x][wall[i].y] = 1;
		}
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
