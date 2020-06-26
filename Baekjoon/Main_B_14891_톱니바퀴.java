import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,088 KB
 * 시간 : 80 ms
 * 코드길이 : 1,935 B
 * 소요시간 : 1H
 */

public class Main_B_14891_톱니바퀴 {

	static int cnt = 4, size = 8, K, n, dir;
	static int[] wheel = new int[cnt * size];
	static int[] wheelDir = new int[cnt];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		String tmp;
		for (int i = 0; i < cnt; i++) {
			tmp = in.readLine();
			for (int j = 0; j < size; j++) {
				wheel[size * i + j] = tmp.charAt(j) - '0';
			}
		}

		K = Integer.parseInt(in.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken()) - 1;
			dir = Integer.parseInt(st.nextToken());
			simul(n, dir);
			rotate();
			Arrays.fill(wheelDir, 0);
		}

		// N극은 0, S극은 1
		int answer = 0;
		for (int i = 0; i < cnt; i++) {
			answer += wheel[size * i] * Math.pow(2, i);
		}
		System.out.println(answer);
		in.close();
	} // end main

	private static void rotate() {
		int first, last;
		for (int i = 0; i < cnt; i++) {
			if (wheelDir[i] == 1) { // 시계방향 회전
				last = wheel[size * i + (size - 1)];
				for (int j = size * i + (size - 1); j > size * i; j--) {
					wheel[j] = wheel[j - 1];
				}
				wheel[size * i] = last;
			} else if (wheelDir[i] == -1) { // 반시계방향 회전
				first = wheel[size * i];
				for (int j = size * i; j < size * i + (size - 1); j++) {
					wheel[j] = wheel[j + 1];
				}
				wheel[size * i + (size - 1)] = first;
			}
		}
	}

	private static void simul(int n, int dir) {
		if (wheelDir[n] != 0)
			return;

		wheelDir[n] = dir;

		if (n > 0 && wheel[n * size + 6] != wheel[(n - 1) * size + 2])
			simul(n - 1, dir * (-1));

		if (n < cnt - 1 && wheel[n * size + 2] != wheel[(n + 1) * size + 6])
			simul(n + 1, dir * (-1));

	}

}
