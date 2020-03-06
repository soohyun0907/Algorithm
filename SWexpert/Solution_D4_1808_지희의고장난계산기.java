import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 32,776 KB
 * 실행시간 : 173 ms
 * 코드길이 : 2,241 B
 */

public class Solution_D4_1808_지희의고장난계산기 {

	static int SIZE = 10, X, xSize, min;
	static int[] number = new int[SIZE];
	static ArrayList<Integer> list, numbers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			list = new ArrayList<>();
			numbers = new ArrayList<>();
			min = Integer.MAX_VALUE;

			answer.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");

			for (int i = 0; i < SIZE; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = SIZE - 1; i >= 0; i--) {
				if (number[i] > 0)
					numbers.add(i);
			}

			X = Integer.parseInt(in.readLine());
			xSize = len(X);

			// X가 1일 경우는
			if (X == 1) {
				// 1을 사용할 수 있는 경우
				if (number[1] > 0) {
					answer.append("2").append("\n");
					continue;
				}
				// 1을 사용할 수 없는 경우
				answer.append("-1").append("\n");
				continue;
			}
			// X의 약수 찾기
			findFactor(0, 0);
			// 찾은 약수로 최소 버튼 횟수 찾기
			findMin(X, 0);

			if (min == Integer.MAX_VALUE) {
				answer.append("-1").append("\n");
				continue;
			}

			answer.append(min).append("\n");
		} // end test-case

		System.out.print(answer);
		in.close();
	}

	private static void findMin(int num, int cnt) {
		if (num == 1) {
			if (cnt < min)
				min = cnt;
			return;
		}

		for (Integer n : list) {
			if (n == 1)
				continue;

			int div = num / n;

			if (div == 0 || num % n != 0)
				continue;

			findMin(div, cnt + 1 + len(n));
		}
	}

	private static void findFactor(int num, int cnt) {
		if (cnt > xSize)
			return;

		if (num != 0) {
			if (X / num == 0)
				return;
			else {
				if (X % num == 0) {
					if (!list.contains(num)) {
						list.add(num);
					}
				}
			}
		}

		for (Integer n : numbers) {
			findFactor(10 * num + n, cnt + 1);
		}
	}

	// int형 길이 구하는 방법 (String으로 바꾼 후 구하는 것보다 훨씬 빠름)
	private static int len(int n) {
		return (int) Math.log10(n) + 1;
	}
}
