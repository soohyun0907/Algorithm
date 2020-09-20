import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 15,616 KB
 * 시간 : 1,140 ms
 * 코드길이 : 1,718 B
 * 소요시간 : -
 */

public class Main_B_15684_사다리조작 {

	static int N, M, H, ans = 4;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H + 2][N + 1];

		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			map[a][b] = 1; // 왼 -> 오 가는 경우
			map[a][b + 1] = 2; // 오 -> 왼 가는 경
		} // end input

		solve(1, 1, 0);

		if (ans == 4)
			System.out.println(-1);
		else
			System.out.println(ans);
	} // end main

	private static void solve(int x, int y, int cnt) {
		if (cnt > 3) return; // 기저조건

		if (isPossible()) {
			ans = Math.min(cnt, ans);
			return;
		}

		for (int i = x; i < H + 1; i++) {
			for (int j = 1; j < N; j++) {
				if (map[i][j] != 0 || map[i][j + 1] != 0)
					continue;

				// 선을 그어주기
				map[i][j] = 1;
				map[i][j + 1] = 2;
				solve(i, j, cnt + 1);
				// 되돌리기
				map[i][j] = 0;
				map[i][j + 1] = 0;
			}
		}
	}

	private static boolean isPossible() {
		for (int i = 1; i < N + 1; i++) {
			int x = 0;
			int y = i;

			while (x < H + 1) {
				if (map[x + 1][y] == 1)
					y++;
				else if (map[x + 1][y] == 2)
					y--;

				// 밑으로 이동
				x++;
			}
			if (y != i)
				return false;
		}

		return true;
	}

}
