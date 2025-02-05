import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int w, h;
    static int[][] map;
    static boolean[][] visited;
    // 상하좌우 + 대각선
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < 8; i++) {
                int cx = current[0] + dx[i];
                int cy = current[1] + dy[i];

                if (cx >= 0 && cy >= 0 && cx < h && cy < w && !visited[cx][cy] && map[cx][cy] == 1) {
                    q.add(new int[]{cx, cy});
                    visited[cx][cy] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken()); // 너비
            h = Integer.parseInt(st.nextToken()); // 높이

            if (w == 0 && h == 0) break;

            map = new int[h][w]; // 높이가 행, 너비가 열
            visited = new boolean[h][w]; 

            for (int i = 0; i < h; i++) { 
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) { 
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        bfs(i, j); 
                        count++; // 섬의 개수 세기
                    }
                }
            }
            System.out.println(count);
        }
        br.close();
    }
}