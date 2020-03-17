import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 33,832 kb
 * 실행시간 : 201 ms
 * 코드길이 : 1,970 B
 */

public class Solution_4012_요리사 {

	static int N;
	static int[][] food;
	static boolean[] visited;
	private static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer.append('#').append(tc).append(' ');
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(in.readLine());
			food = new int[N + 1][N + 1];
			visited = new boolean[N + 1];
			for (int i = 1; i < N + 1; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for (int j = 1; j < N + 1; j++) {
					food[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			permutation(0);
			answer.append(min).append('\n');
		} // end test-case

		System.out.print(answer);
		in.close();
	}

	private static void permutation(int index) {
		if (index == N / 2) {
			int i = index;
			for (int n = 1; n < N + 1; n++) {
				if (!visited[n]) {
					food[0][i] = n;
					i++;
				}
			}
			cook(food[0]);
			return;
		}

		for (int i = 1; i < N + 1; i++) {
			if (visited[i])
				continue;
			if (index != 0 && food[0][index - 1] > i)
				continue;

			food[0][index] = i;
			visited[i] = true;
			permutation(index + 1);
			visited[i] = false;
		}
	}

	private static void cook(int[] num) {
		int sumA = 0, sumB = 0;
		int tmp = 0;
		for (int a = 0; a < (N / 2) - 1; a++) {
			int i = num[a];
			for (int b = a + 1; b < N / 2; b++) {
				int j = num[b];
				sumA += food[i][j] + food[j][i];
			}
		}

		for (int a = N / 2; a < N - 1; a++) {
			int i = num[a];
			for (int b = a + 1; b < N; b++) {
				int j = num[b];
				sumB += food[i][j] + food[j][i];
			}
		}

		tmp = Math.abs(sumA - sumB);
		if (tmp < min)
			min = tmp;
	}
}
