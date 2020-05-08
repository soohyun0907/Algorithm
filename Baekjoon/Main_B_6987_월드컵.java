import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 12,976 KB
 * 시간 : 72 ms
 * 코드길이 : 1,739 B
 * 소요시간 : 1H
 */

public class Main_B_6987_월드컵 {

	static int tc = 4, team = 6;
	static boolean[] t_case = new boolean[tc];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < tc; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int[][] scores = new int[3][team];
			int sum = 0;
			for (int j = 0; j < team; j++) {
				scores[0][j] = Integer.parseInt(st.nextToken()); // 승
				scores[1][j] = Integer.parseInt(st.nextToken()); // 무
				scores[2][j] = Integer.parseInt(st.nextToken()); // 패
				sum += scores[0][j] + scores[1][j] + scores[2][j];
			}

			if (sum != 30)
				t_case[i] = false;
			else
				checkPossible(scores, i, 0, 1);

		}

		for (int i = 0; i < tc; i++) {
			if (t_case[i])
				System.out.print("1 ");
			else
				System.out.print("0 ");
		}
		in.close();
	} // end main

	private static void checkPossible(int[][] scores, int i, int t1, int t2) {
		if (t1 == 6)
			t_case[i] = true;
		else if (t2 == 6)
			checkPossible(scores, i, t1 + 1, t1 + 2);
		else {
			scores[0][t1]--;
			scores[2][t2]--;
			if (scores[0][t1] >= 0 && scores[2][t2] >= 0)
				checkPossible(scores, i, t1, t2 + 1);
			scores[0][t1]++;
			scores[2][t2]++;
			scores[1][t1]--;
			scores[1][t2]--;
			if (scores[1][t1] >= 0 && scores[1][t2] >= 0)
				checkPossible(scores, i, t1, t2 + 1);
			scores[1][t1]++;
			scores[1][t2]++;
			scores[2][t1]--;
			scores[0][t2]--;
			if (scores[2][t1] >= 0 && scores[0][t2] >= 0)
				checkPossible(scores, i, t1, t2 + 1);
			scores[2][t1]++;
			scores[0][t2]++;
		}
	}

}
