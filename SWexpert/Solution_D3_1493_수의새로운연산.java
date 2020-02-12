package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_1493_수의새로운연산 {

	static int SIZE = 285, p, q, px, py, qx, qy, sumx, sumy;
	static int[][] matrix = new int[SIZE + 1][SIZE + 1];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		matrix[0][1] = 1;
		for (int y = 1; y <= SIZE; y++) {
			matrix[y][1] = matrix[y - 1][1] + y - 1;
			int num = y + 1;
			for (int x = 2; x <= SIZE; x++) {
				matrix[y][x] = matrix[y][x - 1] + num;
				num++;
			}
		}

		for (int tc = 1; tc <= T; tc++) {
			answer.append("#").append(tc).append(" ");
			st = new StringTokenizer(in.readLine());
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());

			for (int i = 1; i < matrix.length; i++) {
				for (int j = 1; j < matrix.length; j++) {
					if (matrix[i][j] == p) {
						px = j;
						py = i;
					}
					if (matrix[i][j] == q) {
						qx = j;
						qy = i;
					}
				}
			}

			answer.append(matrix[py + qy][px + qx]).append("\n");
		}

		System.out.println(answer);
		in.close();
	}

}
