import java.util.HashMap;
import java.util.Scanner;

/**
 * @author soohyun
 * 메모리 : 30,196 KB
 * 시간 : 187 ms
 * 코드길이 : 1,300 B
 * 비트마스크와 부분집합을 이용
 */

public class Solution_D5_7206_숫자게임 {

	static HashMap<Integer, Integer> memo;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder answer = new StringBuilder();
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			memo = new HashMap<Integer, Integer>();
			int cnt = game(N);

			answer.append('#').append(tc + 1).append(' ').append(cnt).append('\n');
		}

		System.out.print(answer);
		in.close();
	}

	private static int game(int n) {
		if (n < 10) {
			return 0;
		}
		int max = 0;
		String s = "" + n;
		int len = s.length() - 1;
		/*
		 * 123   00 : 안쪼갠 경우 
		 * 1 23  01 : 첫번째 위치에서 숫자를 쪼갠다. 
		 * 12 3  10 : 두번째 위치에서 
		 * 1 2 3 11 : 첫번째, 두번째 위치에서
		 */
		// 부분집합을 이용해서 1위치에서 숫자를 쪼갠 후 곱한다.
		for (int i = 1, size = 1 << len; i < size; i++) {
			int num = s.charAt(0) - '0';
			int mul = 1;
			for (int j = 0; j < len; j++) {
				if ((i & (1 << j)) == 0) { // 쪼개는 위치가 아님
					num = num * 10 + s.charAt(j + 1) - '0';
				} else {
					mul *= num;
					num = s.charAt(j+1)-'0';
				}
			}
			mul *= num; // 마지막 조각을 연산
			int cnt = 0;
			if(memo.containsKey(mul)) { // 메모이제이션이 되어 있는 경우
				cnt = memo.get(mul);
			} else { // 메모에 없는 경우
				cnt = game(mul);
				memo.put(mul, cnt);
			}
			
			max = Math.max(max, cnt);
		}
		return max+1;
	}

}
