import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Point {
        int x, y;
        boolean destroyed;
        int cnt;

        public Point(int x, int y, boolean destroyed, int cnt) {
            this.x = x;
            this.y = y;
            this.destroyed = destroyed;
            this.cnt = cnt;
        }
    }

    public static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, false, 1));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();

            if (point.x == n - 1 && point.y == m - 1) {
                return point.cnt;
            }

            for (int d = 0; d < 4; d++) {
                int newX = point.x + dx[d];
                int newY = point.y + dy[d];

                if (newX < 0 || newX >= n || newY < 0 || newY >= m) {
                    continue;
                }

                if (point.destroyed) {
                    // 벽을 부순적이 있을 때 해당 지점이 벽이 아니고, 방문한적이 없다면 큐에 정보를 넣습니다.
                    if (map[newX][newY] == 0 && !visited[newX][newY][1]) {
                        visited[newX][newY][1] = true;
                        q.offer(new Point(newX, newY, true, point.cnt + 1));
                    }
                } else {
                    if (map[newX][newY] == 1) {
                        visited[newX][newY][1] = true;
                        q.offer(new Point(newX, newY, true, point.cnt + 1));
                    } else if (!visited[newX][newY][0]) {
                        visited[newX][newY][0] = true;
                        q.offer(new Point(newX, newY, false, point.cnt + 1));
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = Character.getNumericValue(charArray[j]);
            }
        }
        System.out.println(bfs());
    }
}