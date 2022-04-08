

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2636_치즈 {

	static int M,N;
	static int [][] map;
	static int totalC;
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		
		totalC=0;
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1)totalC++;
			}
		}
		
		floodFill(0,0,-1);//외부를 전부 -1로 채우기
		
		//치즈 녹이기
		//구멍 채우기
		int turn=1;//1부터 한턴씩 늘어나자
		int cnt=0;//이번턴에 녹인 치즈의 수
		while(totalC>0) {
			//치즈 녹이기
			cnt=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j]==turn) {//치즈를 만나면
						cnt+=floodFillC(i, j, turn+1);
					}
				}
			}
			
			//구멍 찾기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <M; j++) {
					if(map[i][j]!=0)continue;
					
					boolean isO=false;
					for (int d = 0; d < 4; d++) {
						int nr=i+dr[d];
						int nc=j+dc[d];
						
						if(map[nr][nc]==-1) {
							isO=true;
							break;
						}
					}
					if(isO) {
						floodFill(i, j, -1);//이 구멍은 뚫린 구멍이니 -1로 채워라
					}
				}
			}
			turn++;
			totalC-=cnt;
			
		}
		
		
		System.out.println(turn-1);
		System.out.println(cnt);	
	}
	
	private static int floodFillC(int r, int c, int value) {//r,c 좌표부터 같은 값들을 value값으로 바꾼다//bfs하자
		int origin=map[r][c];
		int cnt=0;
		Queue<int[]> q=new LinkedList<int[]>();
		boolean [][] v=new boolean[N][M];
		
		q.offer(new int[] {r,c});
		v[r][c]=true;
		while(!q.isEmpty()) {
			int [] rc=q.poll();
			boolean isO=false;//외곽인지 판별
			
			for (int d = 0; d < 4; d++) {
				int nr=rc[0]+dr[d];
				int nc=rc[1]+dc[d];
				if(map[nr][nc]==origin&&!v[nr][nc]) {
					q.offer(new int[] {nr,nc});
					v[nr][nc]=true;
				}
				
				if(map[nr][nc]==-1&&!v[nr][nc]) {
					isO=true;
				}
			}
			
			if(isO) {//치즈 녹이는 부분
				map[rc[0]][rc[1]]=-1;
				cnt++;//
			}else {
				map[rc[0]][rc[1]]=value;
			}
		}
		return cnt;
	}
	
	private static void floodFill(int r, int c, int value) {//r,c 좌표부터 같은 값들을 value값으로 바꾼다//bfs하자
		int origin=map[r][c];
		Queue<int[]> q=new LinkedList<int[]>();
		boolean v[][]=new boolean[N][M];
		
		q.offer(new int[] {r,c});
		v[r][c]=true;
		
		while(!q.isEmpty()) {
			int [] rc=q.poll();
			map[rc[0]][rc[1]]=value;
			for (int d = 0; d < 4; d++) {
				int nr=rc[0]+dr[d];
				int nc=rc[1]+dc[d];
				if(check(nr,nc)&&!v[nr][nc]&&map[nr][nc]==origin) {
					q.offer(new int[] {nr,nc});
					v[nr][nc]=true;
				}
			}
		}
	}

	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<M;
	}
}
