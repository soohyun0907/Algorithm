import java.util.Arrays;
import java.util.Scanner;

public class Solution_D1_2063_중간값찾기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] intArray = new int[N];
		sc.nextLine();
		String[] input = sc.nextLine().split(" ");
		for(int i=0;i<N;i++) {
			intArray[i] = Integer.parseInt(input[i]);
		}
		
		Arrays.sort(intArray);
		
		System.out.println(intArray[N/2]);
	}

}
