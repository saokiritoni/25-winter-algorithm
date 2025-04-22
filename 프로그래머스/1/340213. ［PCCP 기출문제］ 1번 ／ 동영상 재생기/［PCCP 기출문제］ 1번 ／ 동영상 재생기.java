import java.util.*;
class Solution {
    
    private int ConvertSec (String time) {
    StringTokenizer st = new StringTokenizer(time, ":");
    int mm = Integer.parseInt(st.nextToken());
    int ss = Integer.parseInt(st.nextToken());
    return mm * 60 + ss;
    }
    private String ConvertMin (int time) {
        int mm = time / 60;
        int ss = time % 60;
        return String.format("%02d:%02d", mm, ss);
    }
    
    private int SkipOpening (int time, int op_start, int op_end) {
        if (op_start <= time && time <= op_end) {return op_end;}
        else return time;
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {   
        
        int cvt_video_len = ConvertSec(video_len);
        int cvt_pos = ConvertSec(pos);
        int cvt_op_start = ConvertSec(op_start);
        int cvt_op_end = ConvertSec(op_end);
        
        for(int i = 0; i < commands.length; i++) {
            String currentCmd = commands[i];
            cvt_pos = SkipOpening(cvt_pos, cvt_op_start, cvt_op_end);
            if (currentCmd.equals("prev")) {
                if (cvt_pos <= 10) { cvt_pos = 0; } 
                else {cvt_pos = cvt_pos - 10;}
            } else if (currentCmd.equals("next")){
                cvt_pos = SkipOpening(cvt_pos, cvt_op_start, cvt_op_end);
                if (cvt_video_len - cvt_pos < 10) { cvt_pos =  cvt_video_len; }
                else {cvt_pos = cvt_pos + 10; }
            }
            cvt_pos = SkipOpening(cvt_pos, cvt_op_start, cvt_op_end);
        }
        String answer = ConvertMin(cvt_pos);
        return answer;
    }
}

