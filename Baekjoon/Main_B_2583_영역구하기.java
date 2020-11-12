import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 14,872 KB
 * 시간 : 140 ms
 * 코드길이 : 2,370 B
 * 소요시간 : 40m
 */

public class Main_B_2583_영역구하기 {

	static int M, N, K;
	static int[][] paper;
	static boolean[][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		paper = new int[M][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			draw(y1, x1, y2, x2);
		}

		visited = new boolean[M][N];
		ArrayList<Integer> answer = new ArrayList<Integer>();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && paper[i][j] == 0) {
					answer.add(bfs(i, j));
				}
			}
		}

		Collections.sort(answer);
		System.out.println(answer.size());
		for (int i = 0; i < answer.size(); i++) {
			System.out.print(answer.get(i) + " ");
		}
	}

	private static int bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(i, j));
		visited[i][j] = true;
		int size = 0, curX, curY;
		Point cur;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			size++;
			for (int d = 0; d < delta.length; d++) {
				curX = cur.x + delta[d][0];
				curY = cur.y + delta[d][1];

				if (curX < 0 || curX >= M || curY < 0 || curY >= N || paper[curX][curY] == 1 || visited[curX][curY])
					continue;

				if (paper[curX][curY] == 0 && !visited[curX][curY]) {
					queue.add(new Point(curX, curY));
					visited[curX][curY] = true;
				}
			}
		}

		return size;
	}

	private static void draw(int y1, int x1, int y2, int x2) {

		for (int y = y1; y < y2; y++) {
			for (int x = M - x2; x < M - x1; x++) {
				paper[x][y] = 1;
			}
		}

	}

	private static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
