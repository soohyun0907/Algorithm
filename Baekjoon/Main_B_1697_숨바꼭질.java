package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_1697_숨바꼭질 {

	static int N, K, count = 0;
	static int SIZE = 100000, size, current;

	public static void main(String[] args) throws IOException {
		boolean[] visited = new boolean[SIZE + 1];
		Queue<Integer> queue = new LinkedList<Integer>();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (N == K) {
			System.out.print(count);
			return;
		}

		queue.add(N);

		while (!queue.isEmpty()) {
			size = queue.size();
			for (int i = 0; i < size; i++) {
				current = queue.poll();
				if (current == K) {
					System.out.print(count);
					return;
				}
				if (current >= 0 && current <= SIZE) {
					if (current + 1 <= SIZE && !visited[current + 1]) {
						queue.offer(current + 1);
						visited[current + 1] = true;
					}
					if (current - 1 >= 0 && !visited[current - 1]) {
						queue.offer(current - 1);
						visited[current - 1] = true;
					}
					if (current <= SIZE / 2 && !visited[current * 2]) {
						queue.offer(current * 2);
						visited[current * 2] = true;
					}
				}
			}
			count++;
		}

		System.out.print(count);
		in.close();
	}

}
