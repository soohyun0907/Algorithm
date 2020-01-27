import java.util.Scanner;

public class Solution_D3_3307 {
	
	public static int max(int[] ans) {
		int maxnum = 0;
		
		for(int i=0;i<ans.length;i++) {
			if(maxnum < ans[i])
				maxnum = ans[i];
		}
		
		return maxnum;
	}
	
	public static int longArray(int[] intArray) {
		int len = 0;
		int[] ans = new int[intArray.length];

		for(int j=0;j<intArray.length;j++) {
			len = 0;
			int i = j+1;
			int temp = intArray[j];
			while(i != intArray.length) {
				if(temp <= intArray[i]) {
					len++;
					temp = intArray[i];
				}
				i++;
			}
			if(len != 0)
				len++;
			
			ans[j] = len;
		}
		
		len = max(ans);
		
		return len;
	}

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] intArray = null;
		
		for(int i=1; i<=T; i++) {
			int len = sc.nextInt();
			intArray = new int[len];
			sc.nextLine();
			String[] input = sc.nextLine().split(" ");
			for(int j=0;j<len;j++) {
				intArray[j] = Integer.parseInt(input[j]);
			}
			
			System.out.println("#"+i+" "+longArray(intArray));
		}
		
		sc.close();
	}

}
