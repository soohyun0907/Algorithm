import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 17,288 KB 
 * 시간 : 156 ms 
 * 코드길이 : 863 B 
 * 소요시간 : 30M
 */

public class Main_B_2003_수들의합2 {

	static int N, M;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N];
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0, right = 0, sum = A[0];
		int cnt = 0;
		while (right < N) {
			if (sum == M) {
				cnt++;
			}

			if (sum < M) {
				right++;
				if (right < N)
					sum += A[right];
			} else {
				sum -= A[left++];
			}
		}

		System.out.println(cnt);
	}

}
