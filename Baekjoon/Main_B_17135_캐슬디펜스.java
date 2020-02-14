package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Enemy implements Comparable<Enemy> {
	Integer r, c, d;
	boolean isTargeted;

	public Enemy(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public int compareTo(Enemy o) {
		if (d.equals(o.d)) {
			return this.c.compareTo(o.c);
		} else {
			return this.d.compareTo(o.d);
		}
	}
}

public class Main_B_17135_캐슬디펜스 {

	static int SIZE = 3;
	static int N, M, D, max = 0;
	static int[][] castle;
	static int[] attack;
	static boolean[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		castle = new int[N][M];
		attack = new int[SIZE];
		selected = new boolean[M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				castle[i][j] = Integer.parseInt(st.nextToken());
				// 적군에 대한 정보 저장
			}
		}

		permutation(0);
		System.out.println(max);

		in.close();
	}

	private static void permutation(int index) {
		if (index == SIZE) {
			check(attack);
			return;
		}

		for (int i = 0; i < M; i++) {
			if (selected[i])
				continue;
			if (index != 0 && attack[index - 1] > i)
				continue;

			attack[index] = i;
			selected[i] = true;
			permutation(index + 1);
			selected[i] = false;
		}
	}

	private static void check(int[] attack2) {
		List<Enemy> enemies = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (castle[i][j] == 1)
					enemies.add(new Enemy(i, j));
			}
		}

		int count = 0;
		while (true) {
			// 적을 죽이는 코드
			for (int i = 0; i < SIZE; i++) {
				PriorityQueue<Enemy> targetedEnemies = new PriorityQueue<>();
				int archer = attack2[i];
				for (int j = 0; j < enemies.size(); j++) {
					Enemy enemy = enemies.get(j);
					enemy.d = Math.abs(archer - enemy.c) + Math.abs(N - enemy.r);

					if (enemy.d <= D) {
						targetedEnemies.offer(enemy);
					}
				}

				if (!targetedEnemies.isEmpty()) {
					targetedEnemies.poll().isTargeted = true;
				}
			}

			// 죽인 적을 카운트
			for (int i = 0; i < enemies.size(); i++) {
				Enemy enemy = enemies.get(i);
				if (enemy.isTargeted) {
					enemies.remove(i--);
					count++;
				} else if (enemy.r == N - 1) {
					enemies.remove(i--);
				} else {
					enemy.r++;
				}
			}
			if (enemies.size() == 0)
				break;
		}
		max = Math.max(max, count);
	}
}
