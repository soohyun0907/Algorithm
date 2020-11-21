import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 81,184 KB
 * 시간 : 2,664 ms
 * 코드길이 : 3,437 B
 * 소요시간 : 1H 50M
 */

public class Main_B_17143_낚시왕 {

	static int R, C, M, answer;
	static int[][] map;
	static Shark[] sharks;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } }; // 0:위 1:아래 2:오른쪽 3:왼쪽

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		sharks = new Shark[M + 1];
		answer = 0;

		for (int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1; // 상어의 위치 x 좌표
			int c = Integer.parseInt(st.nextToken()) - 1; // 상어의 위치 y 좌표
			int s = Integer.parseInt(st.nextToken()); // 상어의 속력
			int d = Integer.parseInt(st.nextToken()) - 1; // 상어의 이동방향 ( 1:위, 2:아래, 3:오른쪽, 4:왼쪽)
			int z = Integer.parseInt(st.nextToken()); // 상어의 크기
			sharks[i] = new Shark(i, r, c, s, d, z);
			map[r][c] = i; // 상어의 위치 index로 표시
		} // end input

		int king = -1;
		while (king < C - 1) { // (오른쪽 끝에 도착하면 끝!)
			// 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
			++king;
			// 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
			catchShark(king);
			// 3. 상어가 이동한다.
			moveShark();
		}
		System.out.println(answer);
	}

	private static void moveShark() {
		Shark shark;
		int move, curX, curY;
		PriorityQueue<Shark> pq = new PriorityQueue<Shark>();
		for (int i = 1; i < M + 1; i++) {
			if (sharks[i].size == 0) continue;
			pq.add(sharks[i]);
		}
		
		for (int[] row : map) {
			Arrays.fill(row, 0);
		}
		
		// 상어가 이동
		while (!pq.isEmpty()) {
			shark = pq.poll();
			move = shark.speed;
			curX = shark.x;
			curY = shark.y;
			while (--move >= 0) {
				curX += delta[shark.dir][0];
				curY += delta[shark.dir][1];
				if (curX < 0 || curX >= R || curY < 0 || curY >= C) {
					shark.dir = changeDir(shark.dir);
					move += 2;
				}
			}

			if (map[curX][curY] == 0) {
				map[curX][curY] = shark.idx;
				sharks[shark.idx] = new Shark(shark.idx, curX, curY, shark.speed, shark.dir, shark.size);
			} else {
				sharks[shark.idx].size = 0;
			}
		}
	}

	private static int changeDir(int dir) {
		if (dir == 0) return 1;
		else if (dir == 1) return 0;
		else if (dir == 2) return 3;
		return 2;
	}

	private static void catchShark(int king) {
		for (int i = 0; i < R; i++) {
			if (map[i][king] > 0) {
				int shark = map[i][king];
				map[i][king] = 0;
				answer += sharks[shark].size;
				sharks[shark].size = 0;
				break;
			}
		}
	}

	private static class Shark implements Comparable<Shark> {
		int idx;
		int x;
		int y;
		int speed;
		int dir;
		int size;

		public Shark(int idx, int r, int c, int s, int d, int z) {
			this.idx = idx;
			this.x = r;
			this.y = c;
			this.speed = s;
			this.dir = d;
			this.size = z;
		}

		@Override
		public int compareTo(Shark o) {
			return Integer.compare(o.size, this.size);
		}
	}
}
