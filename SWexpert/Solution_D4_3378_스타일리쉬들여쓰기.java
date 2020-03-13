import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun
 * 메모리 : 30,784 kb
 * 실행시간 : 124 ms
 * 코드길이 : 3,557 B
 */

public class Solution_D4_3378_스타일리쉬들여쓰기 {

	private static int[][] m, dap;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			sb.append('#').append(testCase).append(" 0"); // 첫번째 줄의 들여쓰기는 0으로 일정.
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken()); // 마스터의 코드 줄수.
			int q = Integer.parseInt(st.nextToken()); // 나의 코드 줄수.

			m = new int[p][4]; // . 소괄호 중괄호 대괄호 순으로 저장.
			for (int i = 0; i < p; i++) {
				// 한 줄을 입력받아서
				String line = br.readLine();
				// 앞부분에 나온 .의 개수
				int index = 0;
				while (line.charAt(index) == '.') {
					index++;
				}
				m[i][0] = index;
				// 괄호의 개수는 누적처리
				if (i > 0) {
					m[i][1] = m[i - 1][1];
					m[i][2] = m[i - 1][2];
					m[i][3] = m[i - 1][3];
				}

				for (int j = index; j < line.length(); j++) {
					switch (line.charAt(j)) {
					case '(':
						m[i][1]++;
						break;
					case ')':
						m[i][1]--;
						break;
					case '{':
						m[i][2]++;
						break;
					case '}':
						m[i][2]--;
						break;
					case '[':
						m[i][3]++;
						break;
					case ']':
						m[i][3]--;
						break;
					}
				}
			} // 마스터의 스타일리쉬 코드분석 for문.

			// 내 코드 분석.
			dap = new int[q][4]; // . 소괄호 중괄호 대괄호 순으로 저장.
			for (int i = 0; i < q; i++) {
				// 한 줄을 입력받아서
				String line = br.readLine();
				// 앞부분에 나온 .의 개수
				int index = 0;

				// 괄호의 개수는 누적처리
				if (i > 0) {
					dap[i][1] = dap[i - 1][1];
					dap[i][2] = dap[i - 1][2];
					dap[i][3] = dap[i - 1][3];
				}

				for (int j = index; j < line.length(); j++) {
					switch (line.charAt(j)) {
					case '(':
						dap[i][1]++;
						break;
					case ')':
						dap[i][1]--;
						break;
					case '{':
						dap[i][2]++;
						break;
					case '}':
						dap[i][2]--;
						break;
					case '[':
						dap[i][3]++;
						break;
					case ']':
						dap[i][3]--;
						break;
					}
				}
			} // 내 코드분석 for문.

			// dap[i][0] : 초기값 -2 .의 개수를 몇개인지 저장.
			for (int i = 0; i < q; i++) {
				dap[i][0] = -2;
			}

			// 중복 순열.
			for (int R = 1; R <= 20; R++) {
				for (int C = 1; C <= 20; C++) {
					for (int S = 1; S <= 20; S++) {
						if (check(R, C, S)) { // 마스터 코드에서 해가 되는가?
							cal(R, C, S);
						}
					}
				}
			}

			for (int i = 1; i < dap.length; i++) {
				sb.append(' ').append(dap[i][0]);
			}
			sb.append('\n');
		}

		System.out.print(sb);
		br.close();
	}

	// 내 코드에서 해가 들여쓰기를 각 라인에 몇개씩 해야하는지 구해서 dap 배열에 저장.
	public static void cal(int R, int C, int S) {
		for (int i = 1; i < dap.length; i++) {
			int x = dap[i - 1][1] * R + dap[i - 1][2] * C + dap[i - 1][3] * S;
			if (dap[i][0] == -2) { // 답을 구한적이 없으면.
				dap[i][0] = x;
			} else if (dap[i][0] != x) { // 기존 값과 다른 들여쓰기 값이 생긴다면.
				dap[i][0] = -1;
			}
		}
	}

	// 마스터 코드에서 해가 되는지 체크해서 리턴.
	public static boolean check(int R, int C, int S) {
		for (int i = 1; i < m.length; i++) {
			if (m[i][0] != m[i - 1][1] * R + m[i - 1][2] * C + m[i - 1][3] * S) {
				return false;
			}
		}
		return true;
	}

}
