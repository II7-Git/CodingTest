import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_4485_녹색옷입은애가젤다지 {

	static int N;
	static int [][]map;
	static boolean [][] v;
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		int t=1;
		N=scann.nextInt();
		while(N!=0) {
			map=new int[N][N];
			int min=0;
			for (int i = 0; i <N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j]=scann.nextInt();
				}
			}
			
			int [][] dijk=new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dijk[i], Integer.MAX_VALUE);
			}
			dijk[0][0]=map[0][0];
			v=new boolean[N][N];
			v[0][0]=true;
			PriorityQueue<int[]> pq=new PriorityQueue<>((x,y) -> Integer.compare(x[2], y[2]));
			pq.offer(new int[] {0,0,dijk[0][0]});
			
			while(!pq.isEmpty()) {
				int [] now=pq.poll();
				int r=now[0];
				int c=now[1];
				int w=now[2];
				
				if(r==N-1&&c==N-1) {//도착
					min=dijk[N-1][N-1];
					break;
				}
				
				for (int d = 0; d < 4; d++) {
					int nr=r+dr[d];
					int nc=c+dc[d];
					
					if(!check(nr,nc)) continue;
					
					if(!v[nr][nc]&& dijk[nr][nc]>dijk[r][c]+map[nr][nc]) {
						dijk[nr][nc]=dijk[r][c]+map[nr][nc];
						v[nr][nc]=true;
						pq.offer(new int[] {nr,nc,dijk[nr][nc]});
					}
				}
				
			}
			
			
			
			
			
			System.out.println("Problem "+(t++)+": "+min);
			N=scann.nextInt();
		}

	}
	private static boolean check(int r, int c) {
		return r>=0 &&r<N &&c>=0&&c<N;
	}

}
