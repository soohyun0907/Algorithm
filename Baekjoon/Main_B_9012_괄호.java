import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @author soohyun
 * 메모리 : 13,156 KB
 * 시간 : 96 ms
 * 코드길이 : 1,003 B
 * 소요시간 : 10m
 */

public class Main_B_9012_괄호 {

	static boolean flag;
	static String str;
	static Stack<Character> stack;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 0; tc < T; tc++) {
			str = in.readLine();
			stack = new Stack<Character>();
			flag = false;

			for (int i = 0; i < str.length(); i++) {
				switch (str.charAt(i)) {
				case '(':
					stack.add('(');
					break;
				case ')':
					if (!stack.empty() && stack.peek() == '(')
						stack.pop();
					else
						flag = true;
					break;
				}
				if (flag)
					break;
			}

			if (flag || !stack.empty()) {
				answer.append("NO\n");
				continue;
			}

			answer.append("YES\n");
		}

		System.out.println(answer);
	}

}
