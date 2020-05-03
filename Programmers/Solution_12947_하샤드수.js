function solution(x) {
    var answer = true;
    var sum = 0;
    x = String(x);
    for(var i=0;i<x.length;i++){
    	sum += parseInt(x.charAt(i),10);
    }
    answer = x % sum == 0 ? true : false;
    return answer;
}

console.log(solution(10));
console.log(solution(12));
console.log(solution(11));
console.log(solution(13));