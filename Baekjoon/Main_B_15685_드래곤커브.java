import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,320 KB
 * 시간 : 104 ms
 * 코드길이 : 1,632 B
 * 소요시간 : 2H 30M
 */

public class Main_B_15685_드래곤커브 {

	static int size = 101, x, y, d, g;
	static boolean[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(in.readLine());
		map = new boolean[size][size];
		int ans = 0;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(in.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			draw(x, y, getDirections(d, g));
		}

		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1; j++) {
				if (map[i][j] && map[i + 1][j] && map[i + 1][j + 1] && map[i][j + 1])
					ans++;
			}
		}

		System.out.println(ans);
	}

	private static void draw(int x, int y, List<Integer> directions) {
		map[x][y] = true;

		for (Integer dir : directions) {
			switch (dir) {
			case 0:
				map[++x][y] = true;
				break;
			case 1:
				map[x][--y] = true;
				break;
			case 2:
				map[--x][y] = true;
				break;
			case 3:
				map[x][++y] = true;
				break;
			}
		}
	}

	private static List<Integer> getDirections(int d, int g) {
		List<Integer> dir = new ArrayList<>();
		dir.add(d);

		while (--g >= 0) {
			for (int i = dir.size() - 1; i > -1; i--) {
				d = (dir.get(i) + 1) % 4;
				dir.add(d);
			}
		}

		return dir;
	}

}
