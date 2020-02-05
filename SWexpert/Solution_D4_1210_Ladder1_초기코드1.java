import java.util.Scanner;

public class Solution_D4_1210_Ladder1 {

	static int[][] ladder;
	static int size = 100;
	static int TCsize = 10;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ladder = new int[size][size];

		for (int t = 0; t < TCsize; t++) {
			int Tnum = sc.nextInt();

			for (int n = 0; n < size; n++) {
				for (int m = 0; m < size; m++) {
					ladder[n][m] = sc.nextInt();
				}
			}

			for (int i = 0; i < size; i++) {
				int ans = 0;
				if (ladder[0][i] == 1)
					ans = getDestination(0, i);
				if (ans == 2) {
					System.out.println("#" + Tnum + " " + i);
					break;
				}
			}
		}

		sc.close();

	}

	private static int getDestination(int n, int m) {

		while (n != size - 1) {
			if (m != size - 1 && ladder[n][m + 1] == 1) {
				m = turnRight(n, m + 1);
				n++;
			} else if (m != 0 && ladder[n][m - 1] == 1) {
				m = turnLeft(n, m - 1);
				n++;
			} else {
				n++;
			}
		}

		return ladder[n][m];
	}

	private static int turnRight(int n, int m) {

		while (m != size - 1) {
			if (ladder[n][m] == 0)
				return m - 1;
			m++;
		}

		return m;
	}

	private static int turnLeft(int n, int m) {

		while (m != 0) {
			if (ladder[n][m] == 0)
				return m + 1;
			m--;
		}

		return m;
	}

}
