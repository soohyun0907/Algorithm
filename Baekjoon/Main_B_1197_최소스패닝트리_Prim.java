package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 57,220 KB
 * 실행시간 : 692 ms
 * 코드길이 : 1,888 B
 */

public class Main_B_1197_최소스패닝트리_Prim {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		long result = 0;

		ArrayList<Edge>[] adj = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()); // 정점1
			int b = Integer.parseInt(st.nextToken()); // 정점2
			int c = Integer.parseInt(st.nextToken()); // 가중치
			adj[a-1].add(new Edge(b - 1, c));
			adj[b-1].add(new Edge(a - 1, c));
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		Edge[] dist = new Edge[V];
		// dist안의 각 거리들은 무한대
		for (int i = 1; i < V; i++) {
			dist[i] = new Edge(i, Integer.MAX_VALUE);
			pq.add(dist[i]);
		}

		boolean[] mst = new boolean[V + 1];
		// 시작점은 0
		dist[0] = new Edge(0, 0);
		pq.add(dist[0]);
		while (!pq.isEmpty()) {
			// 현재 dist가 가장 작은 친구를 데려와서
			Edge e = pq.poll();
			if (mst[e.dest]) {
				continue;
			}
			mst[e.dest] = true;
			result += e.cost;
			// 그 친구로부터 출발하는 모든 간선에 대해서
			for (Edge next : adj[e.dest]) {
				// 그 간선의 목적지가 아직 pq에 들어있는 정점이라면
				// 그리고 더 빨리 도착할 수 있다면
				if (!mst[next.dest] && dist[next.dest].cost > next.cost) {
					// dist갱신
					dist[next.dest].cost = next.cost;
					// decrease key연산
					pq.remove(dist[next.dest]);
					pq.add(dist[next.dest]);
				}
			}
		}
		System.out.println(result);
		in.close();
	}

	static class Edge implements Comparable<Edge> {
		int dest;
		int cost;

		Edge(int d, int c) {
			dest = d;
			cost = c;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

}
