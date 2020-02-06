package com.ssafy.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B_2667_단지번호붙이기_전수현_dfs {

	static int N, house = 0, town = 0;
	static int[][] map;
	static boolean[][] visited;
	static List<Integer> houseArray = new ArrayList<>();

	static int[] deltaX = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int[] deltaY = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder answer = new StringBuilder();

		// N = 지도의 크기
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - 48;
			}
		}

		// dfs
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					house = 0;
					dfs(i, j);
					houseArray.add(house);
					town++;
				}
			}
		} 

		Collections.sort(houseArray);

		answer.append(town).append("\n");
		for (int i = 0; i < houseArray.size(); i++) {
			answer.append(houseArray.get(i)).append("\n");
		}

		System.out.println(answer);
		in.close();
	}

	// dfs - 재귀를 타고 들어가서 깊게 들어가는 구조.
	// 주변에 같은 1을 만나면 이동해서 다시 탐색하는 형식
	private static void dfs(int i, int j) {
		visited[i][j] = true;
		house++;
		for (int n = 0; n < deltaX.length; n++) {
			int nextX = i + deltaX[n];
			int nextY = j + deltaY[n];

			if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N)
				continue;

			if (map[nextX][nextY] == 1 && !visited[nextX][nextY]) {
				dfs(nextX, nextY);
			}
		}
	}
}
