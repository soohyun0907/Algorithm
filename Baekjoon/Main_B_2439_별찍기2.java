package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_2439_별찍기2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i+j >= N-1)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		
		in.close();
	}

}
