package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_D3_8931_제로 {

	static Stack<Integer> stack = new Stack<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder answer = new StringBuilder();

		int TC = Integer.parseInt(st.nextToken());

		for (int tcNum = 1; tcNum <= TC; tcNum++) {
			answer.append("#").append(tcNum).append(" ");
			st = new StringTokenizer(in.readLine());
			int K = Integer.parseInt(st.nextToken());
			int sum = 0;
			stack.clear();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(in.readLine());
				int num = Integer.parseInt(st.nextToken());
				if (num == 0) {
					stack.pop();
				} else {
					stack.push(num);
				}
			}

			while (!stack.isEmpty()) {
				sum += stack.pop();
			}

			answer.append(sum).append("\n");
		}

		System.out.println(answer);
		in.close();
	}

}
