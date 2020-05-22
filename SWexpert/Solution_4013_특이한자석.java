import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 18,696 KB
 * 실행시간 : 112 ms
 * 코드길이 : 2,582 B
 * 소요시간 : 2H
 */

public class Solution_4013_특이한자석 {

	static int N = 4; // 자석의 갯수
	static int nal = 8; // 자석의 날의 갯수
	static int[][] magnet;
	static int[] magDir = new int[N];
	static boolean[] visited = new boolean[N];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			int K = Integer.parseInt(in.readLine());
			magnet = new int[N][nal];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < nal; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int n = Integer.parseInt(st.nextToken()) - 1; // 자석의 번호
				int dir = Integer.parseInt(st.nextToken()); // 1:시계방향, -1: 반시계방향
				checkMagnet(n, dir);
				for (int j = 0; j < N; j++) {
					if (magDir[j] == 0)
						continue;
					moveMagnet(j, magDir[j]);
				}
				Arrays.fill(magDir, 0);
				Arrays.fill(visited, false);
			}
			int result = 0;
			for (int i = 0; i < N; i++) {
				result += magnet[i][0] * Math.pow(2, i);
			}
			answer.append('#').append(tc + 1).append(' ').append(result).append('\n');
		} // end test-case

		System.out.println(answer);
		in.close();
	} // end main

	private static void checkMagnet(int n, int dir) {
		if (visited[n])
			return;
		magDir[n] = dir;
		visited[n] = true;
		if (dir != 0) {
			if (n == 0 && magnet[n + 1][6] != magnet[n][2]) {
				checkMagnet(n + 1, dir * (-1));
			} else if (n == 3 && magnet[n - 1][2] != magnet[n][6]) {
				checkMagnet(n - 1, dir * (-1));
			} else if (n == 1 || n == 2) {
				if (magnet[n - 1][2] != magnet[n][6])
					checkMagnet(n - 1, dir * (-1));
				if (magnet[n + 1][6] != magnet[n][2])
					checkMagnet(n + 1, dir * (-1));
			}
		}
	}

	private static void moveMagnet(int n, int dir) {
		int cur, next;
		switch (dir) {
		case 1: // 시계방향
			cur = magnet[n][0];
			for (int i = 0; i < nal - 1; i++) {
				next = magnet[n][i + 1];
				magnet[n][i + 1] = cur;
				cur = next;
			}
			magnet[n][0] = cur;
			break;
		case -1: // 반시계방향
			cur = magnet[n][7];
			for (int i = nal - 1; i > 0; i--) {
				next = magnet[n][i - 1];
				magnet[n][i - 1] = cur;
				cur = next;
			}
			magnet[n][7] = cur;
			break;
		}
	}

}
