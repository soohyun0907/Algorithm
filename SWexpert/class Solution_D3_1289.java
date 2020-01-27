import java.util.Scanner;

public class Solution_D3_1289 {
	
	public static int count(String memory) {
		int edit = 0;
		int n = 1;
		
		
		if(memory.charAt(0) == '1') {
			edit++;
		}
		
		while(n != memory.length()) {
			if(memory.charAt(n-1) != memory.charAt(n))
				edit++;
			n++;
		}
		
		return edit;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		String memory = null;
		
		for(int i=1;i<=T;i++) {
			memory = sc.next();
			int ans = count(memory);
			System.out.println("#"+ i +" " + ans);
		}
		
		sc.close();
	}

}
