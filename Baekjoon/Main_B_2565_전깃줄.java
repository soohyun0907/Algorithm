import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 13,132 KB
 * 시간 : 72 ms
 * 코드길이 : 2,113 B
 * 소요시간 : 1H
 */

public class Main_B_2565_전깃줄 {

	static class Line {
		int start;
		int dest;

		public Line(int start, int dest) {
			this.start = start;
			this.dest = dest;
		}

	}

	static int N;
	static Line[] line;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		line = new Line[N];
		int[] temp = new int[N]; // LIS로 사용 가능한 숫자를 저장할 배열
		int[] path = new int[N]; // 경로를 저장할 배열, 역추적할 index를 저장
		int size = 0; // LIS 개수를 관리할 변수
		int A, B;

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(in.readLine(), " ");
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			line[n] = new Line(A, B);
		} // end input

		// 도착지를 기준으로 정렬
		Arrays.sort(line, new Comparator<Line>() {

			@Override
			public int compare(Line o1, Line o2) {
				return o1.dest - o2.dest;
			}

		});

		path[size] = -1; // 첫번째 숫자라는 의미
		temp[size++] = 0; // 첫번째 숫자의 index 반영
		for (int i = 1; i < N; i++) {
			if (line[temp[size - 1]].start < line[i].start) {
				path[i] = temp[size - 1];
				temp[size++] = i;
			} else {
				int tmp = binarySearch(temp, 0, size, line[i].start);
				if (tmp < 0)
					tmp = -tmp - 1;
				path[i] = path[temp[tmp]];
				temp[tmp] = i;
			}
		}

		System.out.println(N - size);
	} // end main

	private static int binarySearch(int[] temp, int fromIndex, int toIndex, int key) {
		int low = fromIndex;
		int high = toIndex - 1;

		while (low <= high) {
			int mid = (low + high) >>> 1;
			int midVal = line[temp[mid]].start;

			if (midVal < key)
				low = mid + 1;
			else if (midVal > key)
				high = mid - 1;
			else
				return mid; // key found
		}
		return -(low + 1); // key not found.
	}

}
