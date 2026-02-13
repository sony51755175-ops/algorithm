import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2630_2 {
    static int n;
    static int board[][];
    static int white = 0;
    static int blue = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve(0, 0, n);
        System.out.println(white);
        System.out.println(blue);

    }
    static void solve(int x, int y, int size){
        int color = board[x][y]; // 시작점의 색깔 저장
        boolean flag = true;

        for(int i = x; i < x + size; i++){ // 사이즈만큼 구간 설정
            for(int j = y; j < y + size; j++){
                if(board[i][j] != color){ // 시작점과 다른 경우
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            if(color == 0){
                white++;
            }
            else{
                blue++;
            }
            return;
        }

        size = size / 2; // 사이즈 절반으로 줄이고 분할탐색
        solve(x, y, size);
        solve(x, y + size, size);
        solve(x + size, y, size);
        solve(x + size, y + size, size);


    }
}
