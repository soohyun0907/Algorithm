function solution(x, n) {
	var answer = [];
	for (var i = 0; i < n; i++) {
		answer[i] = x + x * i;
	}
	return answer;
}

console.log(solution(2, 5));
console.log(solution(4, 3));
console.log(solution(-4, 2));