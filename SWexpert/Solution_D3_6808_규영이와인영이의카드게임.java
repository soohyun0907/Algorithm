import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 20,524 KB
 * 실행시간 : 2,942 ms
 * 코드길이 : 1,691 B
 * 소요시간 : 30m
 */

public class Solution_D3_6808_규영이와인영이의카드게임 {

	static int size = 9, win, lose;
	static int[] gyu, inyoungNums, inyoung;
	static boolean[] numbers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc < T + 1; tc++) {
			st = new StringTokenizer(in.readLine(), " ");
			gyu = new int[size];
			inyoung = new int[size];
			inyoungNums = new int[size];
			numbers = new boolean[size * 2 + 1];
			win = 0;
			lose = 0;

			for (int i = 0; i < size; i++) {
				gyu[i] = Integer.parseInt(st.nextToken());
				numbers[gyu[i]] = true;
			}

			int cnt = 0;
			for (int i = 1; i < size * 2 + 1; i++) {
				if (!numbers[i])
					inyoungNums[cnt++] = i;
			}

			recur(0, 0);

			answer.append('#').append(tc).append(' ').append(win).append(' ').append(lose).append('\n');
		} // end test-case

		System.out.println(answer);
	} // end main

	private static void recur(int cnt, int flag) {
		if (cnt == 9) {
			whoWin();
			return;
		}

		for (int i = 0; i < size; i++) {
			if ((flag & (1 << i)) == 0) {
				inyoung[cnt] = inyoungNums[i];
				recur(cnt + 1, (flag | (1 << i)));
			}
		}
	}

	private static void whoWin() {
		int gy = 0, iy = 0;

		for (int i = 0; i < size; i++) {
			if (gyu[i] > inyoung[i])
				gy += gyu[i] + inyoung[i];
			else
				iy += gyu[i] + inyoung[i];
		}

		if (gy > iy)
			win++;
		else if (gy < iy)
			lose++;
	}

}
