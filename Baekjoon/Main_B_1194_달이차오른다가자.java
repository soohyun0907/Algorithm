import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 15,824 KB
 * 시간 : 116 ms
 * 코드길이 : 2,382 B
 * 소요시간 : 1H + 강의
 */

public class Main_B_1194_달이차오른다가자 {

	static int N, M;
	static char[][] maze;
	static boolean[][][] visited;
	static Queue<Info> queue;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new char[N][M];
		visited = new boolean[N][M][1 << 6]; // 키가 있을때와 없을때 나눠서 방문체크하기 위해 3차원 배열 사용
		queue = new LinkedList<Info>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			String tmp = st.nextToken();
			for (int j = 0; j < M; j++) {
				maze[i][j] = tmp.charAt(j);
				if (maze[i][j] == '0') {
					queue.offer(new Info(i, j, 0, 0)); // 민식이의 현재 위치
					maze[i][j] = '.';
					visited[i][j][0] = true;
				}
			}
		} // end input

		System.out.println(bfs());
		in.close();
	} // end main method

	private static int bfs() {
		while (!queue.isEmpty()) {
			Info cur = queue.poll();
			for (int n = 0; n < delta.length; n++) {
				int curX = cur.x + delta[n][0];
				int curY = cur.y + delta[n][1];
				int key = cur.flag;

				if (curX < 0 || curX >= N || curY < 0 || curY >= M || maze[curX][curY] == '#'
						|| visited[curX][curY][key])
					continue;

				if (maze[curX][curY] == '1')
					return cur.depth + 1;

				if (Character.isLowerCase(maze[curX][curY])) {
					key |= (1 << (maze[curX][curY] - 'a'));
				} else if (Character.isUpperCase(maze[curX][curY])) {
					if ((key & (1 << (maze[curX][curY] - 'a'))) == 0) { // 문인데 키가 없는 경우에는 무시하기
						continue;
					}
				}
				visited[curX][curY][key] = true;
				queue.offer(new Info(curX, curY, cur.depth + 1, key));
			}
		}
		return -1;
	}

	static class Info {
		int x;
		int y;
		int depth; // 이동횟수
		int flag; // 키를 가지고 있는지의 여부

		public Info(int x, int y, int depth, int flag) {
			super();
			this.x = x;
			this.y = y;
			this.depth = depth;
			this.flag = flag;
		}
	}
}
