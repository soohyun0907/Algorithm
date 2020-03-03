import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Coord {
	int x;
	int y;
	int d; // 0:가로, 1:세로

	public Coord(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}

/**
 * @author soohyun
 * 메모리 : 100,344 kb
 * 실행시간 : 330 ms
 * 코드길이 : 3,230 B
 */

public class Solution_D4_8382_방향전환 {

	static int SX, SY, EX, EY, cnt;
	static boolean[][][] visited = new boolean[201][201][2];
	static int[] deltaX = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
	static int[] deltaY = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			cnt = 0;
			answer.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			SX = Integer.parseInt(st.nextToken());
			SY = Integer.parseInt(st.nextToken());
			EX = Integer.parseInt(st.nextToken());
			EY = Integer.parseInt(st.nextToken());

			if (SX == EX && SY == EY) {
				answer.append(0).append("\n");
				continue;
			}
			answer.append(bfs()).append("\n");

			for (int i = 0; i < 201; i++) {
				for (int j = 0; j < 201; j++) {
					visited[i][j][0] = false;
					visited[i][j][1] = false;
				}
			}
		}

		System.out.print(answer);
		in.close();
	}

	private static int bfs() {
		Queue<Coord> queue = new LinkedList<Coord>();
		queue.offer(new Coord(SX, SY, 0));
		queue.offer(new Coord(SX, SY, 1));
		visited[SX + 100][SY + 100][0] = true;
		visited[SX + 100][SY + 100][1] = true;

		while (!queue.isEmpty()) {
			int size = queue.size();

			while (size-- > 0) {
				Coord tmp = queue.poll();
				int x = tmp.x;
				int y = tmp.y;
				int direction = tmp.d;

				switch (direction) {
				case 0:
					// 가로로 이동한다.
					for (int n = 2; n < deltaX.length; n++) {
						int currentX = x + deltaX[n];
						int currentY = y + deltaY[n];

						if (!boundary(currentX, currentY))
							continue;

						if (currentX == EX && currentY == EY) {
							cnt++;
							return cnt;
						}

						if (!visited[currentX + 100][currentY + 100][0]) {
							visited[currentX + 100][currentY + 100][0] = true;
							// 다음엔 세로로 이동하도록 저장
							queue.offer(new Coord(currentX, currentY, 1));
						}
					}
					break;
				case 1:
					// 세로로 이동한다.
					for (int n = 0; n < 2; n++) {
						int currentX = x + deltaX[n];
						int currentY = y + deltaY[n];

						if (!boundary(currentX, currentY))
							continue;

						if (currentX == EX && currentY == EY) {
							cnt++;
							return cnt;
						}

						if (!visited[currentX + 100][currentY + 100][1]) {
							visited[currentX + 100][currentY + 100][1] = true;
							// 다음은 가로로 이동하도록 저장
							queue.offer(new Coord(currentX, currentY, 0));
						}
					}
					break;
				} // end switch
			} // end 2nd while
			cnt++;
		} // end 1st while

		return cnt;
	}

	private static boolean boundary(int currentX, int currentY) {
		if (currentX < -100 || currentX > 100 || currentY < -100 || currentY > 100)
			return false;

		return true;
	}
}
