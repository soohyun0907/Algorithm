import java.util.Arrays;

public class Solution_모의고사 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] { 1, 2, 3, 4, 5 })));
		System.out.println(Arrays.toString(solution(new int[] { 1, 3, 2, 4, 2 })));
	}

	public static int[] solution(int[] answers) {
		int[] answer;
		int[] p1 = { 1, 2, 3, 4, 5 };
		int[] p2 = { 2, 1, 2, 3, 2, 4, 2, 5 };
		int[] p3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
		int sum[] = new int[3];
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == p1[i % 5]) {
				sum[0]++;
			}
			if (answers[i] == p2[i % 8]) {
				sum[1]++;
			}
			if (answers[i] == p3[i % 10]) {
				sum[2]++;
			}
		}

		int max = Math.max(sum[0], Math.max(sum[1], sum[2]));

		int cnt = 0;
		for (int i = 0; i < 3; i++) {
			if (sum[i] == max)
				cnt++;
		}

		answer = new int[cnt];
		cnt = 0;
		for (int i = 0; i < 3; i++) {
			if (sum[i] == max)
				answer[cnt++] = i + 1;
		}

		return answer;
	}
}
