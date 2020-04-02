import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 12,912 KB
 * 시간 : 72 ms
 * 코드길이 : 755 B
 */

public class Main_B_2675_문자열반복 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			String str = st.nextToken();

			for (int i = 0; i < str.length(); i++) {
				for (int m = 0; m < n; m++) {
					answer.append(str.charAt(i));
				}
			}
			answer.append('\n');
		}

		System.out.println(answer);
		in.close();
	}

}
