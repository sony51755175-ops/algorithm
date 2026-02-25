package CodingTest;
class pro1 {
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = 200000000;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2; // mid명 가능한지 확인

            if (check_stone(stones, k, mid)) {
                answer = mid;      // 현재 mid명만큼 건너기 가능
                left = mid + 1;    // 더 많이 가능한지 확인
            } else {
                right = mid - 1;   // mid명 보다 더 줄여야함
            }
        }

        return answer;
    }

    boolean check_stone(int[] stones, int k, int people) {
        int cnt = 0;

        for (int stone : stones) {
            if (stone < people) { // 돌을 못건너는지 check
                cnt++; // 못건너면 cnt ++
                if (cnt == k) { // k개만큼 연속해서 못건너면 false로 return
                    return false;
                }
            } else { // 연속이 깨지면 cnt는 0으로 초기화
                cnt = 0;
            }
        }

        return true; 
    }
}