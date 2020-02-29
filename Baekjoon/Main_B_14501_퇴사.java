import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_14501_퇴사 {

	static int N, max = 0;
	static int[] Ti;
	static int[] Pi;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		// N : 퇴사하기까지 남은 일수
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		Ti = new int[N + 1];
		Pi = new int[N + 1];
		visited = new boolean[N + 1];

		Ti[0] = 1;
		// Ti : 상담을 하는데 필요한 기간
		// Pi : 상담을 했을 때 받을 수 있는 금액
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			Ti[i] = Integer.parseInt(st.nextToken());
			Pi[i] = Integer.parseInt(st.nextToken());
		}

		makeMax(0, 0);
		System.out.println(max);
		in.close();
	}

	private static void makeMax(int index, int workDays) {
		if (index > N + 1 || workDays > N) {
			return;
		}

		if (workDays == N) {
			int sum = 0;
			for (int i = 1; i <= N; i++) {
				if (visited[i]) {
					sum += Pi[i];
				}
			}
			if (max < sum)
				max = sum;
			return;
		}

		if (workDays < N) {
			int sum = 0;
			for (int i = 1; i <= N; i++) {
				if (visited[i]) {
					sum += Pi[i];
				}
			}
			if (max < sum)
				max = sum;
		}

		for (int i = index; i <= N; i++) {
			if (visited[i])
				continue;

			visited[i] = true;
			makeMax(i + Ti[i], workDays + Ti[i]);
			visited[i] = false;
		}
	}

}