import java.util.Scanner;

public class Main_J1304_숫자사각형3_전수현 {

	static int[][] intSquare;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		intSquare = new int[n][n];
		int num = 1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				intSquare[j][i] = num++;
			}
		}

		print();

		sc.close();

	}

	private static void print() {
		int size = intSquare.length;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(intSquare[i][j] + " ");
			}
			System.out.println();
		}
	}

}
