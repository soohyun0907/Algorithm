import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 21,576 KB
 * 실행시간 : 127 ms
 * 코드길이 : 1,074 B
 * 소요시간 : 10M
 */

public class Solution_D3_3307_최장증가부분수열_LIS_NlogN {

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

			// LIS 이진탐색방법
			int size = 0, temp;
			for (int i = 0; i < N; i++) {
				temp = -(Arrays.binarySearch(lis, 0, size, num[i])) - 1;
				lis[temp] = num[i];
				if (temp == size)
					++size;
			}
			answer.append('#').append(tc).append(' ').append(size).append('\n');
		} // end test-case;
		System.out.print(answer);
		in.close();
	}

}
