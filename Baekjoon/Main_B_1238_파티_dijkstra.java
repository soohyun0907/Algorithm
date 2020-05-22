import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 72,176 KB
 * 시간 : 476 ms
 * 코드길이 : 2,332 B
 */

public class Main_B_1238_파티_dijkstra {

	static int N, M, X;
	static Node[] adjList; // 인접리스트

	static class Node {
		int no, weight;
		Node next;

		public Node(int no, int weight, Node next) {
			this.no = no;
			this.weight = weight;
			this.next = next;
		}
	}

	static class Point implements Comparable<Point> {
		int v, time;

		public Point(int v, int time) {
			this.v = v;
			this.time = time;
		}

		@Override
		public int compareTo(Point o) {
			return this.time - o.time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken()) - 1;
		adjList = new Node[N];

		int x, y, t;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			x = Integer.parseInt(st.nextToken()) - 1;
			y = Integer.parseInt(st.nextToken()) - 1;
			t = Integer.parseInt(st.nextToken());
			adjList[x] = new Node(y, t, adjList[x]);
		}

		int max = 0, temp = 0;
		for (int i = 0; i < N; i++) {
			if (i == X) continue;
			// i 가는 마을 파티하러 갈때
			temp = go(i, X);
			temp += go(X, i);
			if (max < temp)
				max = temp;
		}

		System.out.println(max);
	}

	// 다익스트라
	private static int go(int start, int end) {
		int[] D = new int[N];
		Arrays.fill(D, Integer.MAX_VALUE);
		boolean[] visited = new boolean[N];
		D[start] = 0;

		PriorityQueue<Point> queue = new PriorityQueue<Point>();
		queue.offer(new Point(start, 0));

		while (!queue.isEmpty()) {
			Point cur = queue.poll(); // 최소거리 정점 선택
			if (visited[cur.v]) continue;
			visited[cur.v] = true;

			if (cur.v == end)
				return cur.time;
			// 현 정점을 경유지로 해서 다른 정점들 비용 갱신
			for (Node temp = adjList[cur.v]; temp != null; temp = temp.next) {
				if (!visited[temp.no] && D[temp.no] > cur.time + temp.weight) {
					D[temp.no] = cur.time + temp.weight;
					queue.offer(new Point(temp.no, D[temp.no]));
				}
			}
		}

		return 0;
	}

}
