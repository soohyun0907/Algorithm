import java.util.Scanner;

public class Solution_D2_1204_최빈수구하기_전수현 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int i=0; i<T; i++) {
			int[] score = new int[101];
			int TN = sc.nextInt();
			sc.nextLine();
			String[] input = sc.nextLine().split(" ");
			for(int j=0;j<input.length;j++) {
				score[Integer.parseInt(input[j])]++;
			}
			
			int max = score[0];
			int maxIndex = 0;
			for(int j=1;j<100;j++) {
				if(max <= score[j]) {
					max = score[j];
					maxIndex = j;
				}
			}
			
			System.out.println("#" + TN + " " + maxIndex);
		}
	}

}
