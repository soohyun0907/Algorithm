import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J_1175_주사위던지기2_개선 {
	static int size = 6;
	static int N, M, count;
	static int[] numbers;
	static StringBuilder ans = new StringBuilder();

	// 1,2,3,... N으로 만들 수 있는 N 자리수 순열
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		numbers = new int[N];
		permutation(0, 0);

		System.out.print(ans);
		br.close();
	}

	// 재귀호출로 순열 구현하기
	private static void permutation(int index, int sum) {

		if (sum > M)
			return;
		if (index == N) {
			if (sum == M) {
				for (int i : numbers) {
					ans.append(i + " ");
				}
				ans.append("\n");
			}

			return;
		}

		for (int i = 1; i <= size; i++) {

			numbers[index] = i;
			permutation(index + 1, sum + i); // 현재의 sum에다가 던져진 주사위 수 i를 더해줘야 한다.
		}

	}

}
