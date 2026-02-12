<<<<<<< HEAD
=======


//재귀 쓸 예정

>>>>>>> c9f24fc95f91adaa25118f0790d6e679ed03e037
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JaeMin {
    static int N, total, paper[][];
    static int blues, whites;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, N);

        System.out.println(sb.append(whites).append("\n").append(blues));

    }

    public static void solve(int r, int c, int size){
        if(!isDivide(r, c, size)){
            if(paper[r][c] == 0) whites++;
            else blues++;
            return;
        }
            
        int nSize = size>>1;
        solve(r, c, nSize);
        solve(r + nSize, c, nSize);
        solve(r, c+ nSize, nSize);
        solve(r + nSize, c + nSize, nSize);
    }
    
    private static boolean isDivide(int r, int c, int size){
        int before = paper[r][c];

        for (int i = r; i < r+ size ; i++) {
            for (int j = c; j < c + size; j++) {
                if(before != paper[i][j]) return true;
            }
        }
        return false;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> c9f24fc95f91adaa25118f0790d6e679ed03e037
