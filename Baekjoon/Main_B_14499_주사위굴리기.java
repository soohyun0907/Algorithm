import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,316 KB
 * 시간 : 96 ms
 * 코드길이 : 2,242 B
 * 소요시간 : 3H
 */

public class Main_B_14499_주사위굴리기 {

	static int N, M, x, y, K, temp;
	static int[][] map;
	static int[] dice;
	static int[][] delta = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } }; // 0:동(우), 1:서(좌), 2:북(상), 3:남(하)

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dice = new int[7]; // 0, U, B, R, L, F, D

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int dir;
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < K; i++) {
			dir = Integer.parseInt(st.nextToken()) - 1;
			if (!isIn(x + delta[dir][0], y + delta[dir][1]))
				continue;
			x += delta[dir][0];
			y += delta[dir][1];
			answer.append(move(x, y, dir)).append('\n');
		}

		System.out.print(answer);
		in.close();
	} // end main

	private static int move(int x, int y, int dir) {
		if (dir == 0) { // 동(우)
			temp = dice[3];
			dice[3] = dice[1];
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = temp;
		} else if (dir == 1) { // 서(좌)
			temp = dice[4];
			dice[4] = dice[1];
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = temp;
		} else if (dir == 2) { // 북(상)
			temp = dice[2];
			dice[2] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = temp;
		} else if (dir == 3) { // 남(하)
			temp = dice[5];
			dice[5] = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = temp;
		}

		if (map[x][y] == 0) {
			map[x][y] = dice[6];
		} else {
			dice[6] = map[x][y];
			map[x][y] = 0;
		}

		return dice[1];
	}

	private static boolean isIn(int i, int j) {
		if (i < 0 || i >= N || j < 0 || j >= M)
			return false;
		return true;
	}
}
