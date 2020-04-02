import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,260 KB
 * 시간 : 80 ms
 * 코드길이 : 716 B
 */

public class Main_B_11399_ATM {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] p = new int[N + 1];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i < N + 1; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(p);
		int min = 0;
		for (int i = 1; i < N + 1; i++) {
			p[i] = p[i - 1] + p[i];
			min += p[i];
		}

		System.out.println(min);
		in.close();
	}

}
