import java.util.Scanner;

public class Main_J2046_숫자사각형4_전수현 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();

		switch (m) {
		case 1:
			for (int i = 1; i <= n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(i + " ");
				}
				System.out.println();
			}
			break;
		case 2:
			for (int i = 0; i < n; i++) {
				if (i % 2 == 0) {
					for (int j = 1; j <= n; j++) {
						System.out.print(j + " ");
					}
				} else {
					for (int j = n; j > 0; j--) {
						System.out.print(j + " ");
					}
				}
				System.out.println();
			}
			break;
		case 3:
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					System.out.print(i * j + " ");
				}
				System.out.println();
			}
			break;
		}

		sc.close();
	}

}
