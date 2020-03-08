import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 46,980 KB
 * 시간 : 316 ms
 * 코드길이 : 1125 B
 */

public class Main_B_15665_N과M11 {
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
				numbers[index] = input[i];
				permutation(index + 1);
			}
		}
	}
}
