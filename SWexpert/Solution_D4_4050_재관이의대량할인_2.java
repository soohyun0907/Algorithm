import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 95,853 KB
 * 실행시간 : 451 ms
 * 코드길이 : 1,173 B
 * 소요시간 : 10M
 */

public class Solution_D4_4050_재관이의대량할인_2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			answer.append('#').append(tc + 1).append(' ');
			int N = Integer.parseInt(in.readLine());
			Integer[] num = new Integer[N];
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(num, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o2.compareTo(o1);
				}
				
			});
			int sum = 0;
			for (int i = 0, n = 0; i < N; i++) {
				if (n == 2) {
					n = 0;
					continue;
				} else {
					sum += num[i];
					n++;
				}
			}
			answer.append(sum).append('\n');
		}

		System.out.print(answer);
		in.close();
	}

}
