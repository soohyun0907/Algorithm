package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리: 120,696 KB 
 * 실행시간: 984 ms 
 * 코드길이: 2,027 B
 */

public class Main_B_1753_최단경로_Dijkstra_PQ {

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

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] check = new boolean[V + 1];
		Edge[] D = new Edge[V + 1];
		// 0번에서 출발하는 걸로
		for (int i = 1; i < V + 1; i++) {
			if (i == start) {
				D[i] = new Edge(i, 0);
				check[i] = true;
			} else {
				D[i] = new Edge(i, Integer.MAX_VALUE);
			}
			pq.add(D[i]);
		}
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (edge.weight == Integer.MAX_VALUE)
				break;

			for (Edge next : adj[edge.v]) {
				// check되지 않았으면서, D[next.v]가 D[edge.v] + next.weight 보다 더 크다면 갱신
				if (!check[next.v] && D[next.v].weight > D[edge.v].weight + next.weight) {
					D[next.v].weight = D[edge.v].weight + next.weight;
					// decrease key
					pq.remove(D[next.v]);
					pq.add(D[next.v]);
				}
			}
			check[edge.v] = true;
		}

		for (int i = 1; i < V + 1; i++) {
			if (D[i].weight == Integer.MAX_VALUE)
				System.out.print("INF\n");
			else
				System.out.print(D[i].weight + "\n");
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
