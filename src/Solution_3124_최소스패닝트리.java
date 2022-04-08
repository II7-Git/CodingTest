import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_3124_최소스패닝트리 {

	static int T,V,E;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		T=Integer.parseInt(br.readLine());
		
		for (int t = 1; t <=T; t++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			
			V=Integer.parseInt(st.nextToken());
			E=Integer.parseInt(st.nextToken());
			
			PriorityQueue<Line> pq=new PriorityQueue<>();
			for (int i = 0; i < E; i++) {
				st=new StringTokenizer(br.readLine()," ");
				int a=Integer.parseInt(st.nextToken())-1;
				int b=Integer.parseInt(st.nextToken())-1;
				int c=Integer.parseInt(st.nextToken());
				
				pq.offer(new Line(a,b,c));
			}

			set();
			
			int groupCnt=V;
			long min=0;
			while(groupCnt!=1) {
				Line now=pq.poll();
				
				if(union(now.a, now.b)) {
					min+=(long) now.c;
					groupCnt--;
				}
			}
			
			System.out.println("#"+t+" "+min);
		}
	}

	
	
	private static void set() {
		parent=new int[V];
		for (int i = 0; i < V; i++) {
			parent[i]=i;
		}
	}
	
	private static int find(int a) {
		if(a==parent[a]) return a;
		return parent[a]=find(parent[a]);
	}

	private static boolean union(int a,int b) {
		int aR=find(a);
		int bR=find(b);
		
		if(aR==bR) return false;
		
		parent[bR]=aR;
		return true;
	}


	public static class Line implements Comparable<Line>{
		int a;
		int b;
		int c;
		
		public Line(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public int compareTo(Line o) {
			// TODO Auto-generated method stub
			return this.c-o.c;
		}
		
		
	}
}
