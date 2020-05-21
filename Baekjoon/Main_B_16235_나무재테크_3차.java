import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 16,320 KB
 * 시간 : 428 ms
 * 코드길이 : 2,623 B
 */

public class Main_B_16235_나무재테크_3차 {

	static int N, M, K;
	static ArrayList<Integer>[][] mapTree;
	static int[][] a, map;
	static int[][] delta = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		a = new int[N][N];
		mapTree = new ArrayList[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
				mapTree[i][j] = new ArrayList<Integer>();
			}
		}

		int x, y, age;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			x = Integer.parseInt(st.nextToken()) - 1;
			y = Integer.parseInt(st.nextToken()) - 1;
			age = Integer.parseInt(st.nextToken());
			mapTree[x][y].add(age);
		}

		for (int k = 0; k < K; k++) {
			springAndSummer();
			fallAndWinter();
		}

		System.out.println(getCount());
	}

	private static void springAndSummer() {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				ArrayList<Integer> list = mapTree[x][y];
				int amount = map[x][y];
				int nAmount = 0; // 새로 더해질 양분
				for (int i = list.size() - 1; i >= 0; --i) {
					int age = list.get(i);
					if (amount >= age) { // 봄 작업
						amount -= age;
						list.set(i, age + 1);
					} else { // 여름 작업
						nAmount += age / 2;
						list.remove(i);
					}
				}
				map[x][y] = amount + nAmount;
			}
		}
	}

	private static void fallAndWinter() {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				ArrayList<Integer> list = mapTree[x][y];
				for (int i = 0; i < list.size(); i++) {
					int age = list.get(i);
					if (age % 5 != 0) continue;
					int nx, ny;
					for (int d = 0; d < delta.length; d++) {
						nx = x + delta[d][0];
						ny = y + delta[d][1];

						if (0 <= nx && nx < N && 0 <= ny && ny < N) {
							mapTree[nx][ny].add(1);
						}
					}
				}
				map[x][y] += a[x][y];
			}
		}
	}

	private static int getCount() {
		int count = 0;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				count += mapTree[x][y].size();
			}
		}
		return count;
	}

}
