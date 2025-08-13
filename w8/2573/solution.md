# 백준 2573: 빙산
### 1. 문제 이해
- 격자 그래프가 주어진다, 만약 격자의 값이 0이면 바다이고 다른 수라면 그것은 빙산의 높이이다.
- 바다와 인접한 빙산은 조금씩 녹아 한 시간마다 높이가 1씩 낮아진다.
- 빙산이 녹다보면, 처음엔 하나의 빙산이었던 것이 두 개로 나누어질 수 있는데 두 개로 나누어지기 까지의 시간을 구하는 문제이다.
- 만약 다 녹을때까지 빙산이 분리되지 않는다면 0을 출력한다.

### 2. 문제 접근 방식 (브루트포스 + BFS)
- 문제에서는 두 가지 동작을 구현해야 한다.
    - 빙산 녹이기 (브루트포스)
    - 빙산의 개수 세기 (BFS)
- 빙산 녹이기
    - 빙산은 한시간마다 "동시에" 녹는다, 즉 전체 격자를 탐색하여 어느 지점이 녹을 지점인지 먼저 판별한 후 동시에 빙산을 녹여야한다.
    - 만약, 반복문으로 순회하며 그냥 녹인다면 원래는 녹을 예정이 아니었던 빙산도 녹을 수 있으니 주의하라
- 빙산의 개수 세기
    - 빙산(0이 아닌 수)에서 너비 우선 탐색을 시작하여, 방문처리를 해가며 더 이상 진행할 수 없을 때까지 탐색한다.
    - 탐색이 끝난다면 해당 빙산 하나가 카운트된 것
    - 전체 격자에서 해당 행위를 반복하여 빙산이 몇 개 있는지 셀 수 있다.


### 3. 핵심 구현 로직
#### 빙산 녹이기 함수
반드시 녹일 지점을 판별하는 시점과 녹이는 시점을 분리해야 한다.
``` java
public static void meltdown(int[][] grid) {
    int[][] meltCount = new int[N][M];

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            int cnt = 0;
            for (int d = 0; d < 4; d++) {
                int nx = i + dx[d];
                int ny = j + dy[d];

                if (checkBound(nx, ny)) {
                    if (grid[nx][ny] == 0) cnt++;
                }
            }
            meltCount[i][j] = cnt;
        }
    }

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            grid[i][j] -= (meltCount[i][j] > grid[i][j]) ? grid[i][j] : meltCount[i][j];
        }
    }
}
```

#### 빙산 카운트 함수
너비 우선 탐색으로 진행하였으나, 깊이 우선 탐색으로도 무방하다.
``` java
public static int cntIsland(int[][] grid) {
    boolean[][] visited = new boolean[N][M];
    int cnt = 0;

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (visited[i][j] || grid[i][j] == 0) continue;
            
            cnt++;
            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.add(new int[]{i, j});
            visited[i][j] = true;

            while(!q.isEmpty()) {
                int[] node = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = node[0] + dx[d];
                    int ny = node[1] + dy[d];

                    if (!checkBound(nx, ny)) continue;
                    if (visited[nx][ny]) continue;
                    if (grid[nx][ny] == 0) continue;

                    q.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                }
            }

        }
    }

    return cnt;
}
```

