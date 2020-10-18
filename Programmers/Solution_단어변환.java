
public class Solution_단어변환 {

	public static void main(String[] args) {
		System.out.println(solution("hit", "cog", new String[] { "hot", "dot", "dog", "lot", "log", "cog" }));
		System.out.println(solution("hit", "cog", new String[] { "hot", "dot", "dog", "lot", "log" }));
	}

	static int answer;

	public static int solution(String begin, String target, String[] words) {
		answer = Integer.MAX_VALUE;
		dfs(begin, target, words, 0, 0);
		if (answer == Integer.MAX_VALUE)
			answer = 0;
		return answer;
	}

	private static void dfs(String cur, String target, String[] words, int flag, int cnt) {
		if (target.equals(cur)) {
			answer = Math.min(cnt, answer);
		}

		for (int i = 0; i < words.length; i++) {
			if ((flag & (1 << i)) == 0) {
				int alpha = 0;
				for (int j = 0; j < cur.length(); j++) {
					if (cur.charAt(j) != words[i].charAt(j))
						alpha++;
				}
				if (alpha == 1)
					dfs(words[i], target, words, (flag | (1 << i)), cnt + 1);
			}
		}
	}
}
