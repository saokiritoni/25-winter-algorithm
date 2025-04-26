import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        int[] answer = new int[commands.length];
        
        for (int i = 0; i < commands.length; i++) {
            int from = commands[i][0];
            int to = commands[i][1];
            int num = commands[i][2];
            int[] cutArr = new int[to - from + 1];
            
            for (int j = 0; j < cutArr.length; j++) {
                cutArr[j] = array[from - 1 + j];
            }
            
            Arrays.sort(cutArr);
            answer[i] = cutArr[num - 1];
        }
        
        return answer;
    }
}
