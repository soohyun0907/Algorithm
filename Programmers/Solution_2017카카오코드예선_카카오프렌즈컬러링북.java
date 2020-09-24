import java.util.LinkedList;
import java.util.Queue;

public class Solution_2017카카오코드예선_카카오프렌즈컬러링북 {

	public static void main(String[] args) {
		int[] answer = solution(6, 4, new int[][] { { 1, 1, 1, 0 }, { 1, 2, 2, 0 }, { 1, 0, 0, 1 }, { 0, 0, 0, 1 },
			{ 0, 0, 0, 3 }, { 0, 0, 0, 3 } });

		for (int i = 0; i < answer.length; i++) {
			System.out.print(answer[i] + " ");
		}
	}

	static boolean[][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;

		int[] answer = new int[2];
		visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && picture[i][j] > 0) {
					int size = bfs(i, j, m, n, picture);
					numberOfArea++;
					maxSizeOfOneArea = Math.max(size, maxSizeOfOneArea);
				}
			}
		}
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}

	private static int bfs(int i, int j, int m, int n, int[][] picture) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(i, j));
		visited[i][j] = true;
		int maxSizeOfOneArea = 0;
		
		Point cur;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			maxSizeOfOneArea++;
			
			for (int d = 0; d < delta.length; d++) {
				int curX = cur.x + delta[d][0];
				int curY = cur.y + delta[d][1];

				if (curX < 0 || curX >= m || curY < 0 || curY >= n 
						|| picture[curX][curY] != picture[cur.x][cur.y] || visited[curX][curY])
					continue;

				queue.offer(new Point(curX, curY));
				visited[curX][curY] = true;
			}
		}
		
		return maxSizeOfOneArea;
	}

	private static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
