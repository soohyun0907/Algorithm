import java.util.Arrays;

public class Solution_주식가격 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] { 1, 2, 3, 2, 3 })));
	}

	public static int[] solution(int[] prices) {
		int size = prices.length;
		int[] answer = new int[size];

		for (int i = 0; i < size; i++) {
			answer[i] = size - 1 - i;
		}

		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if (prices[i] > prices[j]) {
					answer[i] -= size - j - 1;
					break;
				}
			}
		}

		return answer;
	}
}
