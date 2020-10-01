import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 54,108 KB
 * 시간 : 384 ms
 * 코드길이 : 2103 B
 * 소요시간 : -
 */

public class Main_B_4803_트리 {

	static int n, m, a, b, ans;
	static int[] parents, counts, edges;
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		int tc = 1;
		while (true) {
			st = new StringTokenizer(in.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			if (n == 0 && m == 0) break;

			parents = new int[n];
			counts = new int[n];
			edges = new int[n];
			check = new boolean[n];
			init();
			Arrays.fill(counts, 1);

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				a = Integer.parseInt(st.nextToken()) - 1;
				b = Integer.parseInt(st.nextToken()) - 1;
				union(a, b);
			}

			ans = 0;
			for (int i = 0; i < n; i++) {
				int idx = findSet(i);
				if (!check[idx]) {
					if (counts[idx] - 1 == edges[idx])
						ans++;
					check[idx] = true;
				}
			}

			if (ans == 0)
				answer.append("Case ").append(tc).append(":").append(" No trees.\n");
			else if (ans == 1)
				answer.append("Case ").append(tc).append(":").append(" There is one tree.\n");
			else
				answer.append("Case ").append(tc).append(":").append(" A forest of ").append(ans).append(" trees.\n");

			tc++;
		}

		System.out.println(answer);
	}

	private static void init() {
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}

	private static int findSet(int a) {
		if (parents[a] == a) return a;

		return parents[a] = findSet(parents[a]);
	}

	private static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot) {
			edges[aRoot] += 1;
			return;
		}
		if (aRoot > bRoot) {
			int temp = aRoot;
			aRoot = bRoot;
			bRoot = temp;
		}
		parents[bRoot] = aRoot;
		counts[aRoot] += counts[bRoot];
		edges[aRoot] += (edges[bRoot] + 1);
	}
}