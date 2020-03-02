import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

/**
 * @author soohyun
 * 메모리 : 27,764 KB
 * 시간 : 1,678 ms
 */
public class Solution_D5_1247_최적경로_1차 {

	static int N, compX, compY, houseX, houseY, min = 1000;
	static Point[] customer = new Point[10];
	static int[] permu;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			answer.append("#").append(t).append(" ");
			min = 1000;
			N = Integer.parseInt(in.readLine());
			permu = new int[N];
			st = new StringTokenizer(in.readLine(), " ");
			compX = Integer.parseInt(st.nextToken());
			compY = Integer.parseInt(st.nextToken());
			houseX = Integer.parseInt(st.nextToken());
			houseY = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				customer[i] = new Point(x, y);
			}

			makePermutation(0, 0);
			answer.append(min).append("\n");
		} // end for(test-case)

		System.out.print(answer);
		in.close();
	}

	private static void makePermutation(int index, int flag) {
		if (index == N) {
			System.out.print(Arrays.toString(permu));
			findWay(permu);
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) == 0) {
				permu[index] = i;
				makePermutation(index + 1, flag | 1 << i);
			}
		}
	}

	private static void findWay(int[] permu) {
		int path = Math.abs(compX - customer[permu[0]].x) + Math.abs(compY - customer[permu[0]].y);
		path += Math.abs(houseX - customer[permu[N - 1]].x) + Math.abs(houseY - customer[permu[N - 1]].y);

		for (int i = 0; i < N - 1; i++) {
			path += Math.abs(customer[permu[i]].x - customer[permu[i + 1]].x)
					+ Math.abs(customer[permu[i]].y - customer[permu[i + 1]].y);
		}

		System.out.println(path);
		if (path < min)
			min = path;
	}

}
