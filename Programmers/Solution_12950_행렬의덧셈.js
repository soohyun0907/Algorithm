function solution(arr1, arr2) {
    var answer = [];
    for(var i=0;i<arr1.length;i++){
    	answer[i] = [];
    	for(var j=0;j<arr1[i].length;j++){
    		answer[i][j] = arr1[i][j] + arr2[i][j];
    	}
    }
    return answer;
}

console.log(solution([[1,2],[2,3]],[[3,4],[5,6]]));
console.log(solution([[1],[2]],[[3],[4]]));