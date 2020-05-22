import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 20,512 KB
 * 실행시간 : 736 ms
 * 코드길이 : 1,464 B
 * 소요시간 : 1H 20M
 */

public class Solution_D4_3234_준환이의양팔저울 {

	static int N, cnt, sum;
	static int[] m;
	private static int exp[] = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512 };
    private static int fact[] = { 0, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(in.readLine());
			m = new int[N];
			sum = 0;
			cnt = 0;
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				m[i] = Integer.parseInt(st.nextToken());
				sum += m[i];
			}
			
			recur(0, 0, 0, 0);
			answer.append('#').append(tc+1).append(' ').append(cnt).append('\n');
		}
		
		System.out.print(answer);
		in.close();
	}

	private static void recur(int index, int left, int right, int flag) {
		if (left < right)
			return;
		if (index == N) {
			cnt++;
			return;
		}
		
		if(left >= sum-left) {
			cnt += exp[N-index]*fact[N-index];
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((flag & (1 << i)) == 0) {
				recur(index + 1, left + m[i], right, (flag | (1 << i)));
				recur(index + 1, left, right + m[i], (flag | (1 << i)));
			}
		}
	}

}
