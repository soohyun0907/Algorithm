import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int i;
	int j;

	public Point(int i, int j) {
		this.i = i;
		this.j = j;
	}
}

/**
 * @author soohyun
 * 메모리 : 13244 KB
 * 시간 : 84 ms
 * 풀이방법: 물과 고슴도치 각각 BFS를 사용하였다.
 * 		   물이 먼저인지 고슴도치가 먼저인지도 중요했던 문제.
 */

public class Main_B_3055_탈출 {

	static int R, C, time = 0;
	static char[][] tForest;
	static boolean[][] visited;
	static Queue<Point> water = new LinkedList<Point>();
	static Queue<Point> queueS = new LinkedList<Point>();
	static int[] deltaX = { -1, 1, 0, 0 };
	static int[] deltaY = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		tForest = new char[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String tmp = in.readLine();
			for (int j = 0; j < C; j++) {
				tForest[i][j] = tmp.charAt(j);
				if (tForest[i][j] == 'S') {
					queueS.add(new Point(i, j));
				}
				// 물의 위치가 여러개일 수 있기 때문에 큐에 넣어준다.
				if (tForest[i][j] == '*') {
					water.add(new Point(i, j));
				}
			}
		}

		while (true) {
			
			// 큐에 있는 물의 위치만큼 먼저 물을 바꿔준다.
			int size = water.size();
			for (int i = 0; i < size; i++) {
				simulW(water.peek().i, water.poll().j);
			}
			
			// 물이 채워진 후 고슴도치 이
			int n = simulS();

			time++;
			
			// 1이면 목적지 도착으로 while문 break
			if (n == 1)
				break;

			// 2이면 고슴도치가 더이상 움직일 수 없어서 "KAKTUS"출력후 return
			if (n == 2) {
				System.out.println("KAKTUS");
				return;
			}

		}

		System.out.println(time);
		in.close();
	}

	private static int simulS() {
		int size = queueS.size();
		int currentX, currentY;
		while (size-- > 0) {
			int r = queueS.peek().i;
			int c = queueS.poll().j;
			for (int n = 0; n < deltaX.length; n++) {
				currentX = r + deltaX[n];
				currentY = c + deltaY[n];

				if (currentX < 0 || currentX >= R || currentY < 0 || currentY >= C)
					continue;

				if (tForest[currentX][currentY] == 'D') {
					return 1;
				}
				if (tForest[currentX][currentY] == '.' && !visited[currentX][currentY]) {
					visited[r][c] = true;
					tForest[r][c] = '.';
					tForest[currentX][currentY] = 'S';
					visited[currentX][currentY] = true;
					queueS.add(new Point(currentX, currentY));
				}
			}
		}
		// 추가된 고슴도치 위치가 없다면 움직일 수 없으므로 "KAKTUS"를 출력하기 위해서 return;
		if (queueS.isEmpty())
			return 2;
		return 0;
	}

	private static void simulW(int r, int c) {
		for (int n = 0; n < deltaX.length; n++) {
			int currentWR = r + deltaX[n];
			int currentWC = c + deltaY[n];

			if (currentWR < 0 || currentWR >= R || currentWC < 0 || currentWC >= C)
				continue;

			if (tForest[currentWR][currentWC] == '.') {
				water.add(new Point(currentWR, currentWC));
				tForest[currentWR][currentWC] = '*';
			}
		}
	}
}
