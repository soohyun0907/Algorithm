package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 96,264 KB 
 * 실행시간 : 878 ms 
 * 코드길이 : 2,387 B 
 * 소요시간 : 1H 
 * 크루스칼 알고리즘 사용
 */

public class Solution_D4_1251_하나로_Kruskal {

	static long[] X;
	static long[] Y;
	static long[][] graph;
	static int[] parents;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			answer.append('#').append(tc).append(' ');
			int N = Integer.parseInt(in.readLine());
			X = new long[N];
			Y = new long[N];
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				X[i] = Long.parseLong(st.nextToken());
			}
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				Y[i] = Long.parseLong(st.nextToken());
			}

			double E = Double.parseDouble(in.readLine());
			int size = N * (N - 1) / 2;
			graph = new long[size][3];
			int cnt = 0;
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					graph[cnt][0] = i;
					graph[cnt][1] = j;
					graph[cnt][2] = (long) (Math.pow(X[i] - X[j], 2) + Math.pow(Y[i] - Y[j], 2));
					cnt++;
				}
			}

			Arrays.sort(graph, new Comparator<long[]>() {
				@Override
				public int compare(long[] o1, long[] o2) {
					return Long.compare(o1[2], o2[2]);
				}

			});

			parents = new int[N];
			for (int i = 0; i < N; i++) {
				makeSet(i);
			}
			cnt = 0;
			double result = 0;
			for (int i = 0; i < size; i++) {
				int a = findSet((int) graph[i][0]);
				int b = findSet((int) graph[i][1]);
				// 같은 팀이라면 패스
				if (a == b)
					continue;
				// 간선이 연결하는 두 정점이 같은 팀이 아니라면 한팀으로 합쳐주고 간선선택
				union(a, b);
				result += graph[i][2];
				cnt++;
				if (cnt == N - 1)
					break;
			}
			result *= E;
			answer.append(Math.round(result)).append('\n');
		}

		System.out.print(answer);
		in.close();
	}

	static void makeSet(int x) {
		parents[x] = x;
	}

	static int findSet(int x) {
		if (x == parents[x])
			return x;
		else {
			parents[x] = findSet(parents[x]);
			return (int) parents[x];
		}
	}

	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		parents[py] = px;
	}

}
