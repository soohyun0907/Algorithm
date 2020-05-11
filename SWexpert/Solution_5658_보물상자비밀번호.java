import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 22,116 KB
 * 실행시간 : 126 ms
 * 코드길이 : 2,241 B
 * 소요시간 : 1H 10M
 */

public class Solution_5658_보물상자비밀번호 {

	static int N, K;
	static ArrayList<Long> numList;
	static ArrayList<String> list;
	static char[] pwd;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			pwd = in.readLine().toCharArray(); // end input
			numList = new ArrayList<Long>();
			list = new ArrayList<String>();

			int size = N / 4; // 각 변에 있는 숫자의 갯수 & 모든 경우의 수를 찾기 위한 회전 수
			String tmp = "";
			Long num;
			while (--size >= 0) {
				int cnt = 1;
				for (int i = 0; i < N; i++) {
					if (cnt == N / 4) {
						tmp += pwd[i];
						if (!list.contains(tmp)) {
							list.add(tmp);
							num = toDecimal(tmp);
							numList.add(num);
						}
						tmp = "";
						cnt = 1;
					} else {
						tmp += pwd[i];
						cnt++;
					}
				}
				rotate();
			}
			Collections.sort(numList);
			Collections.reverse(numList);
			answer.append('#').append(tc + 1).append(' ').append(numList.get(K - 1)).append('\n');
		} // end test-case

		System.out.print(answer);
		in.close();
	}

	private static void rotate() {
		char prev = pwd[0];
		char next;
		pwd[0] = pwd[N - 1];
		for (int i = 1; i < N; i++) {
			next = pwd[i];
			pwd[i] = prev;
			prev = next;
		}
	}

	private static Long toDecimal(String tmp) {
		Long num = (long) 0;
		int n = 0;
		for (int i = 0; i < tmp.length(); i++) {
			switch (tmp.charAt(i)) {
			case 'A':
				n = 10;
				break;
			case 'B':
				n = 11;
				break;
			case 'C':
				n = 12;
				break;
			case 'D':
				n = 13;
				break;
			case 'E':
				n = 14;
				break;
			case 'F':
				n = 15;
				break;
			default:
				n = tmp.charAt(i) - '0';
				break;
			}
			num += (long) (n * Math.pow(16, N / 4 - i - 1));
		}
		return num;
	}

}
