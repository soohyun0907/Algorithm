import java.util.Stack;

public class Solution_쇠막대기 {

	public static void main(String[] args) {
		System.out.println(solution("()(((()())(())()))(())"));
	}

	public static int solution(String arrangement) {
        int answer = 0;
        Stack<Character> stack = new Stack<Character>();
        for(int i=0; i<arrangement.length();){
            if(arrangement.charAt(i)=='(' && arrangement.charAt(i+1)==')'){
                answer += stack.size();
                i++;
            } else {
                if(arrangement.charAt(i) == ')'){
                    answer += 1;
                    stack.pop();
                } else
                    stack.push('(');
            }
            i++;
        }
        return answer;
    }
}
