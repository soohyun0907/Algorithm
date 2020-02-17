package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Tomato {
	int x;
	int y;

	public Tomato(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main_B_7576_토마토 {

	static int M, N, time = 0, currentX = 0, currentY = 0, countA = 0, countB = 0;
	static int[][] tomato;
	static boolean[][] visited;
	static int[] deltaX = { 0, 0, -1, 1 }; // 좌,우,상,하
	static int[] deltaY = { -1, 1, 0, 0 };
	static Queue<Tomato> queue = new LinkedList<Tomato>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		tomato = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				if (tomato[i][j] == 1) {
					queue.offer(new Tomato(i, j));
					visited[i][j] = true;
				}
				if (tomato[i][j] == 0) {
					countA++;
				}
			}
		}

		if (countA == 0) {
			System.out.println("0");
			return;
		}

		bfs();
		if (countA == countB)
			System.out.println(time - 1);
		else
			System.out.println("-1");
		in.close();
	}

	private static void bfs() {
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				for (int n = 0; n < deltaX.length; n++) {
					currentX = queue.peek().x + deltaX[n];
					currentY = queue.peek().y + deltaY[n];
					if (currentX < 0 || currentX >= N || currentY < 0 || currentY >= M)
						continue;

					if (tomato[currentX][currentY] == 0 && !visited[currentX][currentY]) {
						queue.offer(new Tomato(currentX, currentY));
						tomato[currentX][currentY] = 1;
						visited[currentX][currentY] = true;
						countB++;
					}
				}
				queue.poll();
			}
			time++;
		}
	}
}
