import java.util.ArrayList;
import java.util.Arrays;

public class Solution_두개뽑아서더하기 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] { 2, 1, 3, 4, 1 })));
		System.out.println(Arrays.toString(solution(new int[] { 5, 0, 2, 7 })));
	}

	public static int[] solution(int[] numbers) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int sum;
		for (int i = 0; i < numbers.length-1; i++) {
			for (int j = i+1; j < numbers.length; j++) {
				sum = numbers[i]+numbers[j];
				if(!list.contains(sum))
					list.add(sum);
			}
		}
		
		int size = list.size();
		int[] answer = new int[size];
		for (int i = 0; i < size; i++) {
			answer[i] = list.get(i);
		}
		
		Arrays.sort(answer);
		return answer;
	}
}
