import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 29,576 kb 
 * 실행시간 : 228 ms 
 * 코드길이 : 1,665 B
 */

public class Solution_4012_요리사_2차 {

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
			food = new int[N][N];
			visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					food[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			permutation(0, 0);
			answer.append(min).append('\n');
		} // end test-case

		System.out.print(answer);
		in.close();
	}

	private static void permutation(int index, int count) {
		if (count == N / 2) {
			cook();
			return;
		}

		if (index == N || min == 0)
			return;

		visited[index] = true;
		permutation(index + 1, count + 1);
		visited[index] = false;
		permutation(index + 1, count);
	}

	private static void cook() {
		int sumA = 0, sumB = 0;
		int tmp = 0;
		for (int a = 0; a < N - 1; a++) {
			for (int b = a + 1; b < N; b++) {
				if (visited[a] && visited[b])
					sumA += food[a][b] + food[b][a];
				else if (!visited[a] && !visited[b])
					sumB += food[a][b] + food[b][a];
			}
		}

		tmp = Math.abs(sumA - sumB);
		if (tmp < min)
			min = tmp;
	}
}
