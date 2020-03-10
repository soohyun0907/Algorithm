import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author andy cho 1. 구슬은 좌 우로만 움직인다 - 0<=x < W 중 하나가 구슬의 위치 구슬이 떨어지는 위치에 따라결과가
 *         달라진다. --> 순열 문제 -단 같은 자리에 여러번 떨어질 수 있기 때문에 중복 순열 문제
 * 
 *         - 재귀 과정에서 현재 벽돌들의 상태가 재귀의 깊이에 따라 다르게 전달 - 재귀에서 복귀했을 때 반복문에서 사용하는 벽돌의
 *         상태는 이전과 동일해야
 * 
 *         - 빈 공간이 있는 경우 그냥 떨어진다.
 * 
 *         2. 벽돌의 파워에 의해 주변 벽돌이 제거된다 - 4방으로 영향력 받는 벽돌 제거 - 연쇄 작용은 모두 한꺼번에 동작 -
 *         계속 파생되기 때문에 재귀로 작성하면 딱!
 *
 *         3. 빈 공간이 생기면 중력의 방향으로 아래로 떨어진다. - 0인 곳을 위에서 내려와 채워줄 필요
 * 
 *         가지치기: 중간에 블럭이 다 없어지면 더 이상 가볼 필요가 없어진다. --> 순열이 만들어지는 과정에서 그만 둘 수 있도록
 *         처리
 * 
 */
public class Solution_5656_벽돌깨기_수업  {
	static StringBuilder sb = new StringBuilder();
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	static int TC, N, C, R;
	static int brickCnt;// 현재 맵에서 가지고 있는 벽돌의 개수
	static int answer;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));

		TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			sb.append("#").append(t).append(" ");
			/////////////////////////////////////////////////
			StringTokenizer tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
			R = Integer.parseInt(tokens.nextToken());
			map = new int[R][C];
			brickCnt = 0;
			for (int r = 0; r < R; r++) {
				tokens = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					map[r][c] = Integer.parseInt(tokens.nextToken());
					if (map[r][c] != 0) {
						brickCnt++;
					}
				}
			}
			answer = brickCnt;
			/*
			 * for(int [] row: map) { System.out.println(Arrays.toString(row)); }
			 */

			// 구슬을 하나씩 떨어뜨려보자...:
			// 좌표가 달라져야 한다.,--> 중복 순열로 좌표 고르자.
			// 벽돌의 개수도 전달, map 전달
			dropMarble(N, brickCnt, map);
			sb.append(answer);
			//////////////////////////////////////////////////
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static void dropMarble(int r, int brickCnt, int[][] map) {
		if (r == 0) {
			// 솔루션 사용
			answer = Math.min(answer, brickCnt);
			return;
		}
		for (int c = 0; c < C; c++) {
			// 1. map의 복제
			int[][] cloned = cloneMap(map);
			// 2. 해당 컬럼의 맨 처음 벽돌 가져오기
			Brick first = getFirstBrick(c, cloned);
			// 2-1 null --> contunue
			if (first == null) {
				continue;
			}
			// 3. 구슬을 떨어뜨려서 벽돌을 깬다. --> 깨진 벽돌 개수??
			int broken = crash(first, cloned);
			// 3-1 이미 다 벽돌이 깨졌다면?? 정답 = 0, 종료
			if (broken >= brickCnt) {
				answer = 0;
				return;
			}
			// 4. 화면 정리
			cleanMap(cloned);

			// 5. 다음 샷 발사
			dropMarble(r - 1, brickCnt - broken, cloned);
		}

	}

	static void cleanMap(int[][] map) {
		for (int c = 0; c < C; c++) {
			for (int r = R - 1, nr = R - 1; r >= 0; r--) {
				if (map[r][c] != 0) {
					int temp = map[r][c];
					map[r][c] = 0;
					map[nr--][c] = temp;
				}
			}
		}
	}

	private static int crash(Brick first, int[][] map) {
		int broken = 0;
		// 얻어맞은 벽돌 삭제
		map[first.row][first.col] = 0;
		broken++;

		// 주변 벽돌에 영향 주기
		for (int p = 1; p < first.pow; p++) {
			// 사방 탐색
			for (int d = 0; d < dirs.length; d++) {
				int nr = first.row + dirs[d][0] * p;
				int nc = first.col + dirs[d][1] * p;

				if (isIn(nr, nc) && map[nr][nc] != 0) {
					broken += crash(new Brick(nr, nc, map[nr][nc]), map);
				}
			}
		}
		return broken;
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && 0 <= c && r < R && c < C;
	}

	private static Brick getFirstBrick(int c, int[][] map) {
		for (int r = 0; r < R; r++) {
			if (map[r][c] != 0) {
				return new Brick(r, c, map[r][c]);
			}
		}
		return null;
	}

	private static int[][] cloneMap(int[][] map) {
		int[][] temp = new int[R][C];
		for (int r = 0; r < R; r++) {
			temp[r] = map[r].clone(); // deep copy : 내용 복사, 새 객체
			// temp[r] = map[r]; // swallow copy: 단순 레퍼런스 복사
		}
		return temp;
	}

	static class Brick {
		int row, col, pow;

		public Brick(int row, int col, int pow) {
			this.row = row;
			this.col = col;
			this.pow = pow;
		}

		@Override
		public String toString() {
			return "[" + row + ", " + col + ", " + pow + "]";
		}

	}

	static String src = "5\r\n" + "3 10 10\r\n" + "0 0 0 0 0 0 0 0 0 0\r\n" + "1 0 1 0 1 0 0 0 0 0\r\n"
			+ "1 0 3 0 1 1 0 0 0 1\r\n" + "1 1 1 0 1 2 0 0 0 9\r\n" + "1 1 4 0 1 1 0 0 1 1\r\n"
			+ "1 1 4 1 1 1 2 1 1 1\r\n" + "1 1 5 1 1 1 1 2 1 1\r\n" + "1 1 6 1 1 1 1 1 2 1\r\n"
			+ "1 1 1 1 1 1 1 1 1 5\r\n" + "1 1 7 1 1 1 1 1 1 1\r\n" + "2 9 10\r\n" + "0 0 0 0 0 0 0 0 0\r\n"
			+ "0 0 0 0 0 0 0 0 0\r\n" + "0 1 0 0 0 0 0 0 0\r\n" + "0 1 0 0 0 0 0 0 0\r\n" + "1 1 0 0 1 0 0 0 0\r\n"
			+ "1 1 0 1 1 1 0 1 0\r\n" + "1 1 0 1 1 1 0 1 0\r\n" + "1 1 1 1 1 1 1 1 0\r\n" + "1 1 3 1 6 1 1 1 1\r\n"
			+ "1 1 1 1 1 1 1 1 1\r\n" + "3 6 7\r\n" + "1 1 0 0 0 0\r\n" + "1 1 0 0 1 0\r\n" + "1 1 0 0 4 0\r\n"
			+ "4 1 0 0 1 0\r\n" + "1 5 1 0 1 6\r\n" + "1 2 8 1 1 6\r\n" + "1 1 1 9 2 1\r\n" + "4 4 15\r\n"
			+ "0 0 0 0 \r\n" + "0 0 0 0 \r\n" + "0 0 0 0 \r\n" + "1 0 0 0 \r\n" + "1 0 0 0 \r\n" + "1 0 0 0 \r\n"
			+ "1 0 0 0 \r\n" + "1 0 5 0 \r\n" + "1 1 1 0 \r\n" + "1 1 1 9 \r\n" + "1 1 1 1 \r\n" + "1 6 1 2 \r\n"
			+ "1 1 1 5 \r\n" + "1 1 1 1 \r\n" + "2 1 1 2 \r\n" + "4 12 15\r\n" + "9 9 9 9 9 9 9 9 9 9 9 9\r\n"
			+ "9 9 9 9 9 9 9 9 9 9 9 9\r\n" + "9 9 9 9 9 9 9 9 9 9 9 9\r\n" + "9 9 9 9 9 9 9 9 9 9 9 9\r\n"
			+ "9 9 9 9 9 9 9 9 9 9 9 9\r\n" + "9 9 9 9 9 9 9 9 9 9 9 9\r\n" + "9 9 9 9 9 9 9 9 9 9 9 9\r\n"
			+ "9 9 9 9 9 9 9 9 9 9 9 9\r\n" + "9 9 9 9 9 9 9 9 9 9 9 9\r\n" + "9 9 9 9 9 9 9 9 9 9 9 9\r\n"
			+ "9 9 9 9 9 9 9 9 9 9 9 9\r\n" + "9 9 9 9 9 9 9 9 9 9 9 9\r\n" + "9 9 9 9 9 9 9 9 9 9 9 9\r\n"
			+ "9 9 9 9 9 9 9 9 9 9 9 9\r\n" + "9 9 9 9 9 9 9 9 9 9 9 9";
}
