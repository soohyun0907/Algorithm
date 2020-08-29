import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 72,468 KB
 * 시간 : 556 ms
 * 코드길이 : 2,783 B
 * 소요시간 : 45m
 */

public class Main_B_4179_불 {

	static int R, C, answer;
	static char[][] map;
	static boolean[][] visited;
	static boolean isPossible;
	static Queue<Point> JH;
	static Queue<Point> Fire;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상, 우, 하, 좌

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		JH = new LinkedList<Point>();
		Fire = new LinkedList<Point>();
		answer = 0;
		isPossible = false;
		
		for (int i = 0; i < R; i++) {
			String tmp = in.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = tmp.charAt(j);

				if (map[i][j] == 'J') {
					JH.offer(new Point(i, j));
					visited[i][j] = true;
				} else if (map[i][j] == 'F')
					Fire.offer(new Point(i, j));
			}
		}

		while (true) {
			int size = Fire.size();
			while (--size >= 0) {
				bfsFire(Fire.poll());
			}

			size = JH.size();
			while (--size >= 0) {
				bfsJH(JH.poll());
			}
			
			answer++;
			
			if(JH.isEmpty() || isPossible) {
				break;
			}
		}
		
		if(!isPossible)
			System.out.println("IMPOSSIBLE");
		else
			System.out.println(answer);
	} // end main

	private static void bfsJH(Point cur) {
		if(isBound(cur.x, cur.y)) {
			isPossible = true;
			return;
		}
		
		for (int d = 0; d < delta.length; d++) {
			int curX = cur.x + delta[d][0];
			int curY = cur.y + delta[d][1];

			if (isOut(curX, curY) || map[curX][curY] == 'F' || visited[curX][curY]) continue;

			JH.offer(new Point(curX, curY));
			visited[curX][curY] = true;
		}
	}

	private static boolean isBound(int curX, int curY) {
		if ((curX == 0 || curX == R - 1) && (0 <= curY && curY < C))
			return true;
		else if ((0 <= curX && curX < R) && (curY == 0 || curY == C - 1))
			return true;
		else
			return false;
	}

	private static void bfsFire(Point cur) {
		for (int d = 0; d < delta.length; d++) {
			int curX = cur.x + delta[d][0];
			int curY = cur.y + delta[d][1];

			if (isOut(curX, curY)) continue;

			if (map[curX][curY] == 'J' || map[curX][curY] == '.') {
				Fire.offer(new Point(curX, curY));
				map[curX][curY] = 'F';
			}
		}
	}

	private static boolean isOut(int curX, int curY) {
		if (curX < 0 || curX >= R || curY < 0 || curY >= C || map[curX][curY] == '#')
			return true;
		return false;
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
