import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,656 KB
 * 시간 : 100 ms
 * 코드길이 : 1,400 B
 * 소요시간 : 15M
 */

public class Main_B_14002_가장긴증가하는부분수열4_DP {

	static int N, num[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		num = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		} // end input
		lisPath();
	} // end main

	private static void lisPath() {
		StringBuilder answer = new StringBuilder();
		int lis[] = new int[N]; // 길이를 담을 배열
		int pre[] = new int[N]; // 경로를 추척할 인덱스를 담을 배열
		int maxIndex = 0;
		for (int i = 0; i < N; ++i) {
			lis[i] = 1;
			pre[i] = -1;
			for (int j = 0; j < i; ++j) {
				if (num[i] > num[j] && lis[i] < lis[j] + 1) {
					lis[i] = lis[j] + 1;
					pre[i] = j;
					if (lis[maxIndex] < lis[i])
						maxIndex = i;
				}
			}
		}
		answer.append(lis[maxIndex]).append('\n');

		int cur = maxIndex;
		Stack<Integer> stack = new Stack<Integer>();
		while (cur != -1) {
			stack.push(num[cur]);
			cur = pre[cur];
		}
		while (!stack.isEmpty()) {
			answer.append(stack.pop()).append(' ');
		}
		System.out.println(answer);
	} // end listPath

}
