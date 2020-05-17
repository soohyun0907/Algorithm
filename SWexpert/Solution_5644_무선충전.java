import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 22,628 KB
 * 실행시간 : 128 ms
 * 코드길이 : 3,221 B
 * 소요시간 : 4H
 */

public class Solution_5644_무선충전 {

	static int SIZE = 10;
	static int M, A, aBC, bBC;
	static int[] moveA;
	static int[] moveB;
	static int[][] BC;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(in.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			map = new int[SIZE][SIZE];
			Point personA = new Point(0, 0);
			Point personB = new Point(SIZE - 1, SIZE - 1);
			moveA = new int[M + 1];
			moveB = new int[M + 1];
			BC = new int[A + 1][4];

			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 1; i <= M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 1; i <= M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= A; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				BC[i][0] = Integer.parseInt(st.nextToken()) - 1; // X좌표
				BC[i][1] = Integer.parseInt(st.nextToken()) - 1; // Y좌표
				BC[i][2] = Integer.parseInt(st.nextToken()); // 충전범위(C)
				BC[i][3] = Integer.parseInt(st.nextToken()); // 성능(P)
			}

			int result = 0;
			for (int i = 0; i <= M; i++) {
				aBC = movePerson(0, moveA[i], personA);
				bBC = movePerson(0, moveB[i], personB);
				if (aBC > 0 && bBC > 0 && aBC == bBC) { // 같을 경우 다른 충전 포인트 찾기
					result += inSame(aBC, bBC, personA, personB);
					continue;
				}
				result += BC[aBC][3] + BC[bBC][3];
			}

			answer.append('#').append(tc + 1).append(' ').append(result).append('\n');
		} // end test-case
		System.out.println(answer);
		in.close();
	} // end main

	private static int inSame(int aBC, int bBC, Point personA, Point personB) {
		int maxResult = BC[aBC][3];
		for (int i = 1; i < A + 1; i++) {
			int temp = 0;
			if (BC[i][2] >= (Math.abs(personA.x - BC[i][0]) + Math.abs(personA.y - BC[i][1]))) {
				aBC = i;
			}
			for (int j = 1; j < A + 1; j++) {
				if (BC[j][2] >= (Math.abs(personB.x - BC[j][0]) + Math.abs(personB.y - BC[j][1]))) {
					bBC = j;
					if (aBC == bBC)
						temp = BC[aBC][3];
					else
						temp = BC[aBC][3] + BC[bBC][3];

					if (maxResult < temp)
						maxResult = temp;
				}
			}
		}
		return maxResult;
	}

	private static int movePerson(int BCpoint, int dir, Point person) {
		switch (dir) {
		case 1: // 상
			person.y -= 1;
			break;
		case 2: // 우
			person.x += 1;
			break;
		case 3: // 하
			person.y += 1;
			break;
		case 4: // 좌
			person.x -= 1;
			break;
		}
		for (int i = 1; i < A + 1; i++) {
			if (BC[i][2] >= (Math.abs(person.x - BC[i][0]) + Math.abs(person.y - BC[i][1]))) {
				if (BC[BCpoint][3] < BC[i][3]) {
					BCpoint = i;
				}
			}
		}
		return BCpoint;
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
