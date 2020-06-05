import java.util.Arrays;

public class Solution_완주하지못한선수 {

	public static void main(String[] args) {
		String[] participant = {"leo", "kiki", "eden"};
		String[] completion= {"kiki", "eden"};
		System.out.println(solution(participant,completion));
	}
	
	public static String solution(String[] participant, String[] completion) {
		String answer = "";
		Arrays.sort(participant);
		Arrays.sort(completion);
		boolean find = false;
		for (int i = 0; i < completion.length; i++) {
			find = false;
			if (participant[i].equals(completion[i])) {
				find = true;
			}
			if (!find) {
				answer += participant[i];
				break;
			}
		}
		if (find)
			answer += participant[participant.length - 1];
		return answer;
    }

}
