import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_탈주범검거 {//사실상 조건부 bfs

	static int N,M,R,C,L,T;
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	static int [][] map;
	static boolean [][] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 1; t <=T; t++) {
			int answer=0;
			st=new StringTokenizer(br.readLine()," ");
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			R=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());
			
			map=new int[N][M];
			v=new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st=new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < M; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			//맵 초기화 완료
			
			Queue<int[]> que=new LinkedList<int[]>();
			
			//처음 R,C 위치 큐에 넣기
			que.offer(new int[] {R,C});
			v[R][C]=true;
			//큐를 턴 개념으로 순회
			//이 떄 터널에 조건에 맞게 탐색 가능해야 큐에 넣기 //현재 r,c 위치에서 갈수 있는 위치여야 하고,상대 위치에서도 받을 수 있는 터널의 모양이어야한다
			int turn=0;
			while(!que.isEmpty()) {
				int size=que.size();//이번 턴에 탐색할 요소
				for (int i = 0; i < size; i++) {//전 턴에 추가된 요소들 탐색
					int [] cur=que.poll();
					int r=cur[0];
					int c=cur[1];
					int type=map[r][c];
					answer++;//범인이 올 수 있는 곳이다
					
					//type에 맞게 갈 수 있는 노드 처리
					int[] canGo=getD(type);//터널 타입에 맞게 갈 수 있는 방향을 담은 리스트 리턴
					int dSize=canGo.length;
					for (int n = 0; n < dSize; n++) {
						int d=canGo[n];
						
						int nr=r+dr[d];
						int nc=c+dc[d];
						
						if(!check(nr,nc,d)) continue;//평소랑 다르게 상대쪽에서도 받을 수 있는지 검사해야한다.
						
						if(!v[nr][nc]) {
							v[nr][nc]=true;
							que.offer(new int[] {nr,nc});
						}
					}
				}
				
				if(++turn==L) break;//턴이 하나 증가해서 L과 같다면 종료
			}
			
			System.out.println("#"+t+" "+answer);
		}
	}
	private static boolean check(int nr, int nc, int d) {//nr,nc가 주어졌을 때 원래 왔던 r,c 좌표를 -d로 알아내어서 nr nc 터널로 갈 수 있는 곳인지 확인
		if(!(nr>=0&&nr<N&&nc>=0&&nc<M)) return false;//먼저 맵 범위 밖이면 false 리턴
		
		int type=map[nr][nc];
		if(type==0) return false;//0이면 터널이 없다는 뜻이므로 갈 수 없다
		
		//원래 r,c 위치 찾기
		int r=nr-dr[d];
		int c=nc-dc[d];
		
		int[] canGo=getD(type);
		int dSize=canGo.length;
		for (int i = 0; i < dSize; i++) {
			int nowD=canGo[i];
			
			int cr=nr+dr[nowD];
			int cc=nc+dc[nowD];
			
			if(cr==r && cc==c) return true;//같은지만 확인하기에 범위 밖으로 넘어가는건 신경 안써도 된다.
		}
		return false;
	}
	
	private static int[] getD(int type) {//타입에 맞춰 갈 수 있는 방향 배열 리턴
		switch (type) {
		case 1: return new int[] {0,1,2,3};//상하좌우
		case 2: return new int[] {0,2};//상하
		case 3: return new int[] {1,3};//좌우
		case 4: return new int[] {0,1};//상우
		case 5: return new int[] {1,2};//하우
		case 6: return new int[] {2,3};//하좌
		case 7: return new int[] {0,3};//상좌
		}
		return null;
	}

}
