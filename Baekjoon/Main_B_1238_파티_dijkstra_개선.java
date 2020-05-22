import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 18,128 KB 
 * 시간 : 148 ms 
 * 코드길이 : 2,525 B
 */

public class Main_B_1238_파티_dijkstra_개선 {

	static int N, M, X;
	static Node[] adjList, radjList; // 인접리스트, 역간선 리스트

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
		radjList = new Node[N];

		int x, y, t;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			x = Integer.parseInt(st.nextToken()) - 1;
			y = Integer.parseInt(st.nextToken()) - 1;
			t = Integer.parseInt(st.nextToken());
			adjList[x] = new Node(y, t, adjList[x]); // x -> y로 가는 정보
			radjList[y] = new Node(x, t, radjList[y]); // y <- x 정보 (y는 x에서 온다.)
		}

		int max = 0, temp = 0;
		int[] gD = go(X, radjList); // 파티하러 갈 때
		int[] cD = go(X, adjList); // 파티에서 올 때

		for (int i = 0; i < N; i++) {
			if (i == X)
				continue;
			temp = gD[i] + cD[i];
			if (max < temp)
				max = temp;
		}
		System.out.println(max);
	}

	// 다익스트라
	private static int[] go(int start, Node[] adjList) {
		int[] D = new int[N];
		Arrays.fill(D, Integer.MAX_VALUE);
		boolean[] visited = new boolean[N];
		D[start] = 0;
		PriorityQueue<Point> queue = new PriorityQueue<Point>();
		queue.offer(new Point(start, 0));

		while (!queue.isEmpty()) {
			Point cur = queue.poll(); // 최소거리 정점 선택
			if (visited[cur.v])
				continue;
			visited[cur.v] = true;

			// 현 정점을 경유지로 해서 다른 정점들 비용 갱신
			for (Node temp = adjList[cur.v]; temp != null; temp = temp.next) {
				if (!visited[temp.no] && D[temp.no] > cur.time + temp.weight) {
					D[temp.no] = cur.time + temp.weight;
					queue.offer(new Point(temp.no, D[temp.no]));
				}
			}
		}

		return D;
	}

}
