import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author soohyun
 * 메모리 : 12,924 KB
 * 시간 : 68 ms
 * 코드길이 : 500 B
 * 그리디
 */

public class Main_B_2839_설탕배달_Greedy {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());

		// Greedy
		int cnt = 0;
		// 5로 나누어 떨어지지 않는다면
		while (N % 5 != 0) {
			N -= 3;
			cnt++;
		}
		if (N < 0) {
			System.out.println(-1);
		} else {
			// 5로 나누어 떨어진다면
			cnt += N / 5;
			System.out.println(cnt);
		}
		
		in.close();
	}

}
