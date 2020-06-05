
public class Solution_순위 {

	public static void main(String[] args) {
		int[][] results = { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } };
		System.out.println(solution(5, results));
	}

	public static int solution(int n, int[][] results) {
		int answer = 0;
		int INF = 12345678;
		int[][] player = new int[n + 1][n + 1];
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				player[i][j] = (i == j) ? 0 : INF;
			}
		}

		for (int i = 0; i < results.length; i++) {
			int a = results[i][0];
			int b = results[i][1];
			player[a][b] = 1;
		}

		for (int k = 1; k < n + 1; k++) {
			for (int i = 1; i < n + 1; i++) {
				if (k == i) continue;
				for (int j = 1; j < n + 1; j++) {
					if (k == j || i == j) continue;
					if (player[i][j] > player[i][k] + player[k][j])
						player[i][j] = player[i][k] + player[k][j];
				}
			}
		}

		for (int i = 1; i < n + 1; i++) {
			boolean flag = true;
			for (int j = 1; j < n + 1; j++) {
				if (player[i][j] == INF && player[j][i] == INF) {
					flag = false;
					break;
				}
			}
			if (flag)
				answer++;
		}
		return answer;
	}
}
