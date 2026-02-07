import java.io.*;
import java.util.*;

public class Main {
	
	public static class LazySegmentTree {
		private int[] tree;
		private int[] lazy;
		private int n;
		
		public LazySegmentTree(int n) {
			this.n = n;
			tree = new int[n * 4];
			lazy = new int[n * 4];
		}
		
		private void propagate(int node, int start, int end) {
			if(lazy[node] != 0) {
				tree[node] = lazy[node];
				if(start != end) {
					lazy[node * 2] = lazy[node];
					lazy[node * 2 + 1] = lazy[node];
				}
				lazy[node] = 0;
			}
		}
		
		public int query(int left, int right) {
			return query(1, 0, n - 1, left, right);
		}
		
		private int query(int node, int start, int end, int left, int right) {
			propagate(node, start, end);
			if(right < start || end < left) {
				return Integer.MAX_VALUE;
			}
			if(left <= start && end <= right) {
				return tree[node];
			}
			int mid = (start + end) / 2;
			int leftQuery = query(node * 2, start, mid, left, right);
			int rightQuery = query(node * 2 + 1, mid + 1, end, left, right);
			return Math.min(leftQuery, rightQuery);
		}
		
		public int update(int left, int right, int value) {
			return update(1, 0, n - 1, left, right, value);
		}
		
		private int update(int node, int start, int end, int left, int right, int value) {
			propagate(node, start, end);
			if(right < start || end < left) {
				return tree[node];
			}
			if(left <= start && end <= right) {
				lazy[node] = value;
				propagate(node, start, end);
				return tree[node];
			}
			int mid = (start + end) / 2;
			int leftUpdate = update(node * 2, start, mid, left, right, value);
			int rightUpdate = update(node * 2 + 1, mid + 1, end, left, right, value);
			return tree[node] = Math.min(leftUpdate, rightUpdate);
		}
	}
	
	public static int lowerBound(List<Integer> list, int key) {
		int left = 0;
		int right = list.size();
		while(left < right) {
			int mid = (left + right) / 2;
			if(list.get(mid) >= key) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[][] query = new int[n][2];
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			query[i][0] = x - r;
			query[i][1] = x + r;
			list.add(query[i][0]);
			list.add(query[i][1]);
		}
		Arrays.sort(query, (q1, q2) -> (q2[1] - q2[0]) - (q1[1] - q1[0]));
		Collections.sort(list);
		List<Integer> dist = new ArrayList<>();
		for(int v : list) {
			if(dist.isEmpty() || dist.get(dist.size() - 1) != v) {
				dist.add(v);
			}
		}
		LazySegmentTree lst = new LazySegmentTree(dist.size());
		for(int i = 0; i < n; i++) {
			int start = lowerBound(dist, query[i][0]);
			int end = lowerBound(dist, query[i][1]);
			lst.update(start, end - 1, i);
		}
		int count = 0;
		for(int i = 0; i < n; i++) {
			int start = lowerBound(dist, query[i][0]);
			int end = lowerBound(dist, query[i][1]);
			int min = lst.query(start, end - 1);
			if(min > i) {
				count += 2;
			} else {
				count += 1;
			}
		}
		System.out.println(count + 1);
	}
}