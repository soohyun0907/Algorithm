import java.util.Scanner;

public class Main_J1692_곱셈 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();

		int temp1 = n * (m % 10);
		int temp2 = n * (((m - (m % 10)) / 10) % 10);
		int temp3 = n * (m / 100);

		System.out.println(temp1 + "\n" + temp2 + "\n" + temp3 + "\n" + n * m);
	}

}
