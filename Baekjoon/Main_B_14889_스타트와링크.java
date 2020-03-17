import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 17,656 KB
 * 실행시간 : 224 ms
 * 코드길이 : 1,400 B
 */

public class Main_B_14889_스타트와링크 {

	static int N, min;
	static int[][] team;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		team = new int[N][N];
		visited = new boolean[N];
		min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				team[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		permutation(0, 0);
		System.out.println(min);
		in.close();
	}

	private static void permutation(int index, int count) {
		if (count == N / 2) {
			findMin();
			return;
		}

		if (min == 0 || index == N)
			return;

		visited[index] = true;
		permutation(index + 1, count + 1);
		visited[index] = false;
		permutation(index + 1, count);
	}

	private static void findMin() {
		int start = 0, link = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (visited[i] && visited[j])
					start += team[i][j] + team[j][i];
				else if (!visited[i] && !visited[j])
					link += team[i][j] + team[j][i];
			}
		}

		int tmp = Math.abs(start - link);
		if (tmp < min)
			min = tmp;
	}

}
