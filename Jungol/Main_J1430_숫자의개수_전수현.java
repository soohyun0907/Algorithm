import java.util.Scanner;

public class Main_J1430_숫자의개수_전수현 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();

		int mul = a * b * c;
		String result = Integer.toString(mul);
		char[] temp = result.toCharArray();
		int[] ans = new int[10];

		for (int i = 0; i < temp.length; i++) {
			ans[temp[i] - '0']++;
		}

		for (int i = 0; i < 10; i++) {
			System.out.println(ans[i]);
		}

		sc.close();
	}

}
