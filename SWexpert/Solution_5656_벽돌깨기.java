import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 96,740 kb
 * 시간 : 281 ms
 * 코드길이 : 2,800 B
 */

public class Solution_5656_벽돌깨기 {

	static int W, H, N, min;
	static int[][] bricks;
	static int[][] delta = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상, 좌, 하, 우

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			answer.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			bricks = new int[H][W];

			for (int h = 0; h < H; h++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int w = 0; w < W; w++) {
					bricks[h][w] = Integer.parseInt(st.nextToken());
				}

			} // end input

			permutation(0, bricks);
			answer.append(min).append("\n");
		} // end test-case

		System.out.println(answer);
		in.close();
	}

	private static void permutation(int index, int[][] b) {
		if (index == N) {
			min = Math.min(min, countBricks(b));
			return;
		}

		for (int i = 0; i < W; i++) {
			int[][] tmp = copy(b);
			tmp = searchBricks(i, tmp);
			permutation(index + 1, tmp);
		}
	}

	private static int countBricks(int[][] tmp) {
		int num = 0;

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (tmp[i][j] > 0)
					num++;
			}
		}

		return num;
	}

	private static int[][] searchBricks(int w, int[][] b) {
		for (int h = 0; h < H; h++) {
			if (b[h][w] > 0) {
				breakBricks(h, w, b);
				moveBricks(b);
				return b;
			}
		}
		return b;
	}

	private static void moveBricks(int[][] cb) {
		for (int i = 0; i < W; i++) {
			int bj = 0;
			boolean flag = false;
			for (int j = H - 1; j > -1; j--) {
				if (cb[j][i] == 0 && !flag) {
					bj = j;
					flag = true;
				}
				if (cb[j][i] > 0 && flag) {
					cb[bj][i] = cb[j][i];
					cb[j][i] = 0;
					bj--;
				}
			}
		}
	}

	private static void breakBricks(int h, int w, int[][] cb) {
		int size = cb[h][w];
		cb[h][w] = 0;

		if (size == 1)
			return;

		for (int n = 0; n < delta.length; n++) {
			for (int s = 1; s < size; s++) {
				int curX = h + delta[n][0] * s;
				int curY = w + delta[n][1] * s;

				if (curX < 0 || curX >= H || curY < 0 || curY >= W || cb[curX][curY] == 0)
					continue;

				breakBricks(curX, curY, cb);
			}
		}
	}

	private static int[][] copy(int[][] b) {
		int[][] matrix = new int[H][W];

		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				matrix[h][w] = b[h][w];
			}
		}
		return matrix;
	}
}
