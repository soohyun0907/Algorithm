import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 14,952 KB
 * 시간 : 132 ms
 * 코드길이 : 2,545 B
 * 소요시간 : 1H
 */

public class Main_B_15686_치킨배달 {

	static ArrayList<Point> house;
	static ArrayList<Point> chicken;
	static int[][] distance;
	static int[] num;
	static boolean[] visited;
	static int N, M, result;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		house = new ArrayList<Point>();
		chicken = new ArrayList<Point>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				int info = Integer.parseInt(st.nextToken());
				if (info == 1) {
					house.add(new Point(i, j));
				} else if (info == 2) {
					chicken.add(new Point(i, j));
				}
			}
		} // end input

		// 배열에 치킨거리 계산하여 저장하기
		int hsize = house.size();
		int csize = chicken.size();
		distance = new int[hsize][csize + 1];
		for (int i = 0; i < hsize; i++) {
			Point curH = house.get(i);
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < csize; j++) {
				distance[i][j] = Math.abs(curH.r - chicken.get(j).r) + Math.abs(curH.c - chicken.get(j).c);
				if (min > distance[i][j]) {
					min = distance[i][j];
				}
			}
			// 각 행의 마지막에는 min값을 저장하여 준다
			distance[i][csize] = min;
		}
		if (csize == M) {
			result = 0;
			for (int i = 0; i < hsize; i++) {
				result += distance[i][csize];
			}
			System.out.println(result);
		} else {
			num = new int[csize];
			visited = new boolean[csize];
			result = Integer.MAX_VALUE;
			calTotalDistance(0, 0);
			System.out.println(result);
		}
		in.close();
	} // end main

	private static void calTotalDistance(int index, int cnt) {
		if (cnt == M) {
			int total = 0;
			for (int i = 0; i < house.size(); i++) {
				int min = Integer.MAX_VALUE;
				for (int j = 0; j < M; j++) {
					if (min > distance[i][num[j]])
						min = distance[i][num[j]];
				}
				total += min;
			}
			if (result > total)
				result = total;
			return;
		}
		for (int i = index; i < chicken.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				num[cnt] = i;
				calTotalDistance(i, cnt + 1);
				visited[i] = false;
			}
		}
	}

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}