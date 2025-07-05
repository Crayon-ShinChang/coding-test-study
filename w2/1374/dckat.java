import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 강의 저장 클래스
class Lecture implements Comparable<Lecture> {
    int no;
    int start;
    int end;

    Lecture(int no, int start, int end) {
        this.no = no;
        this.start = start;
        this.end = end;
    }

    // 정렬을 위한 비교 함수
    @Override
    public int compareTo(Lecture o) {
        // 기본적으로 시작시간을 기준으로 오름차순 정렬
        // 시작 시간이 같으면 끝시간 기준 오름차순 정렬
        if (this.start == o.start)
            return this.end - o.end;
        return this.start - o.start;
    }
}

public class Main {

    static int n;
    static ArrayList<Lecture> lectures = new ArrayList<>();

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        int no, start, end;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            no = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            lectures.add(new Lecture(no, start, end));
        }
    }

    private static int solution() {
        Collections.sort(lectures);

        // 강의별로 끝나는 시간을 저장하기 위한 우선순위 큐 (minHeap)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < lectures.size(); i++) {
            // 모든 강의실이 비어있는 경우
            if (pq.isEmpty()) {
                pq.add(lectures.get(i).end);
            }
            else {
                int e = pq.peek();               // 끝시간이 가장 빠른 강의
                int s = lectures.get(i).start;   // 들어온 강의 시작시간

                // 가장 빨리 끝나는 강의의 시간과 현재 강의의 시작시간 비교
                // 빨리 끝나는 강의가 있다면 우선순위 큐에서 제거
                if (e <= s) {
                    pq.poll();
                }
                pq.add(lectures.get(i).end);
            }
        }

        // 최종적으로 pq에 남은 강의의 갯수가 필요한 강의실의 갯수
        return pq.size();
    }

    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}