import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,076 KB
 * 실행시간 : 80 ms
 * 코드길이 : 971 B
 * 소요시간 : 20 M
 */

public class Main_B_2606_바이러스_dfs {

	static int N, cnt;
	static int[][] comp;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		comp = new int[N + 1][N + 1];
		visited = new boolean[N + 1];

		for (int m = 0; m < M; m++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			comp[i][j] = comp[j][i] = 1;
		}

		dfs(1);
		System.out.print(cnt);
		in.close();
	}

	private static void dfs(int v) {
		visited[v] = true;

		for (int j = 1; j < N + 1; j++) {
			if (comp[v][j] > 0 && !visited[j]) {
				cnt++;
				dfs(j);
			}
		}
	}
}
