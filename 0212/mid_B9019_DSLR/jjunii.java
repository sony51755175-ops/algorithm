package boj;

import java.io.*;
import java.util.*;

public class Boj9019 {

	static int A, B;
	static Deque<Integer> queue = new ArrayDeque<>();
	static String[] commands = new String[10000];
	static char[] command = new char[] {'D', 'S', 'L', 'R'};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			queue.clear();
			Arrays.fill(commands, null);
			sb.append(search()).append('\n');
		}
		System.out.println(sb);
	}

	static String search() {
		queue.offer(A);
		commands[A] = "";
		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int c = 0; c < 4; c++) {
				int next = excute(cur, c);

				// 이미 방문한 숫자
				if (commands[next] != null) {
					continue;
				}

				commands[next] = commands[cur] + command[c];
				
				// 종료 조건
				if (next == B) {
					return commands[next];
				}
				
				queue.offer(next);
			}
		}
		return null;
	}

	static int excute(int cur, int c) {
		switch (c) {
		case 0: // D
			return (cur * 2) % 10000;
		case 1: // S
			return (cur + 10000 - 1) % 10000;
		case 2: // L
			return (cur % 1000) * 10 + cur / 1000;
		case 3: // R
			return (cur % 10) * 1000 + cur / 10;
		}
		return 0;
	}
}
