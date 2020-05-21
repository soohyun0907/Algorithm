import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 14,996 KB
 * 시간 : 256 ms
 * 코드길이 : 1,616 B
 * 소요시간 : 2H 15M
 */

public class Main_B_1062_가르침 {

	static int N, K;
	static int answer, alphSize = 26;
	static String[] words;
	static boolean[] alpha;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		answer = Integer.MIN_VALUE;
		words = new String[N];
		alpha = new boolean[alphSize];
		alpha['a' - 'a'] = alpha['c' - 'a'] = alpha['i' - 'a'] = alpha['n' - 'a'] = alpha['t' - 'a'] = true; // acint 미리 카운트

		for (int n = 0; n < N; n++) {
			String tmp = in.readLine();
			words[n] = tmp.substring(4, tmp.length() - 4);
		}

		if (K < 5) {
			System.out.println(0);
		} else if (K >= alphSize) {
			System.out.println(N);
		} else {
			K -= 5;
			recursive(0, 0);
			System.out.println(answer);
		}
	}

	private static void recursive(int index, int count) {
		if (answer == words.length)
			return;

		if (count == K) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				boolean check = true;
				for (int j = 0; j < words[i].length(); j++) {
					if (!alpha[words[i].charAt(j) - 'a']) {
						check = false;
						break;
					}
				}
				if (check)
					cnt++;
			}
			answer = Integer.max(answer, cnt);
			return;
		}

		for (int i = index; i < alphSize; i++) {
			if (!alpha[i]) {
				alpha[i] = true;
				recursive(i, count + 1);
				alpha[i] = false;
			}
		}

	}
}
