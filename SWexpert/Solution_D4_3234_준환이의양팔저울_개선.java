import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 20,188 KB 
 * 실행시간 : 610 ms 
 * 코드길이 : 1,641 B 
 */

public class Solution_D4_3234_준환이의양팔저울_개선 {

	static int N, cnt, sum;
	static int[] m;
	private static int[] pow;
	private static int[] facto;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(in.readLine());
			m = new int[N];
			facto = new int[N + 1];
			pow = new int[N + 1];
			facto[0] = facto[1] = pow[0] = 1;
			sum = 0;
			cnt = 0;
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				m[i] = Integer.parseInt(st.nextToken());
				sum += m[i];
				facto[i + 1] = facto[i] * (i + 1);
				pow[i + 1] = pow[i] * 2;
			}

			recur(0, 0, 0, 0, sum);
			answer.append('#').append(tc + 1).append(' ').append(cnt).append('\n');
		}

		System.out.print(answer);
		in.close();
	}

	private static void recur(int index, int left, int right, int flag, int remain) {
		if (remain + right <= left) { // 남은 추를 다 오른쪽에 놔도 왼쪽보다 안무거울 경우 가지치기
			cnt += facto[N - index] * pow[N - index];
			return;
		}

		if (index == N) {
			cnt++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((flag & (1 << i)) == 0) {
				recur(index + 1, left + m[i], right, (flag | (1 << i)), remain - m[i]);
				if (right + m[i] <= left)
					recur(index + 1, left, right + m[i], (flag | (1 << i)), remain - m[i]);
			}
		}
	}

}
