import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class Solution_D4_7701_염라대왕의이름정렬 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			answer.append("#").append(t).append("\n");
			int N = Integer.parseInt(in.readLine());
			HashSet<String> nameList = new HashSet<>();

			for (int n = 0; n < N; n++) {
				String tmp = in.readLine();
				nameList.add(tmp);
			}

			ArrayList<String> names = new ArrayList<>(nameList);

			Collections.sort(names, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					if (o1.length() == o2.length())
						return o1.compareTo(o2);
					return o1.length() - o2.length();
				}
			});

			for (String name : names) {
				answer.append(name).append("\n");
			}
		} // end test-case

		System.out.print(answer);
		in.close();
	}

}