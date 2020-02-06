package pretest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_5213_햄버거다이어트 {

	static int N, L, score, maxScore = 0;
	static int[] Ti;
	static int[] Ki;
	static StringBuilder ans = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		// 1. 테스트 케이스 T 입력 받기
		StringTokenizer st;
		int T = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			// 2. 재료의 수 N과 제한 칼로리 L 입력받기
			score = 0;
			maxScore = 0;
			st = new StringTokenizer(bf.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			// 3. N개의 줄로 재료에 대한 맛의 점수 Ti와 칼로리 Ki 입력받기
			Ti = new int[N];
			Ki = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine(), " ");
				Ti[i] = Integer.parseInt(st.nextToken());
				Ki[i] = Integer.parseInt(st.nextToken());
			}
			// 제한 칼로리 이하의 조합 중에서 가장 맛에 대한 점수가 높은 햄버거의 점수 출력
			// 같은 재료를 여러번 사용할 수 없음.
			hamburger(0, 0, 0);

			System.out.println("#" + test_case + " " + maxScore);
		}

		bf.close();
	}

	private static void hamburger(int index, int tasteSum, int cal) {

		if (cal > L)
			return;
		if (index == N) {
			if (cal <= L) {
				if (maxScore < tasteSum) {
					maxScore = tasteSum;
				}
			}
			return;
		}

		// 선택한 경우
		hamburger(index + 1, tasteSum + Ti[index], cal + Ki[index]);

		// 선택하지 않은 경우
		hamburger(index + 1, tasteSum, cal);
	}

}