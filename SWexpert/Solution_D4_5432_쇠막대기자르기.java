package com.ssafy.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_D4_5432_쇠막대기자르기 {

	static Stack<Character> iron  = new Stack<Character>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder answer = new StringBuilder();
		int tc = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<=tc;i++) {
			int num = 0;
			answer.append("#").append(i).append(" ");
			String str = in.readLine();
			for (int j = 0; j < str.length();) {
				if(str.charAt(j)=='(' && str.charAt(j+1)==')') {
					num += iron.size();
					j++;
				} else {
					if(str.charAt(j) == ')') {
						num += 1;
						iron.pop();
					}
					else
						iron.push(str.charAt(j));
				}
				j++;
			}
			
			answer.append(num).append("\n");
		}	
		
		System.out.println(answer);
		in.close();
	}
}
