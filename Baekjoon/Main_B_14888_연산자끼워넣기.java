
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author soohyun0907
 * 
 * 1. 입력받은 숫자로 피연산자 배열로 저장한다.
 * 2. 입력받은 숫자를 기반으로 연산자 배열로 저장한다.
 * 3. 함수를 호출하여 연산자 배열의 순열을 만든다. (큐에 넣어주기? - 넣은 순서대로 꺼내서 차례로 계산?)
 * 4. 만들어진 연산자 순열과 피연산자 배열을 가지고 하나씩 계산한다.
 * 5. 최대값과 최소값을 비교한다.
 *
 */
public class Main_B_14888_연산자끼워넣기 {

	static int N, SIZE;
	static int[] num;
	static List<Character> calculate = new ArrayList<>(); // 연산자를 저장할 리스트
	static char[] calArray;
	static boolean[] selectedCal;
	// 계산한 결과를 담을 리스트
	static List<Integer> calculateResult = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder answer = new StringBuilder();
		// N입력, N개의 수로 이루어진 순열 입력  
		N = Integer.parseInt(st.nextToken()); // 수의 개수
		num = new int[N]; // 입력받을 수열를 저장할 배열
		selectedCal = new boolean[N-1];
		calArray = new char[N-1];
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		// 합이 N-1개인 연산자의 숫자 입력
		// 0:덧셈, 1:뺄셈, 2:곱셈, 3:나눗셈의 개수
		for(int i=0;i<4;i++) {
			int temp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < temp; j++) {
				if(i==0)
					calculate.add('+');
				else if(i==1)
					calculate.add('-');
				else if(i==2)
					calculate.add('*');
				else
					calculate.add('/');
			}
		}
		SIZE = calculate.size();
		operationInsert(0);
		
		Collections.sort(calculateResult);
		
		answer.append(calculateResult.get(calculateResult.size()-1))
		.append("\n").append(calculateResult.get(0));
		// 첫째줄에 최대값, 둘째줄에 최솟값 출력
		System.out.println(answer);
		
		in.close();
	}
	
	// 순열로 연산자의 배열만 바꿔서 큐에 넣어준다.
	public static void operationInsert(int index) {
		if(index == N-1) {
			int i=1, result=num[0];
			for(char c:calArray) {
				if(c=='+') {
					result += num[i++];
				} else if(c=='-') {
					result -= num[i++];
				} else if(c=='*') {
					result *= num[i++];
				} else {
					if(result < 0) {
						int temp = Math.abs(result) / num[i++];
						result = temp*(-1);
					} else
						result /= num[i++];
				}
			}
			calculateResult.add(result);
			return;
		}
		
		for(int i=0;i<N-1;i++) {	
			if(selectedCal[i])
				continue;
			
			calArray[index] = calculate.get(i);
			selectedCal[i] = true;
			operationInsert(index+1);
			selectedCal[i] = false;
		}
	}
	
}
