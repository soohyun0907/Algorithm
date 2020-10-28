import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 29,340 KB
 * 시간 : 424 ms
 * 코드길이 : 914 B
 * 소요시간 : 30M
 */

public class Main_B_2467_용액 {

	static int N, L, R;
	static int min = Integer.MAX_VALUE;
	static int[] num;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		num = new int[N];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0, right = N - 1, sum;
		while (left < right) {
			sum = num[left] + num[right];
			if (min > Math.abs(sum)) {
				min = Math.abs(sum);
				L = left;
				R = right;
			}

			if (sum < 0)
				left++;
			else if (sum > 0)
				right--;
			else
				break;
		}

		System.out.println(num[L] + " " + num[R]);
	}

}
