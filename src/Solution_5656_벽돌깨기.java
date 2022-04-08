import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기 {

	static int T,N,W,H;
	static int total;
	static int max,min;
	static int [] dr = {-1,0,1,0};
	static int [] dc = {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
		T=Integer.parseInt(br.readLine().trim());
		
		for (int t = 1; t <= T; t++) {
			int [][] map;//map은 복사해서 계속 써야할 것 같다
			st=new StringTokenizer(br.readLine().trim()," ");
			N=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			H=Integer.parseInt(st.nextToken());
			
			map=new int[H][W];
			total=0;
			for (int i = 0; i < H; i++) {
				st=new StringTokenizer(br.readLine().trim()," ");
				for (int j = 0; j < W; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]!=0) total++;//남은 벽돌 구할 때 사용
				}
			}
			max=0;
			min=Integer.MAX_VALUE;
			shoot(0,map,0);//shoot(cnt,map,break);
			
			//System.out.println("total : "+total+" max :"+ max);
			System.out.println("#"+t+" "+(total-max));
		}
	}
	private static void shoot(int cnt, int[][] map, int breakCnt) {
		if(cnt==N) {//구슬을 N번 다 쏜 상태
			//max=Math.max(breakCnt, max);
			return;
		}
		
		for (int j = 0; j < W; j++) {//가로로 전부 구슬 쏴본다
			//맵 복사
			int[][] copyMap=new int[H][W];
			for (int i = 0; i < H; i++) {
				copyMap[i]=map[i].clone();
			}
			//변수들 초기화
			boolean [][] v=new boolean[H][W];
			int nowBreakCnt=0;//이번 턴에 부순 벽돌 수
			
			int startR=-1;
			int startC=j;
			
			//시작 r 위치 찾기
			for (int i = 0; i < H; i++) {
				if(copyMap[i][j]!=0) {
					startR=i;
					break;
				}
			}
			//만약 구슬이 아무것도 못깼을 때
			if(startR==-1) {
				shoot(cnt+1,copyMap,breakCnt);
				continue;
			}
			
			//벽돌 깨기//bfs
			Queue<int[]> que=new LinkedList<int[]>();
			que.offer(new int[] {startR,startC});
			v[startR][startC]=true;
			
			while (!que.isEmpty()) {
				int [] now=que.poll();
				int r=now[0];
				int c=now[1];
				int pow=copyMap[r][c]-1;
				copyMap[r][c]=0;
				nowBreakCnt++;
				
				//pow==0 이면 1로 자기 자신만 삭제된다
				if(pow==0) continue;
				//pow>0 , 즉, 연쇄 폭발 가능
				for (int d = 0; d < 4; d++) {
					for (int i = 1; i <= pow; i++) {
						int nr=r+dr[d]*i;
						int nc=c+dc[d]*i;
						
						if(!check(nr,nc))continue;
						
						if(!v[nr][nc]&&copyMap[nr][nc]!=0) {
							v[nr][nc]=true;
							que.offer(new int[] {nr,nc});
						}
					}
				}
			}
			
			//벽돌 밑으로 떨어지기
			falling(copyMap);
			
			//넘기기
			shoot(cnt+1, copyMap, breakCnt+nowBreakCnt);
		}
	}
	
	private static void falling(int[][] map) {
		for (int j = 0; j < W; j++) {//열별로 탐색
			for (int i = H-1; i >= 0; i--) {
				if(map[i][j]!=0) {//밑에 있는 0과 교체
					
					int zeroR=-1;
					for (int k = H-1; k >i; k--) {//i보다 밑에 있는 것 중에 0 찾기
						if(map[k][j]==0) {
							zeroR=k;
							break;
						}
					}
					if(zeroR==-1) continue;
					
					map[zeroR][j]=map[i][j];
					map[i][j]=0;
					
				}
			}
		}
	}
	private static boolean check(int r, int c) {
		return r>=0 &&r<H &&c>=0 &&c<W;
	}
}
