package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution_D4_1258_행렬찾기 {

	static int T, N;
	static int nextX = 0, nextY = 0;
	static int[][] matrix;
	static boolean[][] visited;

	static int[][] subMatrix;
	static int subMatCount = 0;

	static int[] deltaX = { -1, 1, 0, 0 }; // 상하좌우
	static int[] deltaY = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		// 테스트 케이스의 개수 T
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder answer = new StringBuilder();

		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			subMatCount = 0;
			answer.append("#").append(tc).append(" ");

			// 행렬의 크기 N (N은 100이하)
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			if (N <= 100) {
				subMatrix = new int[20][3];
				matrix = new int[N + 1][N + 1];
				visited = new boolean[N + 1][N + 1];

				// 행렬 입력받기
				for (int i = 1; i <= N; i++) {
					st = new StringTokenizer(in.readLine(), " ");
					for (int j = 1; j <= N; j++) {
						matrix[i][j] = Integer.parseInt(st.nextToken());
					}
				}

				// 행렬에서 0으로 둘러쌓인 영역 찾기
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (matrix[i][j] != 0 && !visited[i][j]) {
							dfs(i, j);
							subMatrix[subMatCount][0] = subMatrix[subMatCount][0] - i + 1;
							subMatrix[subMatCount][1] = subMatrix[subMatCount][1] - j + 1;
							subMatrix[subMatCount][2] = subMatrix[subMatCount][0] * subMatrix[subMatCount][1];
							subMatCount++;
						}
					}
				}

				// 발견한 부분 행렬 크기가 작은 순으로 배열하기
				Arrays.sort(subMatrix, new Comparator<int[]>() {
					@Override
					public int compare(int[] o1, int[] o2) {
						int result = o1[2] - o2[2];
						if (result == 0) {
							result = o1[0] - o2[0];
						}
						return result;
					}
				});

				answer.append(subMatCount).append(" ");
				for (int i = 0; i < subMatrix.length; i++) {
					if (subMatrix[i][0] != 0)
						answer.append(subMatrix[i][0]).append(" ").append(subMatrix[i][1]).append(" ");
				}
				answer.append("\n");
			}
		}

		System.out.println(answer);
		in.close();
	}

	private static void dfs(int i, int j) {
		visited[i][j] = true;

		for (int n = 0; n < deltaX.length; n++) {
			nextX = i + deltaX[n];
			nextY = j + deltaY[n];

			if (nextX < 0 || nextX >= N + 1 || nextY < 0 || nextY >= N + 1) {
				continue;
			}

			if (matrix[nextX][nextY] != 0 && !visited[nextX][nextY]) {
				if (subMatrix[subMatCount][0] < nextX) {
					subMatrix[subMatCount][0] = nextX;
					subMatrix[subMatCount][1] = nextY;
				}
				if (subMatrix[subMatCount][1] < nextY) {
					subMatrix[subMatCount][0] = nextX;
					subMatrix[subMatCount][1] = nextY;
				}
				dfs(nextX, nextY);
			}
		}
		return;
	}

}
