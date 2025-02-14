import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashMap;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int carNum = Integer.parseInt(br.readLine());
        LinkedHashMap <String, Integer> inCar = new LinkedHashMap<>();

        for(int i = 1; i <= carNum; i++) {
            inCar.put(br.readLine(), i);
        }

        int count = 0;
        for(int i = 1; i <= carNum; i++){
            String outCar = br.readLine();

            for (String s : inCar.keySet()) {
                if (inCar.get(outCar) > inCar.get(s)) {
                    count++;
                    break;
                }
            }
            inCar.remove(outCar);
        }
        System.out.println(count);
    }

}
