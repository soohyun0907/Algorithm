
public class Solution_타겟넘버 {

	public static void main(String[] args) {
		int[] numbers = { 1, 1, 1, 1, 1 };
		System.out.println(solution(numbers, 3));
	}

	static int answer;

	public static int solution(int[] numbers, int target) {
		answer = 0;
		dfs(numbers, target, 0, 0);
		return answer;
	}

	private static void dfs(int[] numbers, int target, int sum, int index) {
		if (index == numbers.length) {
			if (sum == target)
				answer++;
			return;
		}

		dfs(numbers, target, sum + numbers[index], index + 1);
		dfs(numbers, target, sum - numbers[index], index + 1);
	}
}
