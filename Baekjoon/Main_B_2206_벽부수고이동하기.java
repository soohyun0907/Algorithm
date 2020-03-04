import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class MapPoint {
	int n;
	int m;
	int flag; // 벽 부순 횟수

	public MapPoint(int n, int m, int flag) {
		this.n = n;
		this.m = m;
		this.flag = flag;
	}
}

public class Main_B_2206_벽부수고이동하기 {

	static int N, M;
	static int[][] map;
	static boolean[][][] visited;
	static int[] deltaX = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int[] deltaY = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1][2];

		for (int i = 1; i <= N; i++) {
			String tmp = in.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = tmp.charAt(j - 1) - 48;
			}
		}

		if (N == 1 && M == 1) {
			System.out.println("1");
			return;
		}

		System.out.println(bfs());

		in.close();
	}

	private static int bfs() {
		Queue<MapPoint> queue = new LinkedList<MapPoint>();
		int cnt = 0;
		visited[1][1][0] = true;
		queue.add(new MapPoint(1, 1, 0));

		while (!queue.isEmpty()) {
			int size = queue.size();

			while (size-- > 0) {
				MapPoint curP = queue.poll();
				int x = curP.n;
				int y = curP.m;
				int flag = curP.flag;

				for (int n = 0; n < deltaX.length; n++) {
					int currentX = x + deltaX[n];
					int currentY = y + deltaY[n];

					if (currentX <= 0 || currentX > N || currentY <= 0 || currentY > M)
						continue;

					if (currentX == N && currentY == M) {
						cnt++;
						return cnt + 1;
					}

					if (map[currentX][currentY] == 1 && flag == 0) {
						visited[currentX][currentY][flag + 1] = true;
						queue.add(new MapPoint(currentX, currentY, flag + 1));
					}

					if (map[currentX][currentY] == 0 && !visited[currentX][currentY][flag]) {
						visited[currentX][currentY][flag] = true;
						queue.add(new MapPoint(currentX, currentY, flag));
					}
				} // end delta
				// 큐에 추가된 것이 없다면 불가능한 상황이기 때문에 -1을 리턴
				if (queue.isEmpty())
					return -1;
			} // end 2nd while
			cnt++;
		} // end 1st while

		return cnt;
	}

}
