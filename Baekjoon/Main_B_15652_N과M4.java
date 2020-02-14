package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_15652_N과M4 {

	static int N, M;
	static int[] numbers;
	static boolean[] visited;

	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		numbers = new int[M];
		visited = new boolean[N + 1];

		permutation(0);
		System.out.println(answer);
		in.close();
	}

	private static void permutation(int index) {
		if (index == M) {
			for (int i : numbers) {
				answer.append(i).append(" ");
			}
			answer.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (index != 0 && numbers[index - 1] > i)
				continue;
			numbers[index] = i;
			permutation(index + 1);
		}
	}
}
