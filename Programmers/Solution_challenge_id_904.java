import java.util.Arrays;
import java.util.Comparator;

public class Solution_challenge_id_904 {

	public static void main(String[] args) {
		String[] strings = {"sun", "bed", "car"};
		solution(strings, 1);
		
		String[] strings1 = {"abce", "abcd", "cdx"};
		solution(strings1, 2);
	}
	
	public static String[] solution(String[] strings, int n) {
        String[] answer = strings;
        Arrays.sort(answer, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if(o1.charAt(n) == o2.charAt(n))
					return o1.compareTo(o2);
				return Character.compare(o1.charAt(n), o2.charAt(n));
			}
        	
        });
        
        System.out.println(Arrays.toString(answer));
        return answer;
    }

}
