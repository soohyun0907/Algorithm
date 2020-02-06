package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_15684_사다리조작 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 세로선의 개수
		int M = Integer.parseInt(st.nextToken()); // 가로선의 개수
		// 각 세로선마다 가로선을 놓을 수 있는 위치의 개수
		int H = Integer.parseInt(st.nextToken());
		int answer = 0;
		
		// 1부터 배열 시작
		int[][] ladder = new int[H+1][N+1];
		
		// M개의 줄에 가로선의 정보 입력받기 + 가로선의 정보 배열에 저장하기(1로 표시하기)
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int li = Integer.parseInt(st.nextToken());
			int lj = Integer.parseInt(st.nextToken());
			ladder[li][lj] = 1;
			if(lj == N)
				ladder[li][lj-1] = 1;
			ladder[li][lj+1] = 1;
		}
		
		int[] countInfo = new int[N+1];
		// 각 세로선마다 1로 표시된 갯수 세고 홀수라면 1카운트
		for (int i = 1; i <= N; i++) {
			int count = 0;
			for (int j = 1; j <= H; j++) {
				/*if(countInfo[i]!=0 && countInfo[i]%2 == 0)
					continue;*/
				if(ladder[j][i] == 1)
					count++;
			}
			countInfo[i] = count;
			if(countInfo[i] > M || count > 3) {
				System.out.println("-1");
				break;
			}
			if(countInfo[i]%2 == 0) {
				continue;
			}
			else {
				answer++;
				countInfo[i]++;
				if(i+1 <= N)
					countInfo[i+1]++;
			}
		}
		
		// 카운트 된 값이 3보다 큰 값이거나 불가능한 경우에는 -1 출력
		if(answer > 3)
			System.out.println("-1");
		else
			System.out.println(answer);

	}

}
