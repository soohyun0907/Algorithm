package SWExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author soohyun0907
 * 게임맵 정보
 * . 평지(전차가 들어갈 수 있음)
 * * 벽돌로 만들어진 벽
 * # 강철로 만들어진 벽
 * - 물(전차는 들어갈 수 없음)
 * ^ 위쪽을 바라보는 전차
 * v 아래쪽을 바라보는 전차
 * < 왼쪽을 바라보는 전차
 * > 오른쪽을 바라보는 전차
 * 
 * 사용자가 넣을 수 있는 입력의 종류
 * U : 전차가 바라보는 방향을 위쪽으로 바꾸고 한칸 위의 칸이 평지라면 그 칸으로 이동
 * D : 전차가 바로브는 방향을 아래쪽으로 바꾸고 한 칸 아래의 칸이 평지라면 그 칸으로 이동
 * L : 전차가 바라보는 방향을 왼쪽으로..
 * R : 전차가 바라보는 방향을 오른쪽으로..
 * S : 전차가 현재 바라보고 있는 방향으로 포탄 발사 - 벽돌이나 강철로 만들어진 벽에 충돌 또는 게임 맵 밖으로 나갈때까지 직진
 * 	   벽에 부딪히면 포탄은 소멸, 벽돌로 만들어진 벽에 부딪히면 벽은 파괴되고 칸은 평지가 됨, 강철로 만들어진 벽과 게임 맵 밖으로 나가면 아무일도 X
 * 
 * 처음에는 if문과 while문을 사용하여 하나하나 제어를 하려고 했더니 더 많은 예외처리가 필요했다.
 * 오히려 메소드를 만들어서 호출하니까 코드 보기에도 더 편하고 중복되는 예외처리는 한번에 처리할 수 있었다.
 */
public class Solution_D3_1873_상호의배틀필드 {

	static int T, H, W, Ci, Cj, N, currentX, currentY, beforeX, beforeY;
	static char[][] gameMap;
	static String player;
	static int[] deltaX = { -1, 1, 0, 0 }; // 상,하,좌,우
	static int[] deltaY = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		// 테스트 케이스의 수 T
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder answer = new StringBuilder();
		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			answer.append("#").append(tc).append(" ");
			// 게임 맵의 높이와 너비가 H, W로 주어짐
			st = new StringTokenizer(in.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			// 게임 맵은 H*W크기의 격자판
			gameMap = new char[H][W];

			// H개의 줄로 길이가 W인 문자열이 주어짐
			for (int i = 0; i < H; i++) {
				String map = in.readLine();
				for (int j = 0; j < W; j++) {
					gameMap[i][j] = map.charAt(j);
					if (gameMap[i][j] == '<' || gameMap[i][j] == '>' || gameMap[i][j] == '^' || gameMap[i][j] == 'v') {
						Ci = i;
						Cj = j;
					}
				}
			}

			// 사용자가 넣을 입력의 개수 N
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			// 길이가 N인 문자열
			player = in.readLine();
			// 구현
			currentX = Ci;
			currentY = Cj;
			for (int i = 0; i < N; i++) {
				if (player.charAt(i) == 'U') {
					gameMap[currentX][currentY] = '^';
					move(currentX, currentY, 0);
				} else if (player.charAt(i) == 'D') {
					gameMap[currentX][currentY] = 'v';
					move(currentX, currentY, 1);
				} else if (player.charAt(i) == 'L') {
					gameMap[currentX][currentY] = '<';
					move(currentX, currentY, 2);
				} else if (player.charAt(i) == 'R') {
					gameMap[currentX][currentY] = '>';
					move(currentX, currentY, 3);
				} else { // S인 경우
					shoot(currentX, currentY);
				}
			}

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					answer.append(gameMap[i][j]);
				}
				answer.append("\n");
			}

		}

		System.out.println(answer);
		in.close();
	}

	// 상(0),하(1),좌(2),우(3)
	private static void shoot(int shootX, int shootY) {
		int dir = 0;
		switch (gameMap[shootX][shootY]) {
		case '^':
			dir = 0;
			break;
		case 'v':
			dir = 1;
			break;
		case '<':
			dir = 2;
			break;
		case '>':
			dir = 3;
			break;
		}

		while (true) {
			shootX += deltaX[dir];
			shootY += deltaY[dir];
			if (shootX < 0 || shootX >= H || shootY < 0 || shootY >= W) {
				return;
			}
			if (gameMap[shootX][shootY] == '*') {
				gameMap[shootX][shootY] = '.';
				return;
			}
			if (gameMap[shootX][shootY] == '#') {
				return;
			}
		}
	}

	private static void move(int x, int y, int dir) {
		char c = 0;
		switch (dir) {
		case 0:
			c = '^';
			break;
		case 1:
			c = 'v';
			break;
		case 2:
			c = '<';
			break;
		case 3:
			c = '>';
			break;
		}

		int moveX = x + deltaX[dir];
		int moveY = y + deltaY[dir];

		if (moveX < 0 || moveX >= H || moveY < 0 || moveY >= W) {
			return;
		}
		if (gameMap[moveX][moveY] == '-' || gameMap[moveX][moveY] == '*' || gameMap[moveX][moveY] == '#') {
			return;
		}

		if (gameMap[moveX][moveY] == '.') {
			gameMap[x][y] = '.';
			gameMap[moveX][moveY] = c;
			currentX = moveX;
			currentY = moveY;
		}
	}
}