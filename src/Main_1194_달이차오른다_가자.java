import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_1194_달이차오른다_가자 {

	static int N,M;
	static char [][] map;
	static boolean [][][]v;
	
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	
	
	
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		M=scann.nextInt();
		int startR=0,startC=0;
		map=new char[N][M];
		v=new boolean[N][M][64];//0~63 -> 111111의 비트마스킹 사용 위함
		for (int i = 0; i < N; i++) {
			char [] ch=scann.next().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j]=ch[j];
				if(map[i][j]=='0') {
					startR=i;
					startC=j;
				}
			}
		}
		
		
		Queue<Minsik> que=new LinkedList<>();
		que.offer(new Minsik(startR, startC, 0, 0));//r,c위치에서 키를 가지지 않은 상태에서 출발
		v[startR][startC][0]=true;
		int val=-1;
		while(!que.isEmpty()) {
			Minsik minsik=que.poll();
			int r=minsik.r;
			int c=minsik.c;
			int cnt=minsik.cnt;
			int key=minsik.key;
			
			if(map[r][c]=='1') {//탈출
				val=cnt;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr=r+dr[d];
				int nc=c+dc[d];
				
				if(!check(nr,nc)) continue;
				
				if(map[nr][nc]=='#') {//벽
					continue;
				}else if(map[nr][nc]-'a'>=0 && map[nr][nc]-'a'<=5) {// key 있는 곳
					int index=map[nr][nc]-'a';
					int temp=key|(1<<index);//키를 주웠다고 가정한 위치
					if(!v[nr][nc][temp]) {//키를 주워도 기존과 겹치지 않는다면
						v[nr][nc][temp]=true;
						que.offer(new Minsik(nr, nc, cnt+1, temp));
					}
				}else if(map[nr][nc]-'A'>=0 && map[nr][nc]-'A'<=5) {//문이 있는곳
					int index=map[nr][nc]-'A';
					boolean canPass=(key&(1<<index))>0? true:false;//특정문에 맞는 열쇠가 있는지
					if(canPass && !v[nr][nc][key]) {//문에 맞는 열쇠가 있고 이상태로 처음 오는 문이라면
						v[nr][nc][key]=true;
						que.offer(new Minsik(nr, nc, cnt+1, key));
					}
				}else {//'0'이나'1','.' 위치는 그냥 체크만 하고 넘기면 된다
					if(!v[nr][nc][key]) {
						v[nr][nc][key]=true;
						que.offer(new Minsik(nr, nc, cnt+1, key));
					}
				}
			}
		}
		
		System.out.println(val);
	}
	
	
	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<M;
	}


	static class Minsik{
		int r;
		int c;
		int cnt;
		int key;
		public Minsik(int r, int c, int cnt, int key) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.key = key;
		}

		
	}
}
