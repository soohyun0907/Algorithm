package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리: 119,116 KB
 * 실행시간: 2,152 ms 
 * 코드길이: 1,922 B
 */

public class Main_B_1753_최단경로_Dijkstra {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(in.readLine());
		List<Edge>[] adj = new ArrayList[V + 1];

		for (int i = 1; i < V + 1; i++)
			adj[i] = new ArrayList<>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[a].add(new Edge(b, c));
		}

		int[] D = new int[V + 1];
		Arrays.fill(D, Integer.MAX_VALUE);
		boolean[] check = new boolean[V + 1];

		D[start] = 0;
		for (int i = 1; i < V; i++) {
			int min = Integer.MAX_VALUE;
			int index = -1;
			for (int j = 1; j < V + 1; j++) {
				if (!check[j] && min > D[j]) {
					min = D[j];
					index = j;
				}
			}
			// 연결이 없는 경우 끝!
			if (index == -1)
				break;

			// 새로운 친구로부터 갈 수 있는 경로들을 업데이트
			for (Edge next : adj[index]) {
				// 아직 처리하지 않았으면서, 경로가 존재하고, index까지의 거리 + index부터 j까지의 거리가 D[j]보다 작으면
				if (!check[next.v] && D[next.v] > D[index] + next.weight) {
					D[next.v] = D[index] + next.weight;
				}
			}
			// 처리된 것으로 체크
			check[index] = true;
		}

		for (int i = 1; i < V + 1; i++) {
			if (D[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(D[i]);
		}
		in.close();
	}

	static class Edge implements Comparable<Edge> {
		int v, weight;

		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

}
