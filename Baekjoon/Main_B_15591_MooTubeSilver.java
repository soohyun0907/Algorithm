import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 310,292 KB
 * 시간 : 1,848 ms
 * 코드길이 : 1,899 B
 * 소요시간 : 2H
 */

public class Main_B_15591_MooTubeSilver {

	static int N, Q;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder answer = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		ArrayList<Info>[] mootube = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			mootube[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			mootube[p].add(new Info(q, r));
			mootube[q].add(new Info(p, r));
		}

		int ans;
		boolean[] visited;
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			visited = new boolean[N + 1];
			ans = 0;
			visited[v] = true;
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.offer(v);
			while (!queue.isEmpty()) {
				int cur = queue.poll();

				for (int j = 0; j < mootube[cur].size(); j++) {
					int next = mootube[cur].get(j).idx;

					if (visited[next]) continue;
					
					int nextVal = mootube[cur].get(j).val;

					if (nextVal >= k) {
						visited[next] = true;
						ans++;
						queue.offer(next);
					}
				}
			}

			answer.append(ans).append('\n');
		}

		System.out.println(answer);
	}

	private static class Info {
		int idx;
		int val;

		public Info(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
	}

}
