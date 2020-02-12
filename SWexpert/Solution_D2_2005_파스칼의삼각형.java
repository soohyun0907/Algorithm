package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D2_2005_파스칼의삼각형 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder answer = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			answer.append("#").append(tc).append("\n");
			st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] pascal = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i >= j) {
						if (i > 1 && j > 0) {
							pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
							answer.append(pascal[i][j]).append(" ");
						} else {
							pascal[i][j] = 1;
							answer.append(pascal[i][j]).append(" ");
						}
					}
				}
				answer.append("\n");
			}
		}

		System.out.println(answer);
		in.close();
	}

}
