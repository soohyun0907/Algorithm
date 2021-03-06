﻿import java.util.Scanner;

public class Main_J1307_문자사각형1 {

	static char[][] charArray;
	static int n;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		char ch = 'A';
		charArray = new char[n][n];

		for (int i = n - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				charArray[j][i] = ch++;
				if (ch == '[')
					ch = 'A';
			}
		}

		print();

		sc.close();
	}

	private static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(charArray[i][j] + " ");
			}
			System.out.println();
		}
	}

}
