package com.ssafy.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_3289_서로소집합 {

	private static int[] parents;

	static int T, n, m;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();

		T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			answer.append("#").append(tc).append(" ");
			st = new StringTokenizer(in.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			parents = new int[n + 1];
			Arrays.fill(parents, -1);

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int c = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if (c == 0) {
					union(a, b);
				} else {
					answer.append(checkUnion(a, b));
				}
			}
			answer.append("\n");
		}

		System.out.println(answer);
		in.close();
	}

	private static int checkUnion(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot != bRoot) {
			return 0;
		}

		return 1;
	}

	// 합집합
	private static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
		}
	}

	// 부모 찾기
	private static int findSet(int a) {
		if (parents[a] < 0)
			return a;
		return parents[a] = findSet(parents[a]);
	}
}
