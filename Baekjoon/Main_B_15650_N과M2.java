package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 
 */
public class Main_B_15650_N과M2 {

	static int N, M;
	static int[] numbers;
	static boolean[] selected;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		// 1부터 N까지의 자연수 중에서 중복없이 M개의 수를 고르기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[M];
		selected = new boolean[N + 1];

		permutation(0);

		System.out.println(answer);
		in.close();
	}

	public static void permutation(int index) {
		if (index == M) {
			for (int i : numbers)
				answer.append(i + " ");
			answer.append('\n');
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (selected[i])
				continue;
			if (index != 0 && numbers[index - 1] > i)
				continue;

			numbers[index] = i;
			selected[i] = true;
			permutation(index + 1);
			selected[i] = false;
		}
	}
}
