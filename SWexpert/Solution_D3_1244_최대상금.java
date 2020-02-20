package com.ssafy.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution_D3_1244_최대상금 {

	static int T, N, size, maxNum;
	static String num;
	static int[] numbers = new int[6];
	static HashSet<String> s = new HashSet<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder answer = new StringBuilder();
		T = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= T; i++) {
			maxNum = 0;
			answer.append("#").append(i).append(" ");
			st = new StringTokenizer(in.readLine(), " ");
			num = st.nextToken();
			size = num.length();
			for (int j = 0; j < size; j++) {
				numbers[j] = num.charAt(j) - '0';
			}

			N = Integer.parseInt(st.nextToken());

			maxPrice(N);
			answer.append(maxNum).append("\n");
		}

		System.out.println(answer);
		in.close();
	}

	private static void maxPrice(int N) {
		int val = 0;
		for (int i = 0; i < size; i++) {
			val = val * 10 + numbers[i];
		}
		if (s.contains("" + val + N))
			return;

		s.add("" + val + N);
		if (N == 0) {
			if (maxNum < val)
				maxNum = val;
			return;
		}

		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {
				swap(i, j);
				maxPrice(N - 1);
				swap(i, j);
			}
		}
	}

	private static void swap(int a, int b) {
		int temp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = temp;
	}

}
