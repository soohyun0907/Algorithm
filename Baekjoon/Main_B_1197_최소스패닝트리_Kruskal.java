package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 50,356 KB
 * 시간 : 504 ms
 * 코드길이 : 1,741 B
 * 소요시간 : 1H
 */

public class Main_B_1197_최소스패닝트리_Kruskal {

	static int[] parents;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int[][] edges = new int[E][3];
		parents = new int[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			edges[i][0] = Integer.parseInt(st.nextToken()); // 정점1
			edges[i][1] = Integer.parseInt(st.nextToken()); // 정점2
			edges[i][2] = Integer.parseInt(st.nextToken()); // 가중치
		}
		// 가중치를 오름차순으로 정렬
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}

		});

		// unionFind를 할 준비 - makeSet과정
		for (int i = 1; i < V + 1; i++) {
			parents[i] = i;
		}

		// V-1번 반복하면서 union진행
		int cnt = 0;
		long result = 0;
		for (int i = 0; i < E; i++) {
			int a = findSet(edges[i][0]);
			int b = findSet(edges[i][1]);

			if (a == b)
				continue;

			union(a, b);
			result += edges[i][2];
			cnt++;
			if (cnt == V - 1)
				break;
		}

		System.out.println(result);
		in.close();
	}

	static int findSet(int x) {
		if (parents[x] == x)
			return x;
		return parents[x] = findSet(parents[x]);
	}

	static void union(int a, int b) {
		int px = findSet(a);
		int py = findSet(b);

		parents[py] = px;
	}
}
