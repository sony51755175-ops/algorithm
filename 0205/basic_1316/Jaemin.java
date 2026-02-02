
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Jaemin {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        int cnt = 0;
        for(int i = 1 ; i <=T; i++){
            if(isGroup(br.readLine())) cnt++;            
        }
        bw.write(cnt + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean isGroup(String str){
        Set<Character> set = new HashSet<>();
        char pre = ' ';

        for(int i =0; i < str.length(); i++){
            char current = str.charAt(i);

            if(pre != current){

                if(set.contains(current)){
                    return false;
                }

                set.add(current);
            }
            pre = current;
        }
        return true;
    }
}