# 백준 1034: 램프
### 1. 문제 이해
- 주어진 수열이 등차수열인지 판별하는 문제이다.
- a, b는 정수여야 한다.
- 조건이 모호하여 해가 여러개 존재한다면 "A"를 출력하고
- 불가능할 경우 "B"를 출력한다

### 2. 문제 접근 방식 (브루트포스)
- 수열의 길이에 따라 다른 방식으로 접근한다
- N = 1, 어떤 수는 올 수 있으므로 "A"
- N = 2, 두 수가 같으면 그 수를 출력하고, 아니라면 "A"
- N >= 3
    - a, b에 대한 연립방정식을 세워 풀이한다.
    - 분모가 0일 경우를 대비해 예외 처리 해야하며
    - a, b 값을 구해 검증을 진행한다.
    - 검증 실패 시, "B"를 출력한다.


### 3. 핵심 구현 로직
- 아래는 연립 방정식 풀이 코드이다.
``` java
        int a, b;

        int denominator = arr[1] - arr[0]; // 분모
        int numerator = arr[2] - arr[1]; // 분자

        if (denominator == 0) { // 분모가 0일 경우
            System.out.println("B");
            return;
        }

        if (numerator % denominator != 0) { // 정수가 나오지 않을 경우
            System.out.println("B");
            return;
        }

        // a와 b 계산
        a = numerator / denominator;
        b = arr[1] - a * arr[0];

        // 검증, 모든 수에 대해 해당 수열이 적용되는지 확인
        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[i - 1] * a + b) {
                System.out.println("B");
                return;
            }
        }
```


