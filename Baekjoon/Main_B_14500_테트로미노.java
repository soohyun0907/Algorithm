import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 34,188 KB
 * 시간 : 476 ms
 * 코드길이 : 1,981 B
 * 소요시간 : 1H 50M
 */

public class Main_B_14500_테트로미노 {

	static int N, M;
	static int[][] map;
	static int[][] tetromino = { {0,1},{1,0},{1,1} // 0 ㅁ
								,{0,1},{0,2},{0,3} // 1 ㅡ
								,{1,0},{2,0},{3,0} // 2 ㅣ
								,{1,0},{1,1},{1,2} // 3 ㄴ
								,{0,1},{0,2},{-1,2} // 4 ㄴ대칭
								,{0,1},{0,2},{1,2} // 5 ㄱ
								,{1,0},{0,1},{0,2} // 6 ㄱ대칭
								,{1,0},{2,0},{2,1} // 7 ㄴ 90도 회전 후 대칭
								,{0,1},{-1,1},{-2,1} //8 ㄴ 90도 회전
								,{0,1},{1,0},{2,0} // 9 ㄱ 90도 회전
								,{0,1},{1,1},{2,1} // 10 ㄱ 90도 회전 후 대칭
								,{1,0},{1,1},{2,0} // 11 ㅏ
								,{1,0},{1,1},{2,1} // 12 ㄴㄱ
								,{1,0},{0,1},{-1,1} // 13 ㄴㄱ 대칭
								,{0,1},{-1,1},{-1,2} // 14 ㄱ_ 대칭 
								,{0,1},{1,1},{1,2} // 15 ㄱ_
								,{0,1},{-1,1},{0,2} // 16 ㅗ
								,{0,1},{1,1},{0,2} // 17 ㅜ
								,{0,1},{-1,1},{1,1} }; //18 ㅓ
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end input

		int tmp = 0, result = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp = cal(i, j);
				result = Math.max(result, tmp);
			}
		}

		System.out.println(result);
	} // end main
	
	private static int cal(int i, int j) {
		int curX, curY, sum, max = Integer.MIN_VALUE;
		for (int k = 0; k < tetromino.length; k += 3) {
			sum = map[i][j];
			for (int n = k, cnt = 0; cnt < 3; cnt++) {
				curX = i + tetromino[n + cnt][0];
				curY = j + tetromino[n + cnt][1];

				if (curX < 0 || curX >= N || curY < 0 || curY >= M) break;

				sum += map[curX][curY];
			}
			max = Math.max(max, sum);
		}
		return max;
	}

}
