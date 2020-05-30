import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 28,152 KB
 * 실행시간 : 152 ms
 * 코드길이 : 1,150 B
 * 소요시간 : 10M
 */

public class Solution_D3_3307_최장증가부분수열_LIS_DP {

	static int N, num[], lis[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc < T + 1; tc++) {
			N = Integer.parseInt(in.readLine());
			num = new int[N];
			lis = new int[N];
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}

			// LIS DP방법
			int maxIndex = 0;
			for (int i = 0; i < N; i++) {
				lis[i] = 1; // lis배열 초기화
				for (int j = 0; j < i; j++) {
					if (num[i] > num[j] && lis[i] < lis[j] + 1) {
						lis[i] = lis[j] + 1;
						if (lis[maxIndex] < lis[i])
							maxIndex = i;
					}
				}
			}
			answer.append('#').append(tc).append(' ').append(lis[maxIndex]).append('\n');
		} // end test-case;
		System.out.print(answer);
		in.close();
	} // end main
}
