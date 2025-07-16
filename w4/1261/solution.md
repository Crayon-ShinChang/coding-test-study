## 문제 설명 요약

- **지도**에 0과 1로 표현된 격자 형태의 방이 있음.
    - `0`: 빈 방 (벽이 없음)
    - `1`: 벽이 있는 방
- `(0, 0)` → `(n-1, m-1)`까지 이동
- **벽을 적게 부수고** 도착해야 함
- 상하좌우로 이동 가능
- 벽을 부수는 데 1의 비용이 든다고 가정
- 목표: 벽을 **최소한으로 부수어 목적지까지 가는 것**

## 사용 알고리즘: **0-1 BFS**

- BFS와 비슷하지만, **이동 비용에 따라 가중치를 다르게 적용**
- 다음 이동이 **비용 0**이면 `Deque의 앞` (`addFirst`) 에 넣고,
- 비용이 **1이 드는 경우**(벽을 부수는 경우)는 `뒤에 넣기` (`addLast`)

## 로직 설명

### BFS 반복 처리

```java
while (!dq.isEmpty()) {
    int[] cur = dq.pollFirst(); // 현재 위치
    int x = cur[0], y = cur[1];

    for (int k = 0; k < 4; k++) {
        int nx = x + dx[k];
        int ny = y + dy[k];

        if (isBound(nx, ny) && dist[nx][ny] == -1) {
            if (board[nx][ny] == 1) {
                dist[nx][ny] = dist[x][y] + 1;
                dq.addLast(new int[]{nx, ny}); // 벽이므로 뒤에 추가
            } else {
                dist[nx][ny] = dist[x][y];
                dq.addFirst(new int[]{nx, ny}); // 빈칸이므로 앞에 추가
            }
        }
    }
}
```

- 벽 (`1`)이면 **비용이 증가**하므로 `addLast` (뒤에 삽입)
- 빈방 (`0`)이면 **비용이 그대로**이므로 `addFirst` (앞에 삽입)

## 시간 복잡도

- `O(N × M)`
    - BFS이므로 각 노드를 최대 한 번만 큐에 넣음