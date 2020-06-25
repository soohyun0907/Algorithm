import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 14,212 KB
 * 시간 : 104 ms
 * 코드길이 : 1,714 B
 * 소요시간 : 40M
 */

public class Main_B_14890_경사로 {

	static int N, L;
	static int[][] map;
	static int[][] delta = { { 1, 0 }, { 0, 1 } }; // 하, 우

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (checkRoad(i, 0, 1))
				cnt++;
			if (checkRoad(0, i, 0))
				cnt++;
		}

		System.out.println(cnt);
	} // end main

	private static boolean checkRoad(int i, int j, int d) {
		int num = map[i][j];
		int cnt = 1;
		boolean lower = false;

		while (true) {
			i += delta[d][0];
			j += delta[d][1];

			if (i < 0 || i >= N || j < 0 || j >= N)
				break;

			if (Math.abs(num - map[i][j]) > 1)
				return false;

			if (num == map[i][j]) {
				cnt++;
			} else if (num - map[i][j] == -1) { // 높이가 높아진 경우
				if (cnt < L)
					return false;
				cnt = 1;
				num = map[i][j];
			} else if (num - map[i][j] == 1) { // 높이가 낮아진 경우
				if (lower && cnt < L) {
					return false;
				}
				cnt = 1;
				lower = true;
				num = map[i][j];
			}

			if (lower && cnt == L) {
				cnt = 0;
				lower = false;
			}
		} // end while

		if (lower && cnt < L)
			return false;

		return true;
	} // end checkRoad()
}
