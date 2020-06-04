import java.util.LinkedList;

public class Solution_문자열압축 {

	public static void main(String[] args) {
		System.out.println(solution("aabbaccc"));
		System.out.println(solution("ababcdcdababcdcd"));
		System.out.println(solution("abcabcdede"));
		System.out.println(solution("abcabcabcabcdededededede"));
		System.out.println(solution("xababcdcdababcdcd"));
	}

	public static int solution(String s) {
		int answer = 0;
		int min = Integer.MAX_VALUE;
		int n = 1;
		int cnt = 1;
		int len = s.length();
		String tmp;
		LinkedList<Pair> list = new LinkedList<Pair>();
		while (n <= len / 2) {
			tmp = s.substring(0, n);
			cnt = 1;
			for (int i = n; i < len; i += n) {
				if (i + n > len)
					continue;
				if (tmp.equals(s.substring(i, i + n))) {
					cnt++;
				} else {
					list.add(new Pair(tmp, cnt));
					tmp = s.substring(i, i + n);
					cnt = 1;
				}
				if (i + n + n > len) {
					list.add(new Pair(tmp, cnt));
					break;
				}
			}
			if (len % n != 0)
				list.add(new Pair(s.substring(len - (len % n), len), 1));
			String result = "";
			while (!list.isEmpty()) {
				Pair cur = list.poll();
				if (cur.cnt > 1)
					result += cur.cnt + cur.sub;
				else
					result += cur.sub;
			}
			min = Math.min(min, result.length());
			n++;
		}
		if (min == Integer.MAX_VALUE)
			answer = len;
		else
			answer = min;
		return answer;
	}

	static class Pair {
		String sub;
		int cnt;
		public Pair(String sub, int cnt) {
			super();
			this.sub = sub;
			this.cnt = cnt;
		}
	}
}
