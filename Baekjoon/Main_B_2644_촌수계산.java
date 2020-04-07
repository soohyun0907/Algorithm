import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 12,996 KB
 * 실행시간 : 76 ms
 * 코드길이 : 1,380 B
 * 소요시간 : 40M
 * 처음에 BFS로 시도했다가 메모리 초과가 나와서 DFS로 구현.
 */

public class Main_B_2644_촌수계산 {

	static int N, answer;
	static int[][] family;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		family = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		answer = Integer.MAX_VALUE;
		
		st = new StringTokenizer(in.readLine(), " ");
		int p1 = Integer.parseInt(st.nextToken());
		int p2 = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(in.readLine());
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(in.readLine(), " ");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			family[i][j] = family[j][i] = 1;
		}
		
		dfs(p1,p2,0);
		
		if(answer == Integer.MAX_VALUE)
			System.out.print(-1);
		else
			System.out.print(answer);
		in.close();
	}

	private static void dfs(int p1, int p2, int cnt) {
		visited[p1] = true;
		
		if(p1 == p2)
			answer = cnt;
		else {
			for (int j = 1; j < N + 1; j++) {
				if (family[p1][j] > 0 && !visited[j])
					dfs(j,p2,cnt+1);
			}
		}
		visited[p1] = false;
	}

	private static int bfs(int p1, int p2) {
		int cnt = 0;
		boolean find = false;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(p1);
		visited[p1] = true;

		while (!queue.isEmpty()) {
			int size = queue.size();

			while (--size >= 0) {
				int cur = queue.poll();

				if (cur == p2) {
					find = true;
					queue.clear();
					cnt--;
					break;
				}
				for (int j = 1; j < N + 1; j++) {
					if (family[cur][j] > 0 && !visited[j])
						queue.offer(j);
				}
			}
			cnt++;
		}

		if (!find)
			cnt = -1;

		return cnt;
	}

}