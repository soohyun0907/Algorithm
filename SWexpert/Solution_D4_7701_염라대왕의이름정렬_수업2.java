import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * @author soohyun 
 * 메모리 : 143,392 KB 
 * 실행시간 : 631 ms 
 * 코드길이 : 944 B
 */

// 이름 글자의 개수가 골고루 포진되어 있을 때 시간을 많이 줄일 수 있다.
public class Solution_D4_7701_염라대왕의이름정렬_수업2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			sb.append('#').append(testCase).append('\n');
			int N = Integer.parseInt(br.readLine());

			TreeSet<String>[] name = new TreeSet[51];
			for (int i = 0; i < name.length; i++) {
				name[i] = new TreeSet<String>(); // 배열 각 칸에 생성해서 넣기.
			}

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				name[str.length()].add(str);
			}

			for (int i = 1; i < name.length; i++) {
				for (String str : name[i]) {
					sb.append(str).append('\n');
				}
			}
		} // end of testCase

		System.out.print(sb);
		br.close();
	}
}