import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 내가 처음 푼 방법
 * 메모리 : 20,392 KB
 * 실행시간 : 2,049 ms
 * 코드길이 : 1,610 B
 * 소요시간 : 1H
 * 
 * 교수님 코드 참고 (가지치기 전)
 * 메모리 : 19,940 KB
 * 실행시간 : 1,748 ms
 * 코드길이 : 1,469 B
 * 
 * 교수님 코드 참고 (가지치기 후)
 * 메모리 : 19,848 KB
 * 실행시간 : 114 ms
 * 코드길이 : 1,509 B
 */

public class Solution_D4_7699_수지의수지맞는여행_재풀이 {

	static int R, C, ans, cnt;
	static char[][] islands;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			islands = new char[R][C];
			ans = 0; cnt = 0;
			
			for (int i = 0; i < R; i++) {
				islands[i] = in.readLine().toCharArray();
			}

			dfs(0, 0, 1, (1 << islands[0][0] - 'A'));

			answer.append('#').append(tc).append(' ').append(ans).append('\n');
		} // end test-case

		System.out.println(answer);
	} // end main

	private static void dfs(int r, int c, int cnt, int flag) {
		
		// 가지치기
		if(ans == 26) return;
		ans = Math.max(cnt, ans);
		for (int d = 0; d < delta.length; d++) {
			int curX = r + delta[d][0];
			int curY = c + delta[d][1];

			if (curX < 0 || curX >= R || curY < 0 || curY >= C) continue;

			if ((flag & (1 << islands[curX][curY] - 'A')) == 0) {
				dfs(curX, curY, cnt + 1, (flag | (1 << islands[curX][curY] - 'A')));
			}
		}

	}

	/* BFS = 런타임 에러 발생! */
	/* private static int bfs() {
		int answer = 0;
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(0, 0, 1, (0 | (1 << islands[0][0] - 'A'))));

		Point cur;
		while (!queue.isEmpty()) {
			cur = queue.poll();

			for (int d = 0; d < delta.length; d++) {
				int curX = cur.x + delta[d][0];
				int curY = cur.y + delta[d][1];

				if (curX < 0 || curX >= R || curY < 0 || curY >= C) continue;

				if ((cur.flag & (1 << islands[curX][curY] - 'A')) == 0) {
					queue.offer(new Point(curX, curY, cur.cnt + 1, (cur.flag | (1 << islands[curX][curY] - 'A'))));
				}
			}

			if (answer < cur.cnt)
				answer = cur.cnt;
		}

		return answer;
	}

	private static class Point {
		int x;
		int y;
		int cnt;
		int flag;

		public Point(int x, int y, int cnt, int flag) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.flag = flag;
		}
	} */

}
