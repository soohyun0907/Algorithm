package hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 밑에서부터 올라가기
public class Solution_D4_1210_Ladder1_개선 {
	static int[][] ladder;
	static int size = 100;
	static int TCsize = 10;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ladder = new int[size+2][size+2];
		int desty = 0;

		for (int t = 0; t < TCsize; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int Tnum = Integer.parseInt(st.nextToken());
			for (int n = 0; n < size; n++) {
				st = new StringTokenizer(br.readLine());
				for (int m = 0; m < size; m++) {
					ladder[n][m] = Integer.parseInt(st.nextToken());
					if(ladder[n][m] == 2) {
						desty = m;
					}
				}
			}
			
			System.out.println(desty);
			
			for(int i = 99; i>=0;i--) {
				if(ladder[i][desty - 1] == 1) {
					while(true) {
						desty--;
						if(ladder[i][desty-1] != 1) break;
					}
				}
				else if(ladder[i][desty+1] == 1) {
					while(true) {
						desty++;
						if(ladder[i][desty+1] != 1) break;
					}
				}
			}
			desty--;
			answer.append("#"+Tnum+" "+desty+"\n");
		}
		
		System.out.println(answer);
	}

}
