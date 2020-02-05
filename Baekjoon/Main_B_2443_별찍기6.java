package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_2443_별찍기6 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < N; i++) {
			for(int j=0;j<i;j++) {
				ans.append(' ');			
			}
			for (int j = (N-i)*2-1; j > 0; j--) {
				ans.append('*');
			}
			ans.append('\n');
		}
		
		System.out.print(ans);
		in.close();
	}

}
