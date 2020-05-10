import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 21,180 KB
 * 실행시간 : 129 ms
 * 코드길이 : 1,656 B
 * 소요시간 : 20 M
 */

public class Solution_D3_3307_최장증가부분수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			answer.append('#').append(tc + 1).append(' ');
			int N = Integer.parseInt(in.readLine());
			int[] numbers = new int[N];
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			} // end input

			int[] C = new int[N];
			int[] path = new int[N];
			int size = 0;

			path[size] = -1;
			C[size++] = 0;
			for (int i = 1; i < N; i++) {
				if (numbers[C[size - 1]] < numbers[i]) {
					path[i] = C[size - 1];
					C[size++] = i;
				} else {
					int temp = binarySearch0(numbers, C, 0, size, numbers[i]);
					if (temp < 0)
						temp = -temp - 1;
					path[i] = path[C[temp]];
					C[temp] = i;
				}
			}

			answer.append(size).append('\n');
		} // end test case

		System.out.println(answer);
		in.close();
	} // end main

	private static int binarySearch0(int[] numbers, int[] c, int fromIndex, int toIndex, int key) {
		int low = fromIndex;
		int high = toIndex - 1;

		while (low <= high) {
			int mid = (low + high) >>> 1;
					int midVal = numbers[c[mid]];

					if (midVal < key)
						low = mid + 1;
					else if (midVal > key)
						high = mid - 1;
					else
						return mid;
		}
		return -(low + 1);
	}

}
