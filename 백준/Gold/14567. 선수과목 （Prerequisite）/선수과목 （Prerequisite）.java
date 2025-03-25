import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int v, e;
    public static int[] indegree;
    public static int[] semester; // 각 과목의 최소 수강 학기
    public static List<List<Integer>> graph;

    public static void topologySort() {
        Queue<Integer> q = new LinkedList<>();

        // 진입 차수가 0인 과목은 1학기에 수강 가능
        for (int i = 1; i <= v; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                semester[i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph.get(now)) {
                indegree[next]--;
                // 선수 과목(now)이 끝난 후에 들을 수 있으므로, 학기 수를 갱신
                semester[next] = Math.max(semester[next], semester[now] + 1);
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            System.out.print(semester[i] + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        indegree = new int[v + 1];
        semester = new int[v + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            indegree[b]++;
        }

        topologySort();
    }
}
