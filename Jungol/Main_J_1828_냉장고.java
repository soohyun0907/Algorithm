package Jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J_1828_냉장고 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int[][] frig = new int[N][2];
		int count = 1;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine()," ");
			frig[i][0] = Integer.parseInt(st.nextToken());
			frig[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 최소값 기준으로 정렬
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				if(Integer.compare(frig[i][1], frig[j][1]) > 0) {
					int min = frig[i][0];
					int max = frig[i][1];
					frig[i][0] = frig[j][0];
					frig[i][1] = frig[j][1];
					frig[j][0] = min;
					frig[j][1] = max;
				}
			}
		}
		
		int max = frig[0][1];
		for (int i = 0; i < N-1;i++) {
			for (int j = i+1; j < N; j++) {
				// 최댓값 비교해서 최댓값보다 작은 최솟값은 continue
				if(max >= frig[j][0])
					continue;
				else {
					max = frig[j][1];
					i = j-1;
					count++;
					break;
				}
			}
		}
		
		System.out.println(count);
		in.close();
	}

}
