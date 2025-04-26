import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int maxWidth = 0;
        int maxHeight = 0;

        for (int i = 0; i < sizes.length; i++) {
            Arrays.sort(sizes[i]);
            maxWidth = Math.max(maxWidth, sizes[i][1]); 
            maxHeight = Math.max(maxHeight, sizes[i][0]); 
        }

        int answer = maxWidth * maxHeight;
        return answer;
    }
}
