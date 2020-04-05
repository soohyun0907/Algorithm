import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun 
 * 메모리 : 13,024 KB 
 * 실행시간 : 80 ms 
 * 코드길이 : 971 B 
 * 소요시간 : 20 M
 */

public class Main_B_2606_바이러스_union {

	static int N;
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		parent = new int[N + 1];
		int cnt = 0;

		for (int m = 0; m < M; m++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}

		for (int n = 2; n < N + 1; n++) {
			if (findSet(1) == findSet(n))
				cnt++;
		}

		System.out.print(cnt);
		in.close();
	}

	private static void union(int a, int b) {
		a = findSet(a);
		b = findSet(b);

		if (a != b) {
			parent[b] = a;
		}
	}

	private static int findSet(int a) {
		if (parent[a] < 1)
			return a;
		return parent[a] = findSet(parent[a]);
	}
}
