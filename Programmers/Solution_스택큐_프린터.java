import java.util.LinkedList;
import java.util.Queue;

public class Solution_스택큐_프린터 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 2, 1, 3, 2 }, 2));
		System.out.println(solution(new int[] { 1, 1, 9, 1, 1, 1 }, 0));
	}

	public static int solution(int[] priorities, int location) {
		int answer = 1;
		int[] ranks = new int[10];
		Queue<doc> queue = new LinkedList<doc>();
		for (int i = 0; i < priorities.length; i++) {
			ranks[priorities[i]]++;
			queue.offer(new doc(i, priorities[i]));
		}

		doc cur;
		boolean flag;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			flag = false;
			for (int i = cur.prior + 1; i < 10; i++) {
				if (ranks[i] > 0) {
					flag = true;
					break;
				}
			}

			if (!flag) {
				if (cur.num == location)
					break;
				ranks[cur.prior]--;
				answer++;
				continue;
			}

			queue.offer(cur);
		}

		return answer;
	}

	private static class doc {
		int num;
		int prior;

		public doc(int num, int prior) {
			this.num = num;
			this.prior = prior;
		}
	}
}
