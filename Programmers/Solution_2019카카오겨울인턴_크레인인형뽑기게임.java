import java.util.Stack;

public class Solution_2019카카오겨울인턴_크레인인형뽑기게임 {

	public static void main(String[] args) {
		System.out.println(solution(new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 },
			{ 4, 2, 4, 4, 2 }, { 3, 5, 1, 3, 1 } }, new int[] { 1, 5, 3, 5, 1, 2, 1, 4 }));
	}

	public static int solution(int[][] board, int[] moves) {
		int answer = 0;

		Stack<Integer> stack = new Stack<Integer>();

		int cur, doll;
		for (int i = 0; i < moves.length; i++) {
			cur = moves[i] - 1;
			for (int j = 0; j < board.length; j++) {
				if (board[j][cur] > 0) {
					doll = board[j][cur];
					board[j][cur] = 0;
					if (!stack.isEmpty() && stack.peek() == doll) {
						answer += 2;
						stack.pop();
					} else {
						stack.add(doll);
					}
					break;
				}
			}
		}

		return answer;
	}
}
