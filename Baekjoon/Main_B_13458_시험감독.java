package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_13458_시험감독 {

	public static void main(String[] args) throws IOException {
		// 총 N개의 시험장
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		long answer = 0;
		int N = Integer.parseInt(st.nextToken());

		int[] test = new int[N];
		// 각 시험장마다 있는 응시자수
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			test[i] = Integer.parseInt(st.nextToken());
		}
		// 총감독관은 각 방마다 무조건 1명, 감시할 수 있는 응시자수 B
		// 부감독관이 한 방에서 감시할 수 있는 응시자 수 C
		st = new StringTokenizer(in.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			int tmp = test[i] - B;
			if (tmp <= 0)
				continue;
			answer = answer + tmp / C;
			if (tmp % C != 0)
				answer += 1;
		}
		answer += N;

		System.out.print(answer);
		in.close();
	}

}
