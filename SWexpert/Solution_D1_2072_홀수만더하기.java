package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D1_2072_홀수만더하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder answer = new StringBuilder();
		int num, sum = 0;
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1;tc<=T;tc++) {
			answer.append("#").append(tc).append(" ");
			sum = 0;
			st = new StringTokenizer(in.readLine());
			for(int i=0;i<10;i++) {
				num = Integer.parseInt(st.nextToken());
				if(num%2==1)
					sum += num;
			}
			
			answer.append(sum).append("\n");
		}
		
		System.out.println(answer);
		in.close();
	}

}
