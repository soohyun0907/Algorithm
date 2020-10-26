import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,300 KB
 * 시간 : 92 ms
 * 코드길이 : 830 B
 * 소요시간 : 15M
 */

public class Main_B_11053_가장긴증가하는부분수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] num = new int[N];
		int[] lis = new int[N];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		// LIS Algorithm
		int size = 0, temp;
		for (int i = 0; i < N; i++) {
			temp = -(Arrays.binarySearch(lis, 0, size, num[i])) - 1;
			if(temp < 0) continue;
			lis[temp] = num[i];
			if (temp == size)
				++size;
		}

		System.out.println(size);
	}

}
