package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_15649_N과M1 {
	static int N, M;
	static int[] numbers;
	static boolean[] selected;
	static StringBuilder ans = new StringBuilder();

	// 1,2,3,... N으로 만들 수 있는 N 자리수 순열
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		numbers = new int[M];
		selected = new boolean[N + 1]; // 기본 초기화 상태는 false
		permutation(0);

		System.out.println(ans);
	}

	// 재귀호출로 순열 구현하기
	private static void permutation(int index) {

		if (index == M) {
			for (int i : numbers)
				ans.append(i + " ");
			ans.append('\n');
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (selected[i])
				continue;

			numbers[index] = i;
			selected[i] = true;
			permutation(index + 1);
			selected[i] = false; // flag를 되돌려 놓기
		}

	}

}
