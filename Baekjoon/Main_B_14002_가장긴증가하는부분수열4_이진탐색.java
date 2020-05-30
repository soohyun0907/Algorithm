import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,340 KB
 * 시간 : 88 ms
 * 코드길이 1,562 B
 * 소요시간 : 45M
 */

public class Main_B_14002_가장긴증가하는부분수열4_이진탐색 {

	static int N, num[];
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		num = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		lisPath();
		System.out.println(answer);
		in.close();
	}

	private static void lisPath() {
		int[] lis = new int[N];
		int[] lisIndex = new int[N];
		int[] preIndex = new int[N];
		int size = 0, temp = 0;

		for (int i = 0; i < N; ++i) {
			temp = -(Arrays.binarySearch(lis, 0, size, num[i])) - 1;
			if (temp < 0) continue;
			lis[temp] = num[i];
			lisIndex[temp] = i;
			// 자신의 위치가 0(첫번째)이면 이전은 없고, 자신의 위치가 0이 아니라면 현재 lis 구성의 바로 직전이 이전됨.
			preIndex[i] = (temp == 0) ? -1 : lisIndex[temp - 1];

			if (temp == size)
				++size;
		}
		answer.append(size).append('\n');
		// 경로 출력을 위한 코드
		int cur = lisIndex[size - 1];
		Stack<Integer> stack = new Stack<Integer>();
		while (cur != -1) {
			stack.push(num[cur]);
			cur = preIndex[cur];
		}
		while (!stack.isEmpty()) {
			answer.append(stack.pop()).append(' ');
		}
	}
}
