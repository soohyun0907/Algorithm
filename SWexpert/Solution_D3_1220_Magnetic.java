import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_D3_1220_Magnetic {

	static int N, count, total=0; // 정사각형 테이블의 한 변의 길이
	static int[][] table; // 정사각형의 테이블
	static Stack<Integer> stack = new Stack<Integer>();
	static boolean flag = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		
		for(int tc=1;tc<=10;tc++) {
			answer.append("#").append(tc).append(" ");
			count = 0;
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			table = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					table[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 1은 N극, 2는 S극 성질을 가진다.
			// 테이블 윗부분에 N(1)극 아랫부분에 S(2)극이 위치한다.
			for (int i = 0; i < N; i++) {
				int j = 0;
				flag = false;
				while(j<N) {
					if(table[j][i]==1) {
						flag = true;
					} else if(table[j][i] ==2) {
						if(flag) {
							count++;
							flag = false;
						}
					}
					j++;
				}
			}
			
			answer.append(count).append("\n");
		}
		
		System.out.println(answer);
		in.close();
	}

}