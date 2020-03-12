import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * @author soohyun
 * 메모리 : 127,688 KB
 * 실행시간 : 658 ms
 * 코드길이 : 1,121 B
 */
public class Solution_D4_7701_염라대왕의이름정렬_pq {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int pqSize = 51; // 총 50개의 알파벳까지 넣을 수 있음.
		PriorityQueue<String> pq[] = new PriorityQueue[pqSize];
		for(int i=1;i<pqSize;i++) {
			pq[i] = new PriorityQueue<String>();
		}
		
		int T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			answer.append("#").append(t).append("\n");
			int N = Integer.parseInt(in.readLine());
			for (int n = 0; n < N; n++) {
				String tmp = in.readLine();
				pq[tmp.length()].offer(tmp);
			}
			
			for(int i=1;i<51;i++) {
				String s = "";
				while(!pq[i].isEmpty()) {
					String tmp = pq[i].poll().toString();
					if(tmp.equals(s)) {
						continue;
					}
					s = tmp;
					answer.append(tmp).append("\n");
				}
			}
		} // end test-case

		System.out.print(answer);
		in.close();
	}

}