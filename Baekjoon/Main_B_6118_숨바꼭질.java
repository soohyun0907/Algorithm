import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 31,032 KB
 * 시간 : 308 ms
 * 코드길이 : 2,102 B
 * 소요시간 : 1H 50M
 */

public class Main_B_6118_숨바꼭질 {

	static int N, M;
	static Node[] adjList;

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
		adjList = new Node[N + 1];
		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			adjList[a] = new Node(b, 1, adjList[a]);
			adjList[b] = new Node(a, 1, adjList[b]);
		} // end input

		int start = 1, max = 0;
		int[] D = new int[N + 1];
		Arrays.fill(D, Integer.MAX_VALUE);
		boolean[] visited = new boolean[N + 1];
		D[start] = 0;

		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		pq.offer(new Point(start, 0));

		while (!pq.isEmpty()) {
			Point cur = pq.poll();
			if (visited[cur.v]) continue;
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
		int num = N + 1, cnt = 0;
		for (int i = 1; i < N + 1; i++) {
			if (D[i] == max) {
				if (num > i)
					num = i;
				cnt++;
			}
		}
		System.out.println(num + " " + max + " " + cnt);
		in.close();
	} // end main

}
