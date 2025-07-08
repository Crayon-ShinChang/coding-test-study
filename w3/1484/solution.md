## 해결 아이디어

### **투 포인터 방식**

* 변수 `pre`: 과거 몸무게 (`x`)
* 변수 `cur`: 현재 몸무게 (`y`)
* 초기값: `pre = 1`, `cur = 2`
* 조건을 만족하는 `cur`을 구해나가는 방식
* 반복적으로 `cur^2 - pre^2`을 계산하여 다음을 판단
    * `== G` → 답으로 저장하고 `cur` 증가
    * `< G` → `cur` 증가 (더 큰 차이를 만들어야 함)
    * `> G` → `pre` 증가 (차이를 줄여야 함)
* 단, `cur - pre == 1`일 때 `diff > G`라면 더 이상 유효한 해가 없음 → 종료

---

## 반복문 로직

```java
while (true) {
    long diff = cur*cur - pre*pre;

    if (diff == G) {
        answer.add(cur);
        cur++;
    }
    else if (diff > G) {
        if (cur - pre == 1L) {
            break;
        }
        pre++;
    }
    else if (diff < G) {
        cur++;
    }
}
```

---

## 시간 및 공간 복잡도 분석

### 시간 복잡도
* `cur`과 `pre`는 모두 1씩만 증가하므로 전체 시간 복잡도는 **O(√G)** 수준

### 공간 복잡도
* 가능한 해의 개수만큼만 `ArrayList`에 저장 → **O(k)** (k는 해의 수)

---
