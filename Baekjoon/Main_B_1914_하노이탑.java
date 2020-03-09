import java.math.BigInteger;
import java.util.Scanner;


/**
 * @author soohyun
 * 메모리 : 59524 KB
 * 시간 : 544 ms
 * 코드길이 : 818 B
 */

public class Main_B_1914_하노이탑 {

	static long count = 0;
	static int N;
	static StringBuilder result = new StringBuilder();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		if (N <= 20) {
			hanoi(N, 1, 2, 3);
			System.out.println(count);
			System.out.println(result);
		} else {
			BigInteger K = new BigInteger("2");
			K = K.pow(N).subtract(BigInteger.ONE);
			System.out.println(K);
		}
		in.close();
	}

	private static void hanoi(int cnt, int from, int temp, int to) {
		if (cnt == 1) {
			result.append(from).append(" ").append(to).append("\n");
			count++;
		}

		else {
			hanoi(cnt - 1, from, to, temp);
			count++;
			result.append(from).append(" ").append(to).append("\n");
			hanoi(cnt - 1, temp, from, to);
		}
	}

}