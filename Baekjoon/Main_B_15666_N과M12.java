import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,388 KB
 * 시간 : 92 ms
 * 코드길이 : 1,303 B
 */

public class Main_B_15666_N과M12 {
	static int N, M;
	static int[] input, numbers;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new int[N];
		numbers = new int[M];

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		permutation(0);
		System.out.print(answer);
		in.close();
	}

	private static void permutation(int index) {
		if (index == M) {
			for (int n : numbers)
				answer.append(n).append(" ");
			answer.append("\n");
			return;
		}
		for (int i = 0; i < N; i++) {
			if (numbers[index] != input[i]) {
				if (index == 0) {
					numbers[index] = input[i];
					permutation(index + 1);
				} else {
					if (numbers[index - 1] <= input[i]) {
						numbers[index] = input[i];
						permutation(index + 1);
					}
				}
			}
		}
		numbers[index] = 0;
	}
}
