import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        int left = 0, right = n - 1;
        int leftIdx = 0, rightIdx = 0;
        long min = Long.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        while (left < right) {
            long sum = arr[left] + arr[right];
            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                leftIdx = left;
                rightIdx = right;
            }
            if (sum >= 0) {right--;}
            else {left++;}
        }

        System.out.println(arr[leftIdx]+" "+arr[rightIdx]);

    }
}
