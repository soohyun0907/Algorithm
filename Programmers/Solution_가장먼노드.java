import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_가장먼노드 {

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

	public static int solution(int n, int[][] edge) {
		int answer = 0;
		Node[] adjList = new Node[n];
		int a, b;
		for (int i = 0; i < edge.length; i++) {
			a = edge[i][0] - 1;
			b = edge[i][1] - 1;
			adjList[a] = new Node(b, 1, adjList[a]);
			adjList[b] = new Node(a, 1, adjList[b]);
		}
		int start = 0, max = 0;
		int[] D = new int[n];
		Arrays.fill(D, Integer.MAX_VALUE);
		boolean[] visited = new boolean[n];
		D[start] = 0;

		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		pq.offer(new Point(start, 0));

		while (!pq.isEmpty()) {
			Point cur = pq.poll();
			if (visited[cur.v])
				continue;
			visited[cur.v] = true;

			for (Node temp = adjList[cur.v]; temp != null; temp = temp.next) {
				if (!visited[temp.no] && D[temp.no] > cur.time + temp.weight) {
					D[temp.no] = cur.time + temp.weight;
					pq.offer(new Point(temp.no, D[temp.no]));
					if (max < D[temp.no])
						max = D[temp.no];
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (D[i] == max) {
				answer++;
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		int[][] edge = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };
		System.out.println(solution(6, edge));
	}

}
