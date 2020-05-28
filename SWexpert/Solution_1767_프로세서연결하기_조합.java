import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 22,180 KB
 * 실행시간 : 143 ms
 * 코드길이 : 2,785 B
 * 가지치기 제외하면 414 ms
 */

public class Solution_1767_프로세서연결하기_조합 {

	static int N, max, min, totalCnt, map[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static ArrayList<int[]> list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			list = new ArrayList<int[]>();
			max = 0;
			min = Integer.MAX_VALUE;
			totalCnt = 0;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if ((i == 0 || i == N - 1 || j == 0 || j == N - 1) && map[i][j] == 1)
						continue; // 가장자리 코어는 패스
					// 코어 위치 리스트에 추가
					if (map[i][j] == 1) {
						list.add(new int[] { i, j });
						totalCnt++;
					}
				}
			}

			go(0, 0);
			answer.append('#').append(t).append(' ').append(min).append('\n');
		} // end test-case
		System.out.print(answer);
		in.close();
	} // end main

	// 코어를 부분집합으로 백트랙킹 : 현재코어
	private static void go(int index, int cCnt) {
		if (totalCnt - index + cCnt < max) return; // 남은 코어수 (totalCnt-index)
		
		int res = getLength();
		if (max < cCnt) {
			max = cCnt;
			min = res;
		} else if (max == cCnt) {
			if (min > res)
				min = res;
		}

		for (int i = index; i < totalCnt; i++) {
			int[] cur = list.get(i);
			int x = cur[0];
			int y = cur[1];
			// 포함
			for (int d = 0; d < 4; d++) {
				// 현 방향으로 전선을 놓는 것이 가능한지 체크하고
				if (isAvailable(x, y, d)) {
					// 가능하다면 현 방향으로 전선 놓기(2)
					setStatus(x, y, d, 2);
					// 다음 코어로 넘어감
					go(i + 1, cCnt + 1);
					// 현방향으로 전선 놓았던거 되돌리기(0)
					setStatus(x, y, d, 0);
				}
			}
		}
	}

	private static boolean isAvailable(int x, int y, int d) {
		int nx = x, ny = y;

		while (true) {
			nx += dx[d];
			ny += dy[d];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				break;

			if (map[nx][ny] >= 1)
				return false;
		}
		return true;
	}

	private static void setStatus(int x, int y, int d, int s) {
		int nx = x, ny = y;

		while (true) {
			nx += dx[d];
			ny += dy[d];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				break;

			map[nx][ny] = s;
		}
	}

	private static int getLength() {
		int lCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2)
					lCnt++;
			}
		}

		return lCnt;
	}
}
