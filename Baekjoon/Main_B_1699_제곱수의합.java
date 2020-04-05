import java.util.Scanner;

/**
 * @author soohyun
 * 메모리 : 14,984 KB
 * 실행시간 : 168 ms
 * 코드길이 : 375 B
 * 소요시간 : 1H 30M
 */

public class Main_B_1699_제곱수의합 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int d[] = new int[N+1];
		
		for(int i=1;i<=N;i++)
			d[i] = i;
		
		for(int i=2;i<=N;i++) {
			for(int j=2; j*j<=i;j++) {
				d[i] = Math.min(d[i], d[i-j*j]+1);
			}
		}

		System.out.println(d[N]);
		sc.close();
	}
	
}