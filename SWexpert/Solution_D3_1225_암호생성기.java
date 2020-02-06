package com.ssafy.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D3_1225_암호생성기 {

	static int SIZE = 8;
	static Queue<Integer> password = new LinkedList<Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		
		for (int tc = 0; tc < 10; tc++) {	
			int num = 1;
			st = new StringTokenizer(in.readLine());
			int tcNum = Integer.parseInt(st.nextToken());
			answer.append("#").append(tcNum).append(" ");
			
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < SIZE; i++) {
				password.offer(Integer.parseInt(st.nextToken()));
			}
			
			while(num != 0) {
				for (int j = 1; j <= 5; j++) {
					num = password.poll();
					num -= j;
					if(num <= 0) {
						num = 0;
						password.offer(num);
						break;
					}
					password.offer(num);
				}
			}
			
			for (int i = 0; i < 8; i++) {
				answer.append(password.poll()).append(" ");
			}
			answer.append("\n");
		}
		
		System.out.print(answer);
	}

}
