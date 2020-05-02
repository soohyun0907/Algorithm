import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author soohyun 반례: 1 1110010 10002 #1 82
 * 메모리 : 18,376 KB
 * 실행시간 : 114 ms
 * 코드길이 : 1,779 B
 */

public class Solution_D4_4366_정식이의은행업무 {

	static ArrayList<Long> numList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			answer.append('#').append(tc + 1).append(' ');
			numList = new ArrayList<Long>();
			String bin = in.readLine().trim();
			String ter = in.readLine().trim();

			binaryToN(bin);
			long result = ternaryToN(ter);
			answer.append(result).append('\n');
		} // end test-case

		System.out.print(answer);
		in.close();
	} // end main

	private static long ternaryToN(String ter) {
		int len = ter.length();
		int[] terList = { 0, 1, 2 };
		for (int i = 0; i < len; i++) {
			int n = ter.charAt(len - i - 1) - 48;
			for (int j = 0; j < terList.length; j++) {
				if (n == terList[j])
					continue;
				long num = 0;
				n = terList[j];

				for (int k = 0; k < len; k++) {
					if (i == k) {
						num += n * Math.pow(3, k);
					} else {
						num += (ter.charAt(len - k - 1) - 48) * Math.pow(3, k);
					}
				}
				if (numList.contains(num)) {
					return num;
				}
				n = ter.charAt(len - i - 1) - 48;
			}
		}
		return 0;
	}

	private static void binaryToN(String bin) {
		int len = bin.length();
		for (int i = 0; i < len; i++) {
			long num = 0;
			for (int j = 0; j < len; j++) {
				if (i == j) {
					int n = bin.charAt(j) - 48;
					num += (n == 1) ? 0 : 1 * Math.pow(2, len - j - 1);
				} else {
					num += (bin.charAt(j) - 48) * Math.pow(2, len - j - 1);
				}
			}
			numList.add(num);
		}
	}

}
