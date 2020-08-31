import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3378_D4_스타일리쉬들여쓰기 {

	static int p, q;
	static int[] ans;
	static char[][] master, mine;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(in.readLine());

		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(in.readLine());
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());
			master = new char[p][];
			mine = new char[q][];
			ans = new int[q];
			Arrays.fill(ans, -2);

			for (int i = 0; i < p; i++) {
				master[i] = in.readLine().toCharArray();
			}

			for (int i = 0; i < q; i++) {
				mine[i] = in.readLine().toCharArray();
			}

			for (int R = 1; R < 21; R++) {
				for (int C = 1; C < 21; C++) {
					for (int S = 1; S < 21; S++) {
						if (findRCS(R, C, S))
							findAns(R, C, S);
					}
				}
			}

			answer.append('#').append(t);
			for (int i = 0; i < q; i++) {
				answer.append(' ').append(ans[i]);
			}
			answer.append('\n');
		} // end test-case

		System.out.println(answer);
	} // end main

	private static void findAns(int r, int c, int s) {
		int rCnt = 0, cCnt = 0, sCnt = 0, intent = 0;

		for (int i = 0; i < q; i++) {
			intent = rCnt * r + cCnt * c + sCnt * s;

			if (ans[i] == -2) {
				ans[i] = intent;
			} else if (ans[i] != intent) {
				ans[i] = -1;
			}

			for (char ch : mine[i]) {
				switch (ch) {
				case '(':
					rCnt++;
					break;
				case ')':
					rCnt--;
					break;
				case '{':
					cCnt++;
					break;
				case '}':
					cCnt--;
					break;
				case '[':
					sCnt++;
					break;
				case ']':
					sCnt--;
					break;
				}
			}
		}
	}

	private static boolean findRCS(int r, int c, int s) {
		int cnt = 0, rCnt = 0, cCnt = 0, sCnt = 0, intent = 0;

		for (int i = 0; i < p; i++) {
			cnt = 0;
			for (char ch : master[i]) {
				if (ch == '.')
					cnt++;
				else
					break;
			}

			intent = rCnt * r + cCnt * c + sCnt * s;
			if (intent != cnt) {
				return false;
			}

			for (char ch : master[i]) {
				switch (ch) {
				case '(':
					rCnt++;
					break;
				case ')':
					rCnt--;
					break;
				case '{':
					cCnt++;
					break;
				case '}':
					cCnt--;
					break;
				case '[':
					sCnt++;
					break;
				case ']':
					sCnt--;
					break;
				}
			}
		}
		return true;
	}

}
