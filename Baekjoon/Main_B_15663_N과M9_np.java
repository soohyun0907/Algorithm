import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 37544 KB
 * 시간 : 2064 ms
 * 코드길이 : 1497 B
 */

public class Main_B_15663_N과M9_np {

	static int N, M;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		ArrayList<String> answer = new ArrayList<>();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N];

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numbers);

		do {
			String tmp = "";
			for (int i = 0; i < M; i++) {
				tmp += numbers[i] + " ";
			}
			tmp += "\n";
			if (!answer.contains(tmp))
				answer.add(tmp);
		} while (nextPermutation());

		for (int i = 0; i < answer.size(); i++) {
			System.out.print(answer.get(i));
		}

		in.close();
	}

	private static boolean nextPermutation() {
		int i = N - 1;
		while (i > 0 && numbers[i - 1] >= numbers[i])
			--i;
		if (i == 0)
			return false;

		int j = N - 1;
		while (numbers[i - 1] >= numbers[j])
			--j;

		int temp = numbers[i - 1];
		numbers[i - 1] = numbers[j];
		numbers[j] = temp;

		int k = N - 1;
		while (i < k) {
			temp = numbers[i];
			numbers[i] = numbers[k];
			numbers[k] = temp;
			i++;
			k--;
		}

		return true;
	}
}
