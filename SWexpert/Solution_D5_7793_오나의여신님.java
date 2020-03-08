import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;

	public Point(int i, int j) {
		this.x = i;
		this.y = j;
	}
}

/**
 * @author soohyun
 * 메모리 : 26,448 kb
 * 시간 : 123 ms
 * 코드길이 : 3,198 B
 */

public class Solution_D5_7793_오나의여신님 {

	static int N, M, cnt;
	static boolean isArrived;
	static char[][] map;
	static boolean[][] visited;
	static Queue<Point> SY;
	static Queue<Point> devil;
	static int[] deltaX = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int[] deltaY = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			answer.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new char[N][M];
			visited = new boolean[N][M];
			SY = new LinkedList<>();
			devil = new LinkedList<>();
			cnt = 0;
			isArrived = false;

			for (int i = 0; i < N; i++) {
				String tmp = in.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = tmp.charAt(j);
					if (map[i][j] == 'S')
						SY.add(new Point(i, j));
					if (map[i][j] == '*')
						devil.add(new Point(i, j));
				}
			} // end input

			while (true) {
				int sizeD = devil.size();
				while (--sizeD >= 0) {
					Point curD = devil.poll();
					bfsDevil(curD.x, curD.y);
				}

				int size = SY.size();
				while (--size >= 0) {
					Point cur = SY.poll();
					bfsSY(cur.x, cur.y);
				}
				
				cnt++;
				
				if (SY.isEmpty() || isArrived)
					break;

			}

			if (!isArrived) {
				answer.append("GAME OVER\n");
				continue;
			}
			answer.append(cnt).append("\n");
		} // end test-case

		System.out.print(answer);
		in.close();
	} // end main

	private static void bfsSY(int x, int y) {
		for (int n = 0; n < deltaX.length; n++) {
			int currentX = x + deltaX[n];
			int currentY = y + deltaY[n];

			if (currentX < 0 || currentX >= N || currentY < 0 || currentY >= M || map[currentX][currentY] == 'X'
					|| visited[currentX][currentY])
				continue;

			if (map[currentX][currentY] == 'D') {
				isArrived = true;
				return;
			}

			if (map[currentX][currentY] == '.' && !visited[currentX][currentY]) {
				SY.add(new Point(currentX, currentY));
				visited[currentX][currentY] = true;
				map[currentX][currentY] = 'S';
				visited[x][y] = true;
			}
		} // end delta
	}

	private static void bfsDevil(int x, int y) {
		for (int n = 0; n < deltaX.length; n++) {
			int currentX = x + deltaX[n];
			int currentY = y + deltaY[n];

			if (currentX < 0 || currentX >= N || currentY < 0 || currentY >= M || map[currentX][currentY] == 'X')
				continue;

			if (map[currentX][currentY] == '.' || map[currentX][currentY] == 'S') {
				devil.add(new Point(currentX, currentY));
				map[currentX][currentY] = '*';
			}
		}
	}
}
