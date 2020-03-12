import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author soohyun 
 * 메모리 : 144,240 KB 
 * 실행시간 : 633 ms 
 * 코드길이 : 1,098 B
 */

public class Solution_D4_7701_염라대왕의이름정렬_수업 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			int N = Integer.parseInt(br.readLine());

			TreeSet<String> name = new TreeSet<String>(new Comparator<String>() {
				public int compare(String pre, String next) {
					if (pre.length() != next.length()) { // 글자의 길이 짧은 순서.
						return pre.length() - next.length();
					} else { // 길이가 같으면, 사전순으로.
						return pre.compareTo(next);
					}
				}
			});

			for (int i = 0; i < N; i++) {
				name.add(br.readLine());
			}

			sb.append("#").append(testCase).append("\n");
			for (String str : name) {
				sb.append(str).append("\n");
			}
		} // end of testCase

		System.out.print(sb);
		br.close();
	}
}