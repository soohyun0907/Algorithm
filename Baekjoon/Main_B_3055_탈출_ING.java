import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int i;
	int j;
	
	public Point(int i,int j) {
		this.i = i;
		this.j = j;
	}
}
public class Main_B_3055_탈출_ING {

	static Point sPos;
	static int R,C, time=0;
	static char[][] tForest;
	static boolean[][] visited;
	static Queue<Point> water = new LinkedList<Point>();
	static int[] deltaX = {-1,1,0,0};
	static int[] deltaY = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		tForest = new char[R][C];
		visited = new boolean[R][C];
		for(int i=0;i<R;i++) {
			String tmp = in.readLine();
			for(int j=0;j<C;j++) {
				tForest[i][j] = tmp.charAt(j);
				if(tForest[i][j] == 'S') {
					sPos = new Point(i,j);
				}
				if(tForest[i][j] == '*') {
					water.add(new Point(i,j));
				}
			}
		}
		
		while(true) {
			int count = 0;
			int r = sPos.i;
			int c = sPos.j;
			
			if(tForest[r][c]=='D')
				break;
			
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					if(tForest[i][j]=='.')
						count++;
				}
			}
			if(count == 0) {
				System.out.println("KAKTUS");
				return;
			}
			int size=water.size();
			for(int i=0;i<size;i++) {
				simulW(water.peek().i,water.poll().j);
			}
			sPos = simulS(r,c);
			
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					System.out.print(tForest[i][j]);
				}
				System.out.println();
			}
			System.out.println();
			time++;
		}
		System.out.println(time);
		in.close();
	}
	private static Point simulS(int i, int j) {
		for(int n=0;n<deltaX.length;n++) {
			int currentX = i+deltaX[n];
			int currentY = j+deltaY[n];
			
			if(currentX<0||currentX>=R||currentY<0||currentY>=C)
				continue;
			
			if(tForest[currentX][currentY]=='D') {
				return new Point(currentX, currentY);
			}
			if(tForest[currentX][currentY] == '.'&& !visited[currentX][currentY]) {
				visited[i][j] = true;
				tForest[i][j] = '.';
				tForest[currentX][currentY] = 'S';
				return new Point(currentX, currentY);
			}
		}
		return new Point(i,j);
	}
	private static void simulW(int r, int c) {
		for(int n=0;n<deltaX.length;n++) {
			int currentWR = r+deltaX[n];
			int currentWC = c+deltaY[n];
			
			if(currentWR<0||currentWR>=R||currentWC<0||currentWC>=C)
				continue;
			
			if(tForest[currentWR][currentWC]=='.') {
				water.add(new Point(currentWR,currentWC));
				tForest[currentWR][currentWC] = '*';
			}
		}
	}
	
}
