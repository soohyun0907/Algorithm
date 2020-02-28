import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_1209_Sum {

	static int SIZE = 100, max;
	static int[][] numbers = new int[SIZE][SIZE];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			max = 0;
			st = new StringTokenizer(in.readLine());
			int T = Integer.parseInt(st.nextToken());
			answer.append("#").append(T).append(" ");

			for (int i = 0; i < SIZE; i++) {
				st = new StringTokenizer(in.readLine());
				int sum = 0;
				for (int j = 0; j < SIZE; j++) {
					numbers[i][j] = Integer.parseInt(st.nextToken());
					sum += numbers[i][j];
				}
				if (max < sum) {
					max = sum;
				}
			}

			for (int i = 0; i < SIZE; i++) {
				int sum = 0;
				for (int j = 0; j < SIZE; j++) {
					sum += numbers[j][i];
				}
				if (max < sum) {
					max = sum;
				}
			}

			int sum = 0, sum2 = 0;
			for (int i = 0; i < SIZE; i++) {
				sum += numbers[SIZE - i - 1][i];
				sum2 += numbers[i][i];
			}
			if (sum < sum2)
				sum = sum2;
			if (max < sum)
				max = sum;

			answer.append(max).append("\n");
		}

		System.out.println(answer);
		in.close();
	}

}
