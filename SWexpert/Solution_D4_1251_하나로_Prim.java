import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 60,320 kb
 * 시간 : 466 ms
 * 코드길이 : 2,601
 */

public class Solution_D4_1251_하나로_Prim {

	static int N;
	static long[][] island;
	static long[][] graph;
	static double E;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			answer.append("#").append(t).append(" ");
			N = Integer.parseInt(in.readLine());
			island = new long[N][2];

			// X좌표 입력.
			StringTokenizer xLine = new StringTokenizer(in.readLine(), " ");
			// Y좌표 임력.
			StringTokenizer yLine = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				island[i] = new long[] { Integer.parseInt(xLine.nextToken()), Integer.parseInt(yLine.nextToken()) };
			}
			E = Double.parseDouble(in.readLine());

			// 입력된 자료를 기반으로 섬 간의 가중치 그래프 구하기.
			graph = new long[N][N];
			long[] from, to;
			for (int r = 0; r < N; r++) {
				from = island[r];
				for (int c = r + 1; c < N; c++) {
					to = island[c];
					graph[c][r] = graph[r][c] = (from[0] - to[0]) * (from[0] - to[0])
							+ (from[1] - to[1]) * (from[1] - to[1]);
				}
			}

			double cost = prim(0) * E;
			answer.append(Math.round(cost)).append("\n");

		} // end test-case

		System.out.print(answer);
		in.close();
	}

	private static double prim(int start) {

		// MST에 들어가지 않은 녀석들.
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		// 모든 정점들을 다 관리.
		Edge[] dynamicGraph = new Edge[N];

		for (int n = 0; n < N; n++) {
			dynamicGraph[n] = new Edge(n, Long.MAX_VALUE);
			if (n == start) {
				dynamicGraph[n].cost = 0;
			}
			pq.add(dynamicGraph[n]);
		}
		long cost = 0;

		while (!pq.isEmpty()) {
			Edge front = pq.poll(); // MST에 포함되는 녀석.
			cost += front.cost;

			// 자식 탐색.
			for (int i = 0; i < N; i++) {
				Edge child = dynamicGraph[i];
				// pq : 비MST.
				if (pq.contains(child)) {
					long tempCost = graph[front.idx][child.idx];
					if (tempCost < child.cost) {
						child.cost = tempCost;
						pq.remove(child);
						pq.offer(child);
					}
				}
			}
		}

		return cost;
	}

	static class Edge implements Comparable<Edge> {
		int idx;
		long cost;

		public Edge(int idx, long cost) {
			super();
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}

	}
}
