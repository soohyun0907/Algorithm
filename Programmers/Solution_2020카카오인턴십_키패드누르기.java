import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author soohyun
 * 해당 자리에 손가락이 그대로 있는 경우를 추가 해줘서 통과!!!!
 */

public class Solution_2020카카오인턴십_키패드누르기 {

	public static void main(String[] args) {
		int[] numbers = { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 };
		System.out.println(solution(numbers, "right"));
		System.out.println(solution(numbers, "left"));
		int[] numbers1 = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
		System.out.println(solution(numbers1, "left"));
		int[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		System.out.println(solution(numbers2, "right"));
		int[] numbers3 = {0,8,9,6,5,7,2,1};
		System.out.println(solution(numbers3, "left"));
		System.out.println(solution(numbers3, "right"));
	}

	static char[][] phone = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' }, { '*', '0', '#' } };
	static boolean[][] visited = new boolean[4][3];
	static int[][] delta = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상 좌 하 우

	public static String solution(int[] numbers, String hand) {
		String answer = "";
		Point lh = new Point(3, 0);
		Point rh = new Point(3, 2);
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] % 3 == 1) {
				answer += "L";
				lh = moveHand(numbers[i]);
			} else if (numbers[i] != 0 && numbers[i] % 3 == 0) {
				answer += "R";
				rh = moveHand(numbers[i]);
			} else {
				int lDist = bfs(lh, numbers[i]);
				int rDist = bfs(rh, numbers[i]);
				if(lDist==rDist) {
					if(hand.equals("right")) {
						answer += "R";
						rh = moveHand(numbers[i]);
					} else {
						answer += "L";
						lh = moveHand(numbers[i]);
					}
				} else if(lDist < rDist) {
					answer += "L";
					lh = moveHand(numbers[i]);
				} else if(lDist > rDist) {
					answer += "R";
					rh = moveHand(numbers[i]);
				}
			}
		}
		return answer;
	}

	private static int bfs(Point hand, int num) {
		if(phone[hand.x][hand.y]-'0' == num)
			return 0;
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(hand);
		int count = 0;
		for(boolean[] row : visited) {
			Arrays.fill(row, false);
		}
		
		visited[hand.x][hand.y] = true;
		
		while(!queue.isEmpty()) {
			int size =queue.size();
			
			count++;
			
			while(--size >= 0) {
				Point cur = queue.poll();
				
				for (int n = 0; n < delta.length; n++) {
					int curX = cur.x + delta[n][0];
					int curY = cur.y + delta[n][1];
					
					if(curX < 0 || curX >= 4 || curY < 0 || curY >= 3 || visited[curX][curY]) continue;
					
					if(phone[curX][curY]-'0' == num) {
						return count;
					}
					
					queue.offer(new Point(curX,curY));
					visited[curX][curY] = true;
				}
			}
			
		}
		return count;
	}

	public static Point moveHand(int n) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (phone[i][j] - '0' == n) {
					return new Point(i, j);
				}
			}
		}
		return null;
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
