import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 13,552 KB 
 * 시간 : 120 ms 
 * 코드길이 : 1,513 B 
 * 소요시간 : 30M
 */

public class Main_B_1062_가르침_2차 {

	static int N, K, alpha;
	static int answer, alphSize = 26;
	static int[] words;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		answer = Integer.MIN_VALUE;
		words = new int[N];

		for (int n = 0; n < N; n++) {
			String tmp = in.readLine();
			for (int i = 4; i < tmp.length() - 4; i++) {
				words[n] |= (1 << (tmp.charAt(i) - 'a'));
			}
		}

		if (K < 5) {
			System.out.println(0);
		} else if (K >= alphSize) {
			System.out.println(N);
		} else {
			alpha = 0;
			alpha |= (1 << ('a' - 'a'));
			alpha |= (1 << ('c' - 'a'));
			alpha |= (1 << ('i' - 'a'));
			alpha |= (1 << ('n' - 'a'));
			alpha |= (1 << ('t' - 'a'));
			K -= 5;
			recursive(0, 0);
			System.out.println(answer);
		}
	}

	private static void recursive(int index, int count) {
		if (answer == N)
			return;

		if (count == K) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if ((alpha | words[i]) == alpha)
					cnt++;
			}
			answer = Integer.max(answer, cnt);
			return;
		}

		for (int i = index; i < alphSize; i++) {
			if ((alpha & (1 << i)) == 0) {
				alpha |= (1 << i);
				recursive(i, count + 1);
				alpha ^= (1 << i);
			}
		}

	}
}
