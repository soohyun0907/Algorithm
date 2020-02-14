package baekjoon;

import java.util.Scanner;

public class Main_B_10974_모든순열 {

	static int N;
	static int[] numbers;
	static boolean[] selected;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		N = in.nextInt();
		numbers = new int[N];
		selected = new boolean[N + 1];

		permutation(1);
		System.out.println(answer);
		in.close();
	}

	private static void permutation(int index) {
		if (index == N + 1) {
			for (int i : numbers) {
				answer.append(i + " ");
			}
			answer.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (selected[i])
				continue;

			numbers[index - 1] = i;
			selected[i] = true;
			permutation(index + 1);
			selected[i] = false;
		}
	}

}
