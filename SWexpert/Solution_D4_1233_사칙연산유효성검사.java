import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_1233_사칙연산유효성검사 {

	static int N, index; // 트리가 가지는 정점의 총 수
	static char[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			answer.append("#").append(tc).append(" ");
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());

			tree = new char[N + 1];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				index = Integer.parseInt(st.nextToken());
				tree[index] = st.nextToken().charAt(0);
			}

			int result = 1;
			for (int i = 1; i <= N; i++) {
				int left = i * 2;
				int right = left + 1;

				if (!Character.isDigit(tree[i])) {
					if (left <= N && right <= N) {
						if (Character.isDigit(tree[left]) && !Character.isDigit(tree[right]))
							result = 0;
					}
				} else {
					if (left <= N && right <= N) {
						if (tree[left] != '\u0000' || tree[right] != '\u0000')
							result = 0;
					}
				}

				if (result == 0)
					break;
			}

			answer.append(result).append("\n");

		}

		System.out.println(answer);
		in.close();
	}

}
