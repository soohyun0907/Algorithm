import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 53,692 KB
 * 시간 : 160 ms
 * 코드길이 : 1,603 B
 * 소요시간 : 30M
 */

public class Main_B_5014_스타트링크 {

	static int F, S, G, U, D, ans;
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		if (S == G)
			System.out.println("0");
		else if (S < G && U == 0)
			System.out.println("use the stairs");
		else if (S > G && D == 0)
			System.out.println("use the stairs");
		else {
			ans = bfs();
			System.out.println(ans == -1 ? "use the stairs" : ans);
		}
	}

	private static int bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		visited = new boolean[F + 1];
		queue.offer(S);
		visited[S] = true;

		int size, cur, cnt = 0, curU, curD;
		while (!queue.isEmpty()) {
			size = queue.size();
			while (--size >= 0) {
				cur = queue.poll();
				curU = cur + U;
				curD = cur - D;

				if (cur == G)
					return cnt;
				if (curU < F + 1 && !visited[curU]) {
					queue.offer(curU);
					visited[curU] = true;
				}
				if (curD > 0 && !visited[curD]) {
					queue.offer(curD);
					visited[curD] = true;
				}
			}
			cnt++;
		}
		// G층에 도착을 못한 경우이기 때문에 -1 return해준다.
		return -1;
	}

	// 런타임 에러 발생
	static int min = Integer.MAX_VALUE;
	private static void dfs(int start, int cnt) {
		if (min < cnt)
			return;

		if (start < 1 || start > F)
			return;

		if (start == G) {
			if (min > cnt)
				min = cnt;
			return;
		}

		dfs(start + U, cnt + 1);
		dfs(start - D, cnt + 1);
	}

}
