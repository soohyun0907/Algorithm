import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 38,876 KB 
 * 시간 : 316 ms 
 * 코드길이 : 1,785 B 
 * 소요시간 : 3H
 */

public class Main_B_20055_컨베이어벨트위의로봇 {

	static int N, K, size;
	static int[] belt;
	static boolean[] robots;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		size = 2 * N;
		belt = new int[size];
		robots = new boolean[N];

		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < size; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

		int ans = 0;
		while (true) {
			ans++;
			moveBelt();
			moveRobots();

			int cnt = 0;
			for (int i = 0; i < size; i++) {
				if (belt[i] == 0)
					cnt++;
			}

			if (cnt >= K)
				break;
		}

		System.out.println(ans);
	}

	private static void moveRobots() {
		boolean[] tmp = new boolean[N];

		for (int i = 0; i < N; i++) {
			tmp[i] = robots[i];
		}

		for (int i = N - 2; i >= 0; i--) {
			if (robots[i] && !robots[i + 1] && belt[i + 1] > 0) {
				robots[i] = false;
				tmp[i] = false;
				tmp[i + 1] = true;
				belt[i + 1]--;

				if (tmp[N - 1])
					tmp[N - 1] = false;
			}
		}

		robots = tmp;

		if (!robots[0] && belt[0] > 0) {
			robots[0] = true;
			belt[0]--;
		}
	}

	private static void moveBelt() {
		int cur = belt[0], next;
		for (int i = 0; i < size - 1; i++) {
			next = belt[i + 1];
			belt[i + 1] = cur;
			cur = next;
		}
		belt[0] = cur;

		boolean curR = robots[0], nextR;
		robots[0] = false;
		for (int i = 0; i < N - 1; i++) {
			nextR = robots[i + 1];
			robots[i + 1] = curR;
			curR = nextR;
		}

		if (robots[N - 1])
			robots[N - 1] = false;
	}

}
