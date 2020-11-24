import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 16,076 KB
 * 시간 : 156 ms
 * 코드길이 : 2,592 B
 * 소요시간 : 2H
 */

public class Main_B_17471_게리맨더링 {

	static int N, answer;
	static ArrayList<Integer>[] list;
	static int[] populations;
	static boolean[] selectedArea;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		populations = new int[N];
		selectedArea = new boolean[N];
		list = new ArrayList[N];
		answer = Integer.MAX_VALUE;

		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			populations[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				list[i].add(Integer.parseInt(st.nextToken()) - 1);
			}
		} // end input

		generateSubset(0);

		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	private static void generateSubset(int index) {
		if (index == N) {
			int cnt = 0, sumArea1 = 0, sumArea2 = 0;
			for (int i = 0; i < N; i++) {
				if (selectedArea[i]) cnt++;
			}

			if (cnt == 0 || cnt == N)
				return;

			if (checkConnections(true) && checkConnections(false)) {
				for (int i = 0; i < N; i++) {
					if (selectedArea[i]) sumArea1 += populations[i];
					else sumArea2 += populations[i];
				}

				answer = Math.min(answer, Math.abs(sumArea1 - sumArea2));
			}

			return;
		}

		selectedArea[index] = true;
		generateSubset(index + 1);
		selectedArea[index] = false;
		generateSubset(index + 1);
	}

	private static boolean checkConnections(boolean flag) { // BFS
		boolean[] visited = new boolean[N];
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < N; i++) {
			if (selectedArea[i] == flag) {
				queue.offer(i);
				visited[i] = true;
				break;
			}
		}

		int cur, size, next;
		while (!queue.isEmpty()) {
			cur = queue.poll();

			size = list[cur].size();
			for (int i = 0; i < size; i++) {
				next = list[cur].get(i);
				if (visited[next]) continue;
				if (selectedArea[next] == flag) {
					queue.offer(next);
					visited[next] = true;
				}
			}
		}

		int cnt1 = 0, cnt2 = 0;
		for (int i = 0; i < N; i++) {
			if (selectedArea[i] == flag) cnt1++;
			if (visited[i]) cnt2++;
		}

		if (cnt1 != cnt2) return false;
		return true;
	}

}
