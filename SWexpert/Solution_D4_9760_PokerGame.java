import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 26,312 KB
 * 시간 : 131 ms
 * 코드길이 : 2,954 B
 * 소요시간 : 1H 20M
 */

public class Solution_D4_9760_PokerGame {

	static int SIZE = 5;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			answer.append('#').append(tc).append(' ');
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int[] suit = new int[4]; // S, D, H, C
			int[] rank = new int[13]; // 2-9, A, T, J, Q, K
			Queue<Integer> queue = new LinkedList<>();
			int suitCnt = 0;
			boolean isAsc = false;
			boolean check = false;

			for (int i = 0; i < SIZE; i++) {
				String str = st.nextToken();
				if (str.charAt(0) == 'S')
					suit[0]++;
				else if (str.charAt(0) == 'D')
					suit[1]++;
				else if (str.charAt(0) == 'H')
					suit[2]++;
				else if (str.charAt(0) == 'C')
					suit[3]++;
				if (str.charAt(1) == 'A')
					rank[8]++;
				else if (str.charAt(1) == 'T')
					rank[9]++;
				else if (str.charAt(1) == 'J')
					rank[10]++;
				else if (str.charAt(1) == 'Q')
					rank[11]++;
				else if (str.charAt(1) == 'K')
					rank[12]++;
				else // 숫자일 경우
					rank[str.charAt(1) - 50]++;
			}

			for (int i = 0; i < suit.length; i++) {
				if (suit[i] == 3 || suit[i] == 5) {
					suitCnt = suit[i];
					break;
				}
			}

			for (int i = 0; i < rank.length; i++) {
				if (rank[i] == 1 && !check) {
					check = true;
					isAsc = checkAsc(rank, i);
				} else if (rank[i] > 1) {
					queue.offer(rank[i]);
				}
			}

			if (queue.isEmpty()) {
				if (suitCnt == 5 && isAsc) {
					answer.append("Straight Flush").append('\n');
				} else if (suitCnt == 5 && !isAsc) {
					answer.append("Flush").append('\n');
				} else if (suitCnt != 5 && !isAsc) {
					answer.append("Straight").append('\n');
				} else {
					answer.append("High card").append('\n');
				}
				continue;
			} else {
				if (queue.size() > 1) {
					int rankCnt = 0;
					while (!queue.isEmpty()) {
						rankCnt = queue.poll();
						if (rankCnt == 3) {
							answer.append("Full House").append('\n');
							break;
						}
					}
					if (rankCnt == 2 && queue.isEmpty()) {
						answer.append("Two pair").append('\n');
					}
					continue;
				} else {
					int rankCnt = queue.poll();
					if (rankCnt == 4) {
						answer.append("Four of a Kind").append('\n');
					} else if (rankCnt == 3) {
						answer.append("Three of a kind").append('\n');
					} else {
						answer.append("One pair").append('\n');
					}
					continue;
				}
			}
		} // end test-case

		System.out.print(answer);
		in.close();
	}

	private static boolean checkAsc(int[] rank, int n) {
		for (int i = n; i < SIZE; i++) {
			if (rank[i] != 1)
				return false;
		}
		return true;
	}

}
