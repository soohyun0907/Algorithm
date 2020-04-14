import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 14,004 KB
 * 실행시간 : 96 ms
 * 코드길이 : 1,897 B
 * 소요시간 : 1H 20M
 */

public class Main_B_4386_별자리만들기 {

	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		parents = new int[n];
		double[][] star = new double[n][2];
		double result = 0.0;

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			star[i][0] = Double.parseDouble(st.nextToken());
			star[i][1] = Double.parseDouble(st.nextToken());
		}

		// 크루스칼
		int e = n * (n - 1) / 2;
		double[][] edges = new double[e][3];
		for (int i = 0; i < e; i++) {
			for (int a = 0; a < n; a++) {
				for (int b = a + 1; b < n; b++, i++) {
					edges[i][0] = a;
					edges[i][1] = b;
					edges[i][2] = Math
							.sqrt(Math.pow(star[a][0] - star[b][0], 2) + Math.pow(star[a][1] - star[b][1], 2));
				}
			}
		}

		Arrays.sort(edges, new Comparator<double[]>() {

			@Override
			public int compare(double[] o1, double[] o2) {
				return Double.compare(o1[2], o2[2]);
			}

		});

		for (int i = 0; i < n; i++) {
			makeSet(i);
		}

		int cnt = 0;
		for (int i = 0; i < e; i++) {
			int a = findSet((int) edges[i][0]);
			int b = findSet((int) edges[i][1]);

			if (a == b)
				continue;

			union(a, b);
			result += edges[i][2];
			cnt++;
			if (cnt == n - 1)
				break;
		}

		System.out.printf("%.2f", result);
		in.close();
	}

	static void makeSet(int x) {
		parents[x] = x;
	}

	static int findSet(int x) {
		if (x == parents[x])
			return x;
		return parents[x] = findSet(parents[x]);
	}

	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);

		if (px != py)
			parents[py] = px;
	}

}
