import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 46,808 KB 
 * 시간 : 436 ms 
 * 코드길이 : 1,669 B 
 * 소요시간 : 30M
 */

public class Main_B_2252_줄세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] degree = new int[N + 1];

		// 그래프 초기화
		ArrayList<Integer>[] list = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<Integer>();
		}

		int A = 0;
		int B = 0;
		// 그래프에 정보 저장하고 진입차수++
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			list[A].add(B);
			degree[B]++;
		}

		// 진입차수가 0인 정점 큐에 저장
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 1; i < N + 1; i++) {
			if (degree[i] == 0) {
				queue.offer(i);
			}
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			answer.append(cur).append(' ');

			for (int i = 0; i < list[cur].size(); i++) {
				int temp = list[cur].get(i);
				degree[temp]--;

				if (degree[temp] == 0) {
					queue.offer(temp);
				}
			}
		}

		System.out.println(answer);
		in.close();
	} // end main method

}
