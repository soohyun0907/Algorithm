package com.ssafy.divide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_2630_색종이만들기 {

	static int N, countW = 0, countB = 0;
	static int[][] paper;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		paper = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		divide(0, 0, N);
		System.out.println(countW + "\n" + countB);
		in.close();
	}

	private static void divide(int x, int y, int size) {
		int sum = 0;
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				sum += paper[i][j];
			}
		}

		if (sum == size * size) {
			countB++;
		} else if (sum == 0) {
			countW++;
		} else {
			int newSize = size / 2;
			divide(x, y, newSize); // 좌상
			divide(x, y + newSize, newSize); // 우상
			divide(x + newSize, y, newSize); // 좌하
			divide(x + newSize, y + newSize, newSize); // 우하
		}
	}
}
