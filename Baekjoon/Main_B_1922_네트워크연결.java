import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 54,532 KB
 * 시간 : 656 ms
 * 코드길이 : 1,827 B
 */

public class Main_B_1922_네트워크연결 {

	static int N, M;
	static int[][] costs;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		costs = new int[N + 1][N + 1];
		visited = new boolean[N + 1];

		int a, b, c;
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			costs[a][b] = costs[b][a] = c;
		}

		System.out.println(prim());
		in.close();
	}

	private static int prim() {
		int cost = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		Edge[] comList = new Edge[N + 1];
		comList[1] = new Edge(1, 0);
		pq.add(comList[1]);
		for (int n = 2; n < N + 1; n++) {
			comList[n] = new Edge(n, Integer.MAX_VALUE);
			pq.add(comList[n]);
		}

		while (!pq.isEmpty()) {
			Edge front = pq.poll();
			cost += front.cost;

			for (int n = 1; n < N + 1; n++) {
				Edge child = comList[n];
				if (pq.contains(child)) {
					int tempCost = costs[front.idx][child.idx];
					if (tempCost > 0 && tempCost < child.cost) {
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
		int cost;

		public Edge(int idx, int cost) {
			super();
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}

	}
}
