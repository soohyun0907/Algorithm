import java.util.Scanner;

public class Main_J1856_숫자사각형2 {

	static int[][] numSquare;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		numSquare = new int[n][m];
		int num = 1;

		// 바로 숫자 출력하는 코드 (배열 사용 X)
//		for(int i=0;i < n;i++) {
//			if(i % 2 == 0) {
//				for(int j=0; j < m; j++) {
//					System.out.print(num + " ");
//					num++;
//				}
//			} else {
//				num += m-1;
//				for(int j=0; j < m; j++) {
//					System.out.print(num + " ");
//					num--;
//				}
//				num += m+1;
//			}
//			System.out.println();
//		}

		// 2차원 배열을 만든 후 출력하는 방법
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				numSquare[i][j] = num++;
			}
		}

		print();

		sc.close();
	}

	public static void print() {
		final int row = numSquare.length;

		for (int i = 0; i < row; i++) {
			final int col = numSquare[0].length;
			for (int j = 0; j < col; j++) {
				System.out.print(numSquare[i][j + ((col - 1) - 2 * j) * (i % 2)] + " ");
			}
			System.out.println();
		}
	}

}
