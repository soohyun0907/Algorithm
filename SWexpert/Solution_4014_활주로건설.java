import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 23,872 KB
 * 실행시간 : 128 ms
 * 코드길이 : 2,747 KB
 * 소요시간 : 2H
 */

public class Solution_4014_활주로건설 {

	static int N, X, ans;
	static int[][] map;
	static int[][] delta = { { 1, 0 }, { 0, 1 } }; // 하, 우

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			ans = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // end input

			for (int n = 0; n < N; n++) {
				// 높이 차이가 2이상 나는 경우 1차 걸러내기
				if (checkHeight(n, 0, 1)) { // 가로방향 체크
					if (isAvailable(n, 0, 1)) {
						ans++;
					}
				}
				if (checkHeight(0, n, 0)) { // 세로방향 체크
					if (isAvailable(0, n, 0)) {
						ans++;
					}
				}
			}

			answer.append('#').append(tc).append(' ').append(ans).append('\n');
		} // end test-case

		System.out.println(answer);
		in.close();
	} // end main

	private static boolean checkHeight(int i, int j, int d) {
		int num = map[i][j];

		while (true) {
			i += delta[d][0];
			j += delta[d][1];

			if (i < 0 || i >= N || j < 0 || j >= N)
				break;

			if (Math.abs(map[i][j] - num) > 1)
				return false;
			else if (map[i][j] != num)
				num = map[i][j];
		}

		return true;
	}

	private static boolean isAvailable(int i, int j, int d) {
		int cnt = 1;
		int num = map[i][j];
		boolean flag = false; // 높이가 낮아진 후 경사로 놓을 수 있는지 체크를 위함
		while (true) {
			i += delta[d][0];
			j += delta[d][1];

			if (i < 0 || i >= N || j < 0 || j >= N)
				break;

			if (map[i][j] == num)
				cnt++;
			else if (map[i][j] - num == 1) { // 높이가 높아진 경우
				if (cnt < X) // 높이가 높아졌는데 경사로를 놓기에 길이가 X보다 짧을 경우는 활주로 건설이 불가능
					return false;
				// cnt가 X보다 크면 활주로 건설이 가능. cnt랑 num 값 갱신
				cnt = 1;
				num = map[i][j];
			} else if (map[i][j] - num == -1) { // 높이가 낮아진 경우
				if (!flag) {
					cnt = 1;
					num = map[i][j];
					flag = true;
				} else {
					if (cnt < X)
						return false;
					cnt = 1;
					num = map[i][j];
					flag = true;
				}
			}

			if (flag && cnt == X) {
				cnt = 0;
				flag = false;
			}
		}

		if (flag && cnt < X) // 마지막에 높이가 낮아지고 while문을 탈출한 경우 경사로 설치 여부
			return false;

		return true;
	}

}
