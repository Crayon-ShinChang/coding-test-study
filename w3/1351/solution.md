## 해결 아이디어

### 1. **재귀함수와 메모이제이션 활용**

* `A[i] = A[i/P] + A[i/Q]`라는 점화식이 주어짐 → 재귀적으로 정의 가능
* 하지만 `N`이 크므로, 같은 값이 여러 번 계산되는 비효율 발생 가능 → **메모이제이션** 사용
* Java의 `HashMap<Long, Long>`을 활용하여 계산된 결과를 저장 (중복 계산 방지)

### 2. **기저 조건 설정**

* `A[0] = 1`은 문제에서 주어진 조건 → 재귀 탈출 조건으로 설정

---

## 재귀 함수 `solution(long num)`

```java
private static long solution(long num) {
    if (num == 0L) {
        return 1L;
    }

    if (m.containsKey(num)) {
        return m.get(num);
    }

    long first = solution(num / P);       // 내림 나눗셈
    long second = solution(num / Q);
    m.put(num, first + second);
    return m.get(num);
}
```

* `num`이 0이면 1을 반환
* 이미 계산된 값은 `HashMap`에서 바로 반환
* 아니면 `num/P`와 `num/Q`의 결과를 재귀적으로 구하고, 합을 `num`에 대한 값으로 저장 후 반환

---

## 시간 및 공간 복잡도 분석

### 시간 복잡도

* 최악의 경우에도 중복 계산 없이 `solution(num)`이 각 `num`에 대해 한 번씩만 계산됨
* 가능한 num의 수는 `N`을 `/P` 또는 `/Q`로 계속 나눈 결과만큼 → 매우 빠름
* **O(log N)** 수준의 효율적인 계산 가능

### 공간 복잡도

* 메모이제이션을 위한 HashMap 공간 사용 → O(number of distinct `num`s)

---
