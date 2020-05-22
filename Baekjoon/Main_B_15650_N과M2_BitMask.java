import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 12,924 KB
 * 시간 : 76 ms
 * 코드길이 : 919 B
 * 소요시간 : 15M
 */

public class Main_B_15650_N과M2_BitMask {

	static int N, M;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		permutation(0, 1, 0);
		System.out.println(answer);
	}

	private static void permutation(int index, int n, int flag) {
		if (index == M) {
			for (int i = 1; i <= N; i++) {
				if ((flag & (1 << i)) != 0)
					answer.append(i).append(' ');
			}
			answer.append('\n');
			return;
		}

		for (int i = n; i <= N; i++) {
			if ((flag & (1 << i)) == 0) {
				permutation(index + 1, i, (flag | (1 << i)));
			}
		}
	}

}
