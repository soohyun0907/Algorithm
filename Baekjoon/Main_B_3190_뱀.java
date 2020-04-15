import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,148 KB
 * 시간 : 80 ms
 * 코드길이 : 2,549 B
 * 소요시간 : 2H 50M
 */

public class Main_B_3190_뱀 {

	static int N, K, L;
	static int[][] board;
	static boolean[][] visited;
	static ArrayList<Point> snake;
	static Point[] info;
	static int[][] delta = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // 우, 하, 좌, 상

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine()); // 보드의 크기
		board = new int[N][N];
		visited = new boolean[N][N];
		K = Integer.parseInt(in.readLine()); // 사과의 개수
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			board[a][b] = 1;
		}
		L = Integer.parseInt(in.readLine());
		info = new Point[L];
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			int dir;
			if (c == 'L')
				dir = -1;
			else
				dir = 1;
			info[i] = new Point(x, 0, dir);
		} // end input

		System.out.println(moveSnake());
		in.close();
	}

	private static int moveSnake() {
		int time = 0;
		int cnt = 0;
		snake = new ArrayList<>();
		snake.add(new Point(0, 0, 0)); // 0행0열에서 우측방향을 보고 시작
		visited[0][0] = true;

		while (true) {
			Point head = snake.get(snake.size() - 1);
			int curX = head.x + delta[head.dir][0];
			int curY = head.y + delta[head.dir][1];

			// 벽에 부딪히는 경우, 자신의 몸에 부딪히는 경우
			if (curX < 0 || curX >= N || curY < 0 || curY >= N || visited[curX][curY])
				return time + 1;

			snake.add(new Point(curX, curY, head.dir));
			visited[curX][curY] = true;
			if (board[curX][curY] < 1) { // 사과가 없는 경우
				Point tail = snake.remove(0);
				visited[tail.x][tail.y] = false;
			} else // 사과가 있는 경우
				board[curX][curY] = 0;
			time++;

			if (cnt < L && info[cnt].x == time) {
				head.dir += info[cnt].dir;
				if (head.dir < 0)
					head.dir = 3;
				else if (head.dir > 3)
					head.dir = 0;

				snake.get(snake.size() - 1).dir = head.dir;
				cnt++;
			}
		}

	}

	static class Point {
		int x;
		int y;
		int dir;

		public Point(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

}
