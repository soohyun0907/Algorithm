import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 122,404 KB
 * 실행시간 : 616 ms
 * 코드길이 : 2,283 B
 * 소요시간 : 50M
 */

public class Main_B_7569_토마토 {

	static int M, N, H, answer, cnt;
	static int[][][] tomato;
	static int[] deltaH = { -1, 1, 0, 0, 0, 0 }; // 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
	static int[] deltaN = { 0, 0, 0, 0, -1, 1 }; // 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
	static int[] deltaM = { 0, 0, -1, 1, 0, 0 }; // 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
	static Queue<boxPoint> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		tomato = new int[H][N][M];
		queue = new LinkedList<boxPoint>();
		cnt = 0;

		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int m = 0; m < M; m++) {
					tomato[h][n][m] = Integer.parseInt(st.nextToken());
					if (tomato[h][n][m] != 0)
						cnt++;
					if (tomato[h][n][m] > 0)
						queue.offer(new boxPoint(h, n, m));
				}
			}
		}

		if (cnt == N * M * H) {
			answer = 0;
		} else {
			bfs();

			if (cnt < N * M * H)
				answer = -1;
		}

		System.out.print(answer);
		in.close();
	}

	private static void bfs() {
		answer = 0;
		L: while (!queue.isEmpty()) {
			int size = queue.size();

			while (--size >= 0) {
				boxPoint cur = queue.poll();
				int h = cur.h;
				int n = cur.n;
				int m = cur.m;

				for (int i = 0; i < deltaH.length; i++) {
					int curH = h + deltaH[i];
					int curN = n + deltaN[i];
					int curM = m + deltaM[i];

					if (curH < 0 || curH >= H || curN < 0 || curN >= N || curM < 0 || curM >= M)
						continue;

					if (tomato[curH][curN][curM] == 0) {
						cnt++;
						queue.offer(new boxPoint(curH, curN, curM));
						tomato[curH][curN][curM] = 1;
					}

				}
			}
			answer++;
			if (cnt == N * M * H)
				break L;
		}
	}

	static class boxPoint {
		int h;
		int n;
		int m;

		public boxPoint(int h, int n, int m) {
			this.h = h;
			this.n = n;
			this.m = m;
		}
	}
}
