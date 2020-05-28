import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 26,332 KB
 * 실행시간 : 126 ms
 * 코드길이 : 3,594 B
 * 소요시간 : 1H 10M
 */

public class Solution_1953_탈주범검거 {

	static int N, M, R, C, L, ans;
	static Queue<Point> queue;
	static boolean[][] visited;
	static int[][] map;
	static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}}; // 상(0), 우(1), 하(2), 좌(3)
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			ans = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine()," ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // end input
			
			answer.append('#').append(tc).append(' ').append(bfs()).append('\n');
		} // end test-case
		
		System.out.print(answer);
		in.close();
	} // end main

	private static int bfs() {
		queue = new LinkedList<Point>();
		queue.add(new Point(R,C));
		visited[R][C] = true;
		
		int size, time = 0;
		while(!queue.isEmpty()) {
			size = queue.size();
			time++;
			while(--size >= 0) {
				connect(queue.poll());
				ans++;
			}
			
			if(time == L)
				break;
		}
		queue.clear();
		return ans;
	}

	private static void connect(Point cur) {
		switch (map[cur.x][cur.y]) {
		case 1: // 상하좌우 터널과 연결
			for (int d = 0; d < delta.length; d++) {
				find(cur,d);
			}
			break;
		case 2: // 상하 터널과 연결
			find(cur,0);
			find(cur,2);
			break;
		case 3: // 좌우 터널과 연결
			find(cur,1);
			find(cur,3);
			break;
		case 4: // 상우 터널과 연결
			find(cur,0);
			find(cur,1);
			break;
		case 5: // 하우 터널과 연결
			find(cur,2);
			find(cur,1);
			break;
		case 6: // 하좌 터널과 연결
			find(cur,2);
			find(cur,3);
			break;
		case 7: // 상좌 터널과 연결
			find(cur,0);
			find(cur,3);
			break;
		}
	}
	
	
	private static void find(Point cur, int d) {
		visited[cur.x][cur.y] = true;
		int curX, curY;
		curX = cur.x + delta[d][0];
		curY = cur.y + delta[d][1];
		if (curX >= 0 && curX < N && curY >= 0 && curY < M && !visited[curX][curY] && map[curX][curY] > 0) {
			switch (d) {
			case 0: // 상
				if(map[curX][curY] == 1 || map[curX][curY] == 2 || map[curX][curY] == 5 || map[curX][curY] == 6) {
					queue.add(new Point(curX, curY));
					visited[curX][curY] = true;
				}
				break;
			case 1: // 우
				if(map[curX][curY] == 1 || map[curX][curY] == 3 || map[curX][curY] == 6 || map[curX][curY] == 7) {
					queue.add(new Point(curX, curY));
					visited[curX][curY] = true;
				}
				break;
			case 2: // 하
				if(map[curX][curY] == 1 || map[curX][curY] == 2 || map[curX][curY] == 4 || map[curX][curY] == 7) {
					queue.add(new Point(curX, curY));
					visited[curX][curY] = true;
				}
				break;
			case 3: // 좌
				if(map[curX][curY] == 1 || map[curX][curY] == 3 || map[curX][curY] == 4 || map[curX][curY] == 5) {
					queue.add(new Point(curX, curY));
					visited[curX][curY] = true;
				}
				break;
			}
		}
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
