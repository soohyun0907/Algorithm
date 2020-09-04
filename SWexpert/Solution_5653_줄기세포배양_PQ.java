import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 47,372 KB 
 * 실행시간 : 207 ms 
 * 코드길이 : 2,350 B 
 * 소요시간 : 40M
 */

public class Solution_5653_줄기세포배양_PQ {

	static int size = 400, N, M, K, res;
	static boolean[][] visited;
	static PriorityQueue<Data> cells;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();

		int TC = Integer.parseInt(in.readLine());
		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			visited = new boolean[size][size];
			cells = new PriorityQueue<Data>();
			res = 0;

			for (int i = size / 2; i < N + size / 2; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = size / 2; j < M + size / 2; j++) {
					int x = Integer.parseInt(st.nextToken());
					if (x > 0) {
						visited[i][j] = true;
						cells.offer(new Data(i, j, x, x + 1));
						if (x * 2 > K) res++; // K시간보다 크면 비활성상태일 것이기 때문에 미리 카운트
					}
				}
			}

			bfs();

			answer.append('#').append(t).append(' ').append(res).append('\n');
		} // end test-case

		System.out.println(answer);
	} // end main

	private static void bfs() {
		int time = 0;
		Data cur;

		while (time <= K) {
			cur = cells.poll();
			time = cur.time;

			if (time > K) break;

			for (int d = 0; d < delta.length; d++) {
				int curX = cur.i + delta[d][0];
				int curY = cur.j + delta[d][1];

				if (!visited[curX][curY]) {
					visited[curX][curY] = true;
					cells.offer(new Data(curX, curY, cur.x, time + cur.x + 1));

					if (time + cur.x * 2 > K) res++;
				}
			}
		}
	} // end bfs

	static class Data implements Comparable<Data> {
		int i;
		int j;
		int x; // 줄기세포 생명력

		int time; // 활성화 시간

		public Data(int i, int j, int x, int time) {
			this.i = i;
			this.j = j;
			this.x = x;

			this.time = time;
		}

		@Override
		public int compareTo(Data o) {
			if (time != o.time) return Integer.compare(time, o.time);
			return -Integer.compare(x, o.x);
		}
	}
}
