import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @author soohyun
 * 메모리 : 14,460 KB
 * 시간 : 136 ms
 * 코드길이 : 1,125 B
 * 소요시간 : 30M
 */

public class Main_B_1918_후위표기식 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] input = in.readLine().toCharArray();

		char cur, top;
		Stack<Character> stack = new Stack<Character>();
		StringBuilder answer = new StringBuilder();

		for (int i = 0; i < input.length; i++) {
			cur = input[i];
			if (Character.isAlphabetic(cur))
				answer.append(cur);
			else if (cur == ')') {
				while (!stack.isEmpty() && (top = stack.pop()) != '(')
					answer.append(top);
			} else {
				while (!stack.isEmpty() && getPriority(stack.peek(), true) >= getPriority(cur, false))
					answer.append(stack.pop());
				stack.push(cur);
			}
		}

		while (!stack.isEmpty())
			answer.append(stack.pop());
		
		System.out.println(answer);
	}

	private static int getPriority(char ch, boolean isInStack) {
		if (ch == '+' || ch == '-')
			return 1;
		else if (ch == '*' || ch == '/')
			return 2;
		else
			return isInStack ? 0 : 3;
	}
}
