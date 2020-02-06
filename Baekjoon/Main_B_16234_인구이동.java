package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B_16234_인구이동 {

	static int N, L, R;
	static int sum = 0, count = 0, answer = 0, popul = 0;
	static int[][] land;
	static boolean[][] selectedLand;
	static int[] deltaX = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int[] deltaY = { 0, 1, 0, -1 };

	// 연합한 땅의 좌표 저장 하는 List
	static List<List<Integer[]>> coperList = new ArrayList<List<Integer[]>>();
	static int coperNum = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		// 숫자 N입력 받아서 N*N크기의 배열 생성(땅)
		N = Integer.parseInt(st.nextToken());
		land = new int[N][N];
		selectedLand = new boolean[N][N];

		// 숫자 L, R 입력 받기 (L보다 크고 R보다 작음)
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		// 둘째줄부터 N번째 줄까지 각 나라의 인구수 (land[n][m])
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(in.readLine());
			for (int m = 0; m < N; m++) {
				land[n][m] = Integer.parseInt(st.nextToken());
			}
		}

		// 반복탐색
		while (true) {
			sum = 0;
			count = 0;
			coperNum = 0;
			// 좌표 하나씩 방문하며 인구수 차이 구하고 true로 값 바꿔주기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!selectedLand[i][j]) {
						coperList.add(new ArrayList<Integer[]>());
						searchLand(i, j);
						// true값인 부분 더하기 + 카운트 + 인구이동
						coperNum++;
//						System.out.println(count + " " + coperNum);
					}
				}
			}
			
			if(coperNum == N*N) {
				break;
			}
			if (count == 0)
				break;
			
			for (int i = 0; i < coperList.size(); i++) {
				count = 0;
				for(Integer[] var : coperList.get(i)) {
					sum += land[var[0]][var[1]];
					count++;
				}
				for(Integer[] var : coperList.get(i)) {
					land[var[0]][var[1]] = sum / count;
					selectedLand[var[0]][var[1]] = false;
				}
				sum = 0;
			}
			
			/*for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(land[i][j] + " ");
				}
				System.out.println();
			}*/
			
			answer++;
			coperList.clear();
		}

		System.out.println(answer);
		in.close();
	}

	// 입력 받은 좌표에서부터 인접한 나라와의 인구수 차를 구하고 true로 바꿔주기
	public static void searchLand(int n, int m) {
		// 이미 방문한 땅이라면 return
		if (selectedLand[n][m]) {
			coperList.get(coperNum).add(new Integer[] {n,m});
			count++;
			return;
		}

		for (int i = 0; i < deltaX.length; i++) {
			int nextX = n + deltaX[i];
			int nextY = m + deltaY[i];

			if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N)
				continue;

			// L보다 크고 R보다 작다면 해당 위치 selectedLand = true로 바꾸
			int sub = Math.abs(land[n][m] - land[nextX][nextY]);
			if (sub >= L && sub <= R) {
				if (!selectedLand[n][m]) {
					coperList.get(coperNum).add(new Integer[] {n,m});
					count++;
					selectedLand[n][m] = true;
				}
				if (selectedLand[nextX][nextY]) {
					continue;
				}
				searchLand(nextX, nextY);
				selectedLand[nextX][nextY] = true;
			}
		}
	}
}
