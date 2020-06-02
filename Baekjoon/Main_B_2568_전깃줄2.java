import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 49,032 KB 
 * 시간 : 508 ms 
 * 코드길이 : 2,150 B 
 * 소요시간 : 2H 10M
 */

public class Main_B_2568_전깃줄2 {

	static class Line {
		int start;
		int dest;

		public Line(int start, int dest) {
			this.start = start;
			this.dest = dest;
		}

	}

	static int N;
	static boolean[] checked;
	static Line[] line;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		checked = new boolean[N];
		line = new Line[N];
		int A, B;

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(in.readLine(), " ");
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			line[n] = new Line(A, B);
		} // end input

		// 출발지를 기준으로 정렬
		Arrays.sort(line, new Comparator<Line>() {

			@Override
			public int compare(Line o1, Line o2) {
				return o1.start - o2.start;
			}

		});

		lisPath();
		System.out.println(answer);
		in.close();
	} // end main

	private static void lisPath() {
		int[] lis = new int[N];
		int[] lisIndex = new int[N];
		int[] preIndex = new int[N];
		int size = 0, temp;

		for (int i = 0; i < N; ++i) {
			temp = -(Arrays.binarySearch(lis, 0, size, line[i].dest)) - 1;
			if (temp < 0)
				temp = -(temp) - 1; // 같은 숫자를 찾을 경우는 패스
			lis[temp] = line[i].dest;
			lisIndex[temp] = i;
			// 자신의 위치가 0(첫번째)이면 이전은 없고, 자신의 위치가 0이 아니라면 현재 lis 구성의 바로 직전이 이전됨.
			preIndex[i] = (temp == 0) ? -1 : lisIndex[temp - 1];

			if (temp == size)
				++size;
		}
		answer.append(N - size).append('\n');
		// 경로 출력을 위한 코드
		int cur = lisIndex[size - 1];
		while (cur != -1) {
			checked[cur] = true;
			cur = preIndex[cur];
		}
		for (int i = 0; i < N; i++) {
			if (!checked[i])
				answer.append(line[i].start).append('\n');
		}
	}
}