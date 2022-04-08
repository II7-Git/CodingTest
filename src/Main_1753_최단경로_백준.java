import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//s->i로의 최단 길이를 구하는 다익스트라
//인접리스트로 해야 메모리 부족 방지
public class Main_1753_최단경로_백준 {

	static class Edge{
		int v;//도착 지점
		int w;//웨이트
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int V=Integer.parseInt(st.nextToken());
		int E=Integer.parseInt(st.nextToken());
		int S=Integer.parseInt(br.readLine())-1; //0부터 하기위함
		List<Edge>[] adj=new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adj[i]=new ArrayList<>();
		}
		//읽기 
		for (int i = 0; i < E; i++) {
			st=new StringTokenizer(br.readLine());
			int v=Integer.parseInt(st.nextToken())-1;//시작 0~
			int u=Integer.parseInt(st.nextToken())-1;//도착
			int w=Integer.parseInt(st.nextToken());//weight
			adj[v].add(new Edge(u, w));//단방향이니 한번만
		}
		int [] d=new int[V];
		Arrays.fill(d, Integer.MAX_VALUE/1000);
		boolean c[]=new boolean[V];
		d[S]=0;
		for (int i = 0; i < V-1; i++) {
			int min=Integer.MAX_VALUE/1000;
			int w=-1;
			for (int j = 0; j < V; j++) {
				if(!c[j]&&d[j]<min) {
					min=d[j];
					w=j;
				}
			}
			// A B 연결안됨
			if(w==-1) break;//여기가 없으면 prim
			c[w]=true;
			for (Edge next:adj[w]) {
				int v=next.v;
				if(!c[v] && (d[v]>d[w]+next.w)) {//map[w][v]
					d[v]=d[w]+next.w;
				}
			}
		}
		for (int i = 0; i < V; i++) {
			if(d[i]==Integer.MAX_VALUE/1000) {
				System.out.println("INF");
			}else {
				System.out.println(d[i]);
			}
		}
	}
}
