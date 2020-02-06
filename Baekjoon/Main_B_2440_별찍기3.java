package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_2440_별찍기3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		in.close();
	}

}
