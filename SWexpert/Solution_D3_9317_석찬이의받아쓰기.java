package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_9317_석찬이의받아쓰기 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder answer = new StringBuilder();

		int TC = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= TC; t++) {
			answer.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			String ans = in.readLine();
			String write = in.readLine();
			int count = 0;

			for (int i = 0; i < N; i++) {
				if (ans.charAt(i) == write.charAt(i))
					count++;
				else
					continue;
			}

			answer.append(count).append("\n");
		}

		System.out.println(answer);
		in.close();
	}

}
