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
 * 메모리 : 13,364 KB
 * 시간 : 84 ms
 * 코드길이 : 3,472 B
 * 소요시간 : 1H 30M
 */

public class Main_B_16236_아기상어 {

	static Shark shark;
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark = new Shark(i, j, 2);
				} else if (0 < map[i][j] && map[i][j] < 7) {
					cnt++;
				}
			}
		} // end input

		if (cnt == 0) {
			System.out.println("0");
		} else {
			System.out.println(bfs(cnt));
		}
	} // end main

	private static int bfs(int cnt) {
		Queue<Point> queue = new LinkedList<Point>();
		ArrayList<Point> fishes = new ArrayList<Point>();
		queue.add(new Point(shark.x, shark.y));
		visited[shark.x][shark.y] = true;

		int size = 0;
		int sec = 0, tempSec = 0;
		int eat = 0;
		while (!queue.isEmpty()) {
			size = queue.size();
			tempSec++;
			while (--size >= 0) {
				Point curShark = queue.poll();

				// 일단 사방탐색하며 이동 & 물고기 찾기
				for (int n = 0; n < delta.length; n++) {
					int curX = curShark.x + delta[n][0];
					int curY = curShark.y + delta[n][1];

					if (curX < 0 || curX >= N || curY < 0 || curY >= N || visited[curX][curY])
						continue;

					if (map[curX][curY] == 0 || map[curX][curY] == shark.size) {
						queue.add(new Point(curX, curY));
					} else if (0 < map[curX][curY] && map[curX][curY] < shark.size) {
						fishes.add(new Point(curX, curY));
					}
					visited[curX][curY] = true;
				} // end delta
			} // end second while

			if (!fishes.isEmpty()) {
				sec = tempSec;
				int curX = fishes.get(0).x;
				int curY = fishes.get(0).y;

				// 물고기가 여러마리라면
				if (fishes.size() > 1) {
					// 가장 위, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
					for (Point fish : fishes) {
						if (curX > fish.x) {
							curX = fish.x;
							curY = fish.y;
						} else if (curX == fish.x) {
							if (curY > fish.y) {
								curY = fish.y;
							}
						}
					}
				} // end if(fishes.size())

				map[shark.x][shark.y] = 0;
				map[curX][curY] = 9;
				eat++;
				cnt--;
				shark.x = curX;
				shark.y = curY;
				if (shark.size == eat) {
					shark.size++;
					eat = 0;
				}

				queue.clear();
				fishes.clear();
				if (cnt == 0)
					return sec;
				for (boolean[] visit : visited) {
					Arrays.fill(visit, false);
				}
				queue.add(new Point(shark.x, shark.y));
				visited[shark.x][shark.y] = true;
			} // end if (!fishes.isEmpty())
		} // end first while

		return sec;
	} // end bfs

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static class Shark {
		int x;
		int y;
		int size;

		public Shark(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}
}
