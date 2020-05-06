import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,364 KB
 * 시간 : 80 ms
 * 코드길이 : 4,304 B
 */

public class Main_B_17472_다리만들기2 {

	static int N, M, islandIndex;
	static int[][] map, graph;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬을 찾는다.
		islandIndex = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					bfs(i, j);
					islandIndex++;
				}
			}
		}

		// 그래프 초기화
		graph = new int[islandIndex][islandIndex];
		// 각 섬의 최단거리로 그래프를 구성할 계획 - 그래프의 값을 최대값으로 초기화
		for (int i = 2; i < islandIndex; i++) {
			Arrays.fill(graph[i], INF);
		}

		// 각 섬 별로 거리(간선 가중치) 찾아보기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 1) {
					makeGraph(i, j);
				}
			}
		}

		// MST
		System.out.println(prim());

		in.close();
	} // end main

	private static int prim() {
		// 모든 vertex가 처음에 여기 들어있다가 탈출해야한다.
		PriorityQueue<Vertex> notMstGroup = new PriorityQueue<Vertex>();
		// 연결 비용과 관련된 섬들의 정보를 관리할 배열
		Vertex[] vertexes = new Vertex[islandIndex];
		for (int i = 2; i < islandIndex; i++) {
			if (i == 2) {
				vertexes[i] = new Vertex(i, 0);
			} else {
				vertexes[i] = new Vertex(i, INF);
			}
			notMstGroup.offer(vertexes[i]);
		}

		int sum = 0;
		while (!notMstGroup.isEmpty()) {
			Vertex front = notMstGroup.poll();
			if (front.cost == INF) {
				return -1;
			}
			sum += front.cost;

			for (int i = 2; i < islandIndex; i++) {
				Vertex child = vertexes[i];
				// 아직 MST의 요소가 아니라면
				if (notMstGroup.contains(child)) {
					// 그래프에서 비용 조회, 현재 가지고 있는 것보다 작으면 업데이트
					if (child.cost > graph[front.idx][i]) {
						child.cost = graph[front.idx][i];
						notMstGroup.remove(child);
						notMstGroup.offer(child);
					}
				}
			}
		}
		return sum;
	}

	private static void makeGraph(int row, int col) {
		int base = map[row][col];

		for (int d = 0; d < delta.length; d++) {

			for (int l = 1;; l++) {
				int nr = row + delta[d][0] * l;
				int nc = col + delta[d][1] * l;

				if (isIn(nr, nc)) {
					if (map[nr][nc] == 0) {
						continue;
					} else if (map[nr][nc] == base) {
						break;
					} else {
						if (l > 2) {
							graph[map[nr][nc]][base] = graph[base][map[nr][nc]] = Math.min(graph[base][map[nr][nc]],
									l - 1);
						}
						break;
					}
				} else {
					break;
				}
			}
		}

	}

	private static void bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(i, j));
		map[i][j] = islandIndex;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int k = 0; k < delta.length; k++) {
				int curX = cur.i + delta[k][0];
				int curY = cur.j + delta[k][1];

				if (isIn(curX, curY) && map[curX][curY] == 1) {
					queue.offer(new Point(curX, curY));
					map[curX][curY] = islandIndex;
				}
			}
		}
	}

	private static boolean isIn(int curX, int curY) {
		if (curX < 0 || curX >= N || curY < 0 || curY >= M)
			return false;
		return true;
	}

	// 가중치와 정점에 대한 정보를 가지고 있음
	static class Vertex implements Comparable<Vertex> {
		int idx;
		int cost;

		public Vertex(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.cost, o.cost);
		}

	}

	static class Point {
		int i;
		int j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
