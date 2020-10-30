import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 29,208 KB
 * 시간 : 440 ms
 * 코드길이 : 936 B
 * 소요시간 : 10M
 */

public class Main_B_2470_두용액 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] liq = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			liq[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(liq);
		int left = 0, right = N - 1, sum = 0, min = Integer.MAX_VALUE, L = 0, R = N - 1;
		while (left < right) {
			sum = liq[left] + liq[right];
			if (Math.abs(sum) < min) {
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

		System.out.println(liq[L] + " " + liq[R]);
	}

}
