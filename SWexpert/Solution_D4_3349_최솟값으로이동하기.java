import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 21,708 KB
 * 실행시간 : 126 ms
 * 코드길이 : 1,503 B
 * 소요시간 : 1H 40M
 */

public class Solution_D4_3349_최솟값으로이동하기 {

	static int W, H, N, ans;
	static Point[] points;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			st = new StringTokenizer(in.readLine(), " ");
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			points = new Point[N];

			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(in.readLine(), " ");
				points[n] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			for (int n = 1; n < N; n++) {
				int x = points[n].x - points[n - 1].x;
				int y = points[n].y - points[n - 1].y;

				if (x * y > 0) {
					ans += Math.min(Math.abs(x), Math.abs(y));
					ans += Math.abs(Math.abs(x) - Math.abs(y));
				} else if (x * y == 0) {
					ans += Math.abs(x + y);
				} else {
					ans += Math.abs(x) + Math.abs(y);
				}
			}

			answer.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.println(answer);
	}

	private static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}