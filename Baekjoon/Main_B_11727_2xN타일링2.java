package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author soohyun
 * 메모리 : 12,944 KB
 * 시간 : 72 ms
 * 코드길이 : 680 B
 * 소요시간 : 25M
 */

public class Main_B_11727_2xN타일링2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		if(n == 1)
            System.out.println(1);
        if(n == 2)
            System.out.println(3);
		if (n > 2) {
            int[] memo = new int[n + 1];
		    memo[1] = 1;
		    memo[2] = 3;
			for (int i = 3; i <= n; i++) {
				memo[i] = (memo[i - 1] + 2 * memo[i - 2] % 10007) % 10007;
			}
            System.out.println(memo[n]);
		}
		in.close();
	}

}
