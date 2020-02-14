package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_15655_N과M6 {
	static int N, M;
	static int[] numbers;
	static int[] nm;
	static boolean[] visited;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		numbers = new int[N];
		visited = new boolean[N];
		nm = new int[M];

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers);

		permutation(0);
		System.out.println(answer);
		in.close();
	}

	private static void permutation(int index) {
		if (index == M) {
			for (int i : nm) {
				answer.append(i).append(" ");
			}
			answer.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			if (index != 0 && nm[index - 1] > numbers[i])
				continue;

			nm[index] = numbers[i];
			visited[i] = true;
			permutation(index + 1);
			visited[i] = false;
		}
	}

}
