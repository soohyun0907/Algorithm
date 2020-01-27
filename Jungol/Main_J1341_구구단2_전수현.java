import java.util.Scanner;

public class Main_J1341_구구단2_전수현 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();
		int e = sc.nextInt();

		if (s < 2 || s > 9 || e < 2 || e > 9) {
			System.out.println("INPUT ERROR!");
			s = sc.nextInt();
			e = sc.nextInt();
		}

		if (s < e) {
			for (int i = s; i <= e; i++) {
				for (int j = 1; j < 10; j++) {
					System.out.printf("%1d *%2d = %2d   ", i, j, i * j);
					if (j % 3 == 0)
						System.out.println();
				}
				System.out.println();
			}
		} else {
			for (int i = s; i >= e; i--) {
				for (int j = 1; j < 10; j++) {
					System.out.printf("%1d *%2d = %2d   ", i, j, i * j);
					if (j % 3 == 0)
						System.out.println();
				}
				System.out.println();
			}
		}

		sc.close();

	}

}
