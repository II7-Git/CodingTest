import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_8382_방향전환 {

	static int T;
	static int x1,y1,x2,y2;
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,-1,0,1};//상좌하우  상하 0,2  좌우 1,3
	static int [][][] visited;//방법까지 고려하겠다 //[][]+[방법]
	
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		T=scann.nextInt();
		
		for (int t = 1; t <= T; t++) {
			x1=scann.nextInt()+100;
			y1=scann.nextInt()+100;
			x2=scann.nextInt()+100;
			y2=scann.nextInt()+100;
			
			visited=new int[201][201][2];//방법 2가지 고려
			
			Queue<int[]> que=new LinkedList<int[]>();
			
			que.offer(new int[] {x1,y1,0,0});// 상하로 시작
			que.offer(new int[] {x1,y1,0,1});// 좌우로 시작
			
			visited[x1][y1][0]=1;//상하 방법으로 방문
			visited[x1][y1][1]=1;//좌우 방법으로 방문
			int val=-1;
			while(!que.isEmpty()) {
				int[] cu=que.poll();
				int r=cu[0];
				int c=cu[1];
				int cnt=cu[2];
				int dir=cu[3];
				
				if(r==x2 && c==y2) {
					val=cnt;
					break;
				}
				
				for (int d = 1; d < 4; d+=2) {
					int s=(dir+d)%4;// 방법 dir=0 0+1 0+3 -> 방향
					int u=(dir+d)%2;//      0+1 0+3 -> 방법
					int nr=r+dr[s];
					int nc=c+dc[s];
					
					if(!check(nr,nc))continue;
					//그 지점에 그 방법으로 방문한적잉 없으면
					//다른 방법을 넣어주면서 이동
					if(visited[nr][nc][u]==0) {
						visited[nr][nc][u]=1;
						que.offer(new int[] {nr,nc,cnt+1,s});
					}
				}
			}
			System.out.println("#"+t+" "+val);
		}
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<201&& c>=0 && c<201;
	}

}
