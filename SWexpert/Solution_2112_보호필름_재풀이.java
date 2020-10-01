import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 22,400 KB
 * 실행시간 : 410 ms
 * 코드길이 : 2,126 B
 */

public class Solution_2112_보호필름_재풀이 {

	static int D, W, K, min;
	static int[][] film;
	static int[] list; // 0: 약품을 투여하지 않은 경우 1: a약품을 투여한 경우 2: b약품을 투여한 경우

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(in.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			film = new int[D][W];
			list = new int[D];
			min = Integer.MAX_VALUE;

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dfs(0, 0);

			answer.append('#').append(tc + 1).append(' ').append(min).append('\n');
		} // end test-case
		System.out.print(answer);
		in.close();
	} // end main

	private static void dfs(int row, int count) {
		if (row == D) {
			// 약품을 투여해서 합격기준(K)를 통과한 경우
			if (check()) {
				min = Math.min(min, count);
			}
			return;
		}

		if (count >= min)
			return;

		// 부분집합
		// 투약하지 않았을 때
		list[row] = 0;
		dfs(row + 1, count);

		// a약품을 투여했을 때
		list[row] = 1;
		dfs(row + 1, count + 1);

		// b약품을 투여했을 때
		list[row] = 2;
		dfs(row + 1, count + 1);
	}

	private static boolean check() {
		int count, cur, next;

		for (int i = 0; i < W; i++) {
			count = 1;
			for (int j = 0; j < D - 1; j++) {
				cur = film[j][i];
				next = film[j + 1][i];
				if (list[j] > 0) {
					cur = list[j] - 1;
				}
				if (list[j + 1] > 0) {
					next = list[j + 1] - 1;
				}
				if (cur == next) {
					count++;
					if (count >= K)
						break;
				} else {
					count = 1;
				}
			}
			if (count < K)
				return false;
		}
		return true;
	}

}
