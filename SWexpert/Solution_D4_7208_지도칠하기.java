import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 21,096 KB
 * 시간 : 126 ms
 * 코드길이 : 1,957 B
 */

public class Solution_D4_7208_지도칠하기 {

	static int N; // 국가 숫자
	static int map[][]; // 인접 국가 위치
	static int color[]; // 국가에 배정된 color
	static int fill[]; // 새로 배정 시킬 color => 순열
	static int min; // color에 대한 최소 변경 값

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int testN = Integer.parseInt(in.readLine().trim());
		StringTokenizer st = null;
		for (int t = 0; t < testN; ++t) {
			N = Integer.parseInt(in.readLine().trim());
			map = new int[N][N];
			color = new int[N];
			fill = new int[N];
			st = new StringTokenizer(in.readLine().trim());
			for (int i = 0; i < N; i++) { // color값 읽기
				color[i] = Integer.parseInt(st.nextToken().trim());
			}
			// 국가 인접 정보 load
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken().trim());
				}
			}
			min = Integer.MAX_VALUE;
			dfs(0);
			answer.append('#').append(t + 1).append(' ').append(min).append('\n');
		}
		
		System.out.print(answer);
		in.close();
	}

	private static boolean allAvailable() {
		for (int i = 0; i < N; i++) { // 현재 노드
			for (int j = 0; j < N; j++) {// 인접 노드
				if (map[i][j] == 1 && fill[i] == fill[j]) {
					return false;
				}
			}
		}
		return true;
	}

	private static void dfs(int cnt) {
		if (cnt == N) {
			if (allAvailable()) {
				// 새로 생성한 색상 값이 인접 국가간에 중복이 없으면
				int change = 0;
				for (int i = 0; i < N; i++) {
					if (color[i] != fill[i]) {
						change++;
					}
				}
				min = Math.min(min, change);
			}
			return;
		}
		for (int i = 1; i <= 4; i++) {
			fill[cnt] = i;
			dfs(cnt + 1);
		}
	}
}