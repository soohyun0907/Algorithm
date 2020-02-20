package com.ssafy.subset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_1931_회의실배정 {

	static class Meeting implements Comparable<Meeting> {
		int start, end;

		public Meeting(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			int res = this.end - o.end;
			if (res == 0) {
				res = this.start - o.start;
			}
			return res;
		}
	}

	static int N;
	static Meeting[] list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		list = new Meeting[N];

		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			list[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(list);
		ArrayList<Meeting> schedule = new ArrayList<Meeting>();
		schedule.add(list[0]);
		for (int i = 1; i < N; i++) {
			if (schedule.get(schedule.size() - 1).end <= list[i].start) {
				schedule.add(list[i]);
			}
		}
		System.out.println(schedule.size());
	}

}
