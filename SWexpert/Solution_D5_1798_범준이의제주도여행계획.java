import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 27,928 KB
 * 실행시간 : 1,053 ms
 * 코드길이 : 4,520 B
 * 소요시간 : 1H 20M
 */

public class Solution_D5_1798_범준이의제주도여행계획 {

	static int N, M, maxSatis;
	static Point airport;
	static List<Point> hotels;
	static List<Point> points;
	static List<Integer> maxSatisPath;
	static Stack<Integer> tempPath;
	static int[][] moveTime;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			moveTime = new int[N][N];
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = i + 1; j < N; j++) {
					moveTime[i][j] = moveTime[j][i] = Integer.parseInt(st.nextToken());
				}
			}

			hotels = new ArrayList<Point>();
			points = new ArrayList<Point>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				String type = st.nextToken();
				if (type.equals("A")) {
					airport = new Point(type, i);
				} else if (type.equals("H")) {
					hotels.add(new Point(type, i));
				} else {
					int playTime = Integer.parseInt(st.nextToken());
					int satis = Integer.parseInt(st.nextToken());
					points.add(new Point(type, i, playTime, satis));
				}
			} // end input

			// 각 여행지에서 가까운 호텔 구하기 (완탐이지만 가까운 거리 미리구해서 가지치기)
			for (Point point : points) {
				int time = Integer.MAX_VALUE;
				for (Point hotel : hotels) {
					if (time > moveTime[point.idx][hotel.idx]) {
						time = moveTime[point.idx][hotel.idx];
						point.nearH = new Point(hotel.type, hotel.idx);
					}
				}
			}

			tempPath = new Stack<Integer>();
			maxSatis = Integer.MIN_VALUE;
			// 재귀로 완탐
			solution(0, airport, 0, 0, new boolean[N]);

			answer.append('#').append(tc + 1).append(' ');
			if (maxSatis == 0) {
				answer.append(0);
			} else {
				answer.append(maxSatis).append(' ');
				for (Integer idx : maxSatisPath) {
					answer.append(idx + 1).append(' ');
				}
			}
			answer.append('\n');
		} // end test-case

		System.out.print(answer);
		in.close();
	} // end main method

	// M일후 공항에 다시 돌아올 때까지 얻은 만족도와 경로 구하기 (day == M) 기저조건
	// 한번 갔던 관광 포인트는 다시 방문 X, 호텔은 상관없음(중복방문 가능)
	// 다른 길로 우회해서 방문하지 않음
	// 하루에 이동+놀이에 소요되는 시간이 9시간(540분)을 넘으면 안됨.
	// M일째 날에도 9시간 사용 가능. 단, 그전에 공항 도착해야함.
	private static void solution(int day, Point start, int satis, int time, boolean[] visited) {
		if (day == M) {
			if (satis > maxSatis) {
				maxSatis = satis;
				maxSatisPath = new ArrayList<Integer>(tempPath);
			}
			return;
		}

		boolean canGoNext = false;
		for (Point point : points) {
			if (!visited[point.idx]) {
				int tempTime = time + moveTime[start.idx][point.idx] + point.playTime;
				if (day == M - 1) {
					tempTime += moveTime[point.idx][airport.idx];
				} else {
					tempTime += moveTime[point.idx][point.nearH.idx];
				}

				// 540분을 넘으면 방문할 수 없음.
				if (tempTime > 540)
					continue;

				canGoNext = true;
				visited[point.idx] = true;
				tempPath.push(point.idx);
				solution(day, point, satis + point.satis, time + moveTime[start.idx][point.idx] + point.playTime,
						visited);
				tempPath.pop();
				visited[point.idx] = false;
			}
		} // end for-each

		// 관광지로 못가면 날짜에 따라서 공항이나 호텔로 이동
		if (!canGoNext) {
			if (day == M - 1) {
				tempPath.push(airport.idx);
				solution(day + 1, airport, satis, 0, visited);
				tempPath.pop();
			} else {
				for (Point hotel : hotels) {
					if (time + moveTime[start.idx][hotel.idx] <= 540) {
						tempPath.push(hotel.idx);
						solution(day + 1, hotel, satis, 0, visited);
						tempPath.pop();
					}
				}
			}
		}
	} // end solution method

	static class Point {
		String type;
		int idx;
		int playTime;
		int satis;
		Point nearH; // 관광지에서 가까운 호텔정보 저장

		public Point(String type, int idx) {
			this(type, idx, 0, 0);
		}

		public Point(String type, int idx, int playTime, int satis) {
			this.type = type;
			this.idx = idx;
			this.playTime = playTime;
			this.satis = satis;
		}
	}
}
