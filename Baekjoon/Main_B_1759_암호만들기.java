package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_1759_암호만들기 {

	static int L, C;
	static char[] password;
	static char[] alpha;
	static boolean[] visited;
	static int countVow = 0, countC = 0;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		password = new char[L];
		alpha = new char[C];
		visited = new boolean[C];
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < C; i++) {
			alpha[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(alpha);
		makePassword(0);
		System.out.println(answer);
		in.close();
	}

	private static void makePassword(int index) {
		if (index == L) {
			for (char c : password) {
				if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
					countVow++;
				else
					countC++;
			}
			if (countVow >= 1 && countC >= 2) {
				for (char c : password) {
					answer.append(c);
				}
				answer.append("\n");
			}
			countVow = 0;
			countC = 0;
			return;
		}

		for (int i = 0; i < C; i++) {
			if (visited[i])
				continue;
			if (index > 0 && password[index - 1] > alpha[i])
				continue;

			password[index] = alpha[i];
			visited[i] = true;
			makePassword(index + 1);
			visited[i] = false;
		}
	}

}
