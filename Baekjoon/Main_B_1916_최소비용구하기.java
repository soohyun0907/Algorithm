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
 * 메모리: 45,552 KB
 * 실행시간 : 820 ms
 * 코드길이 : 2,020 B
 * 소요시간 : 30 M
 */

public class Main_B_1916_최소비용구하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		List<Edge>[] adj = new ArrayList[N + 1];

		for (int i = 1; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()); // 출발 도시 번호
			int b = Integer.parseInt(st.nextToken()); // 도착 도시 번호
			int c = Integer.parseInt(st.nextToken()); // 버스비용
			adj[a].add(new Edge(b, c));
		}
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int dest = Integer.parseInt(st.nextToken());

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] check = new boolean[N + 1];
		Edge[] D = new Edge[N + 1];
		for (int i = 1; i < N + 1; i++) {
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
			for (Edge next : adj[edge.v]) {
				if (!check[next.v] && D[next.v].weight > D[edge.v].weight + next.weight) {
					D[next.v].weight = D[edge.v].weight + next.weight;
					pq.remove(D[next.v]);
					pq.add(D[next.v]);
				}
			}
			check[edge.v] = true;
		}

		System.out.println(D[dest].weight);
		in.close();
	}

	static class Edge implements Comparable<Edge> {
		int v;
		int weight;

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
