import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_1249_보급로 {//다익스트라

	static int N,T;
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	static int [][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int t = 1; t <=T; t++) {
			int min=0;
			
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			
			for (int i = 0; i <N; i++) {
				char [] temp=br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j]=temp[j]-'0';
				}
			}
			
			//다익스트라 사용할 것
			int MAX=Integer.MAX_VALUE/10000;
			int[][] dijk=new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dijk[i], MAX);
			}
			boolean[][] v=new boolean[N][N];
			
			PriorityQueue<int[]> pq=new PriorityQueue<>((x,y)->Integer.compare(x[2], y[2]));
			
			pq.offer(new int[] {0,0,0});
			dijk[0][0]=0;
			v[0][0]=true;
			
			while(!pq.isEmpty()) {
				int[] cur=pq.poll();
				int r=cur[0];
				int c=cur[1];
				int w=cur[2];
				
				if(r==N-1 && c==N-1) {//도착
					break;
				}
				
				for (int d = 0; d < 4; d++) {//4방 탐색
					int nr=r+dr[d];
					int nc=c+dc[d];
					
					if(!check(nr,nc)) continue;
					
					if(!v[nr][nc] && dijk[nr][nc]>dijk[r][c]+map[nr][nc]) {
						v[nr][nc]=true;
						dijk[nr][nc]=dijk[r][c]+map[nr][nc];
						
						pq.offer(new int[] {nr,nc,dijk[nr][nc]});
					}
				}
				
			}
			
			min=dijk[N-1][N-1];
			System.out.println("#"+t+" "+min);
		}
	}
	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<N;
	}
}
