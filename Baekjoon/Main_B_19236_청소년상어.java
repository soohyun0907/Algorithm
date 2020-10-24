import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * @author soohyun
 * 메모리 : 13,088 KB
 * 시간 : 84 ms
 * 코드길이 : 2,999B
 * 소요시간 : 4H
 */

public class Main_B_19236_청소년상어 {

	static int N = 4, answer = 0;
	static int[][] map = new int[N][N];
	static Fish[] fishes = new Fish[N * N + 1];
	static boolean[] isVisited = new boolean[N * N + 1];
	static int[][] delta = { { 0, 0 }, { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 },
			{ -1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				fishes[num] = new Fish(i, j, dir);
			}
		} // end input

		int num = map[0][0];
		int dir = fishes[num].dir;
		isVisited[num] = true;
		map[0][0] = -1;
		moveShark(0, 0, num, dir);

		System.out.println(answer);
	}

	private static void moveShark(int x, int y, int sum, int dir) {
		// 배열 복사
		int[][] tmp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmp[i][j] = map[i][j];
			}
		}

		Fish[] tmpFish = new Fish[N * N + 1];
		for (int i = 1; i <= N * N; i++) {
			tmpFish[i] = fishes[i];
		}

		// 물고기 이동
		moveFishes();

		// 상어 이동
		int curX, curY;
		for (int i = 1; i <= N; i++) {
			curX = x + delta[dir][0] * i;
			curY = y + delta[dir][1] * i;

			if (curX < 0 || curX >= N || curY < 0 || curY >= N || map[curX][curY] == 0)
				continue;

			int numF = map[curX][curY];
			isVisited[numF] = true;
			map[curX][curY] = -1;
			map[x][y] = 0;
			moveShark(curX, curY, sum + numF, fishes[numF].dir);
			isVisited[numF] = false;
			map[x][y] = -1;
			map[curX][curY] = numF;
		}

		// 배열 복구
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = tmp[i][j];
			}
		}

		for (int i = 0; i <= N * N; i++) {
			fishes[i] = tmpFish[i];
		}

		answer = Math.max(answer, sum);
	}

	private static void moveFishes() {
		int curX, curY, dir;

		for (int i = 1; i < fishes.length; i++) {
			if (isVisited[i])
				continue;
			Fish cur = fishes[i];
			dir = cur.dir;
			for (int j = 0; j < delta.length; j++) {
				curX = fishes[i].x + delta[dir][0];
				curY = fishes[i].y + delta[dir][1];

				if (curX < 0 || curX >= N || curY < 0 || curY >= N || map[curX][curY] == -1) {
					dir = (dir + 1) % 9;
					if (dir == 0)
						dir += 1;
					continue;
				}

				int temp = map[curX][curY];
				map[curX][curY] = map[cur.x][cur.y];
				map[cur.x][cur.y] = temp;

				fishes[i] = new Fish(curX, curY, dir);
				if (temp != 0)
					fishes[temp] = new Fish(cur.x, cur.y, fishes[temp].dir);
				break;
			}
		}
	}

	private static class Fish {
		int x;
		int y;
		int dir;

		public Fish(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

}
