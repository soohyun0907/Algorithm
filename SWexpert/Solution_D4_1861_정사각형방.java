package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_1861_정사각형방 {

	static int T, N, count = 1;
	static int[][] Rooms;

	static int[] maxRooms;

	static int[] deltaX = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] deltaY = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		// 테스트 케이스의 수 T
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder answer = new StringBuilder();

		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			answer.append("#").append(tc).append(" ");
			// 행렬의 크기 N
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			Rooms = new int[N][N];
			maxRooms = new int[N * N + 1];

			// 행렬의 값 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					Rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					count = 1;
					int tmp = searchRooms(i, j);
					maxRooms[Rooms[i][j]] = tmp;
					if (max < tmp) {
						max = tmp;
					}
				}
			}

			for (int i = 0; i < maxRooms.length; i++) {
				if (max == maxRooms[i]) {
					answer.append(i);
					break;
				}
			}

			answer.append(" ").append(max).append("\n");
		}

		System.out.println(answer);
		in.close();
	}

	private static int searchRooms(int i, int j) {

		for (int n = 0; n < deltaX.length; n++) {
			int nextX = i + deltaX[n];
			int nextY = j + deltaY[n];

			if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
				continue;
			}
			if (Rooms[i][j] + 1 == Rooms[nextX][nextY]) {
				count++;
				searchRooms(nextX, nextY);
			}
		}

		return count;
	}

}
