package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_4408_자기방으로돌아가기 {

	static int T, N, start, dest;
	static int[] corridor = new int[201];

	public static void main(String[] args) throws IOException {
		// 테스트케이스의 수 T
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder answer = new StringBuilder();

		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			Arrays.fill(corridor, 0);
			answer.append("#").append(tc).append(" ");
			// 돌아가야할 학생들의 수 N
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());

			// 현재방, 돌아가야할 방 2개의 수를 N줄로 입력받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				start = Integer.parseInt(st.nextToken());
				dest = Integer.parseInt(st.nextToken());
				if (start < dest) {
					if (start % 2 == 1 && dest % 2 == 1) {
						for (int j = start / 2 + 1; j <= dest / 2 + 1; j++) {
							corridor[j]++;
						}
					} else if (start % 2 == 1 && dest % 2 == 0) {
						for (int j = start / 2 + 1; j <= dest / 2; j++) {
							corridor[j]++;
						}
					} else if (start % 2 == 0 && dest % 2 == 1) {
						for (int j = start / 2; j <= dest / 2 + 1; j++) {
							corridor[j]++;
						}
					} else {
						for (int j = start / 2; j <= dest / 2; j++) {
							corridor[j]++;
						}
					}
				} else {
					int temp = start;
					start = dest;
					dest = temp;
					if (start % 2 == 1 && dest % 2 == 1) {
						for (int j = start / 2 + 1; j <= dest / 2 + 1; j++) {
							corridor[j]++;
						}
					} else if (start % 2 == 1 && dest % 2 == 0) {
						for (int j = start / 2 + 1; j <= dest / 2; j++) {
							corridor[j]++;
						}
					} else if (start % 2 == 0 && dest % 2 == 1) {
						for (int j = start / 2; j <= dest / 2 + 1; j++) {
							corridor[j]++;
						}
					} else {
						for (int j = start / 2; j <= dest / 2; j++) {
							corridor[j]++;
						}
					}
				}
			}

			Arrays.sort(corridor);
			answer.append(corridor[200]).append("\n");
		}

		System.out.println(answer);
	}

}
