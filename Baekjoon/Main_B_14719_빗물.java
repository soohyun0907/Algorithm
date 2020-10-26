import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,348 KB
 * 시간 : 104 ms
 * 코드길이 : 942 B
 * 소요시간 : 10M
 */

public class Main_B_14719_빗물 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(in.readLine());
		int[] rain = new int[W];
		for (int i = 0; i < W; i++) {
			rain[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		int left, right;
		for (int i = 1; i < W - 1; i++) {
			left = rain[i];
			right = rain[i];
			for (int j = 0; j < i; j++) {
				left = Math.max(left, rain[j]);
			}

			for (int j = W - 1; j > i; j--) {
				right = Math.max(right, rain[j]);
			}

			answer += Math.min(left, right) - rain[i];
		}

		System.out.println(answer);
	}

}
