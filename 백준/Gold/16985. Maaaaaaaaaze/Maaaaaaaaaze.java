import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {

    // 6방향 이동
    static int[] dx = {1, -1, 0, 0, 0, 0}; // 층
    static int[] dy = {0, 0, 1, -1, 0, 0}; // 행
    static int[] dz = {0, 0, 0, 0, 1, -1}; // 열

    // 원본 판 배열 (입력 순서대로 5개의 5×5 배열)
    static int[][][] origin = new int[5][5][5];
    // 미리 각 판에 대해 0도, 90도, 180도, 270도 회전한 배열을 저장 (판 번호, 회전상태, 행, 열)
    static int[][][][] rotatedBoards = new int[5][4][5][5];

    // 순열 & 회전상태
    static boolean[] used = new boolean[5]; // 해당 판을 순열에 사용했는지 여부
    static int[] perm = new int[5];          // 순열로 선택된 판의 인덱스 (순서대로 쌓임)
    static int[] selRot = new int[5];          // 각 층에서 선택된 회전 상태 (0,1,2,3)

    // 최단 경로 정답 저장 (= 최소 이동 횟수)
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int b = 0; b < 5; b++) {  // 판 번호
            for (int i = 0; i < 5; i++) { // 행
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 5; j++) { // 열
                    origin[b][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        // 각 판에 대해 4가지 회전 상태(0, 90, 180, 270도)를 미리 구해서 저장해둔다.
        for (int b = 0; b < 5; b++) {
            // 0도 : 원본
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    rotatedBoards[b][0][i][j] = origin[b][i][j];
                }
            }
            // 90도, 180도, 270도
            for (int rot = 1; rot < 4; rot++) {
                rotatedBoards[b][rot] = rotate90(rotatedBoards[b][rot - 1]);
            }
        }

        // 5개의 판 순서를 결정하는 순열을 구하면서 동시에 각 판의 회전 상태(0~3)를 결정한다.
        permute(0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    // 5×5 판을 회전하기
    public static int[][] rotate90(int[][] board) {
        int n = 5;
        int[][] newBoard = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[j][n - 1 - i] = board[i][j];
            }
        }
        return newBoard;
    }

    // 5개의 판을 쌓는 순서를 고려해야 한다.
    // 판의 순열을 재귀적으로 만들기 (idx: 현재 배치할 층 번호 0~4)
    public static void permute(int idx) {

        // 최적해(최소 이동 횟수 12)가 이미 나온 경우 더 이상 탐색할 필요 없음
        if (ans == 12) return;

        // idx가 5가 되면 0~4까지 총 5개의 판의 순서가 모두 결정된 상태이므로, 완성.
        if (idx == 5) {
            // 판의 순열 다음에 각 판의 회전 상태 결정
            setRotation(0); // -> 각 층마다 4가지 회전하고 BFS 탐색
            return;
        }

        // 5개의 판(0~4) 각각 확인
        for (int i = 0; i < 5; i++) {
            if (!used[i]) {
                used[i] = true; // 순열에 해당 판이 사용되었는지 확인, 사용되었다면 true
                perm[idx] = i; // 현재층(idx)에 i번 판 배치
                permute(idx + 1); // 다음 층에 대한 판 선택
                used[i] = false; // i번 판의 사용 상태 초기화 (선택 해제)
            }
        }
    }

    // 순열로 결정된 판들에 대해, 각 층마다 회전 상태(0~3)를 결정(idx: 현재 층 번호(0~4))
    public static void setRotation(int idx) {
        if (ans == 12) return;

        if (idx == 5) {
            // idx == 5 이면 모든 층에 대한 회전 상태가 결정된 것, 최종 미로를 구성하여 BFS 실행
            int[][][] cube = new int[5][5][5]; // cube[층][행][열]
            for (int level = 0; level < 5; level++) {
                int boardIndex = perm[level]; // 해당 층에 올 판 번호
                int rot = selRot[level];       // 그 판의 회전 상태
                // rotatedBoards 배열 사용하여 회전이 적용된 배열을 cube에 복사
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        cube[level][i][j] = rotatedBoards[boardIndex][rot][i][j];
                    }
                }
            }
            // 시작점(0,0,0)과 도착점(4,4,4)이 열려있지 않으면 BFS 실행하지 않음.
            if (cube[0][0][0] == 0 || cube[4][4][4] == 0) return;
            int d = bfs(cube);
            if (d != -1) {
                ans = Math.min(ans, d); // 가장 짧은 경로를 갱신
            }
            return;
        }

        // 회전 상태 결정
        for (int r = 0; r < 4; r++) { // 현재 층에 가능한 4가지 회전 상태 시도 (0,1,2,3)
            selRot[idx] = r; // 반복마다 현재 회전 상태 할당
            setRotation(idx + 1); // 다음 층에 대한 회전 상태 결정
        }
    }

    // 3차원 BFS로 (0,0,0)에서 (4,4,4)까지의 최단 경로를 찾는다.
    public static int bfs(int[][][] cube) {
        boolean[][][] visited = new boolean[5][5][5];
        int[][][] dist = new int[5][5][5];

        // 거리 배열을 -1로 초기화
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    dist[i][j][k] = -1;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0});
        visited[0][0][0] = true;
        dist[0][0][0] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0]; // 층
            int y = cur[1]; // 행
            int z = cur[2]; // 열

            // 도착점에 도달하면 현재까지의 이동 횟수를 반환
            if (x == 4 && y == 4 && z == 4) {
                return dist[x][y][z];
            }

            // 6방향 이동
            for (int d = 0; d < 6; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                int nz = z + dz[d];
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || nz < 0 || nz >= 5) continue;
                if (!visited[nx][ny][nz] && cube[nx][ny][nz] == 1) {
                    visited[nx][ny][nz] = true;
                    dist[nx][ny][nz] = dist[x][y][z] + 1;
                    q.offer(new int[]{nx, ny, nz});
                }
            }
        }
        return -1; // 도달할 수 없는 경우
    }
}
