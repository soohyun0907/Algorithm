import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,732 KB
 * 실행시간 : 96 ms
 * 코드길이 : 1,723 B
 */

public class Main_B_4386_별자리만들기_Prim {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		double[][] star = new double[n][2];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			star[i][0] = Double.parseDouble(st.nextToken());
			star[i][1] = Double.parseDouble(st.nextToken());
		}

		// prim
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] isVisited = new boolean[n];
		// 시작점은 0
		isVisited[0] = true;
		for (int i = 1; i < n; i++) {
			double cost = Math.pow(star[0][0] - star[i][0], 2) + Math.pow(star[0][1] - star[i][1], 2);
			cost = Math.sqrt(cost);
			pq.offer(new Edge(0, i, cost));
		}
		double result = 0;
		int count = 0;
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (isVisited[e.y])
				continue;
			isVisited[e.y] = true;
			count++;
			result += e.cost;

			for (int i = 0; i < n; i++) {
				if (isVisited[i])
					continue;
				pq.offer(new Edge(e.y, i,
						Math.sqrt(Math.pow(star[e.y][0] - star[i][0], 2) + Math.pow(star[i][1] - star[e.y][1], 2))));
			}

			if (count == n - 1)
				break;
		}

		System.out.printf("%.4f", result);
		in.close();
	}

	static class Edge implements Comparable<Edge> {
		int x;
		int y;
		double cost;

		Edge(int x, int y, double cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.cost, o.cost);
		}

	}

}