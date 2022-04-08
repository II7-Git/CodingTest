import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_16236_아기상어_백준 {
	static int N,M;
	static int []dr= {-1,0,0,1};//조건에 맞게 위 왼쪽 오른쪽 아래 순서
	static int []dc= {0,-1,1,0};
	static int [][] map;
	static int r,c;
	static int size=2;//현재 상어의 크기
	static int cnt=0;//먹은 물고기 수//size==cnt면 size가 한단계 커짐
	static int sec=0;//초 세기
	public static void main(String[] args) {//bfs
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		M=0;
		map=new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j]=scann.nextInt();
				if(map[i][j]==9) {//아기 상어의 위치
					r=i; c=j;
				}else if(map[i][j]!=0) {//물고기가 있다
					M++;
				}
			}
		}//배열 구성 완료
		while(M!=0) {//종료되는 조건 M==0이거나 갈 곳이 없다
			if(size<7&&cnt==size) {//물고기 사이즈 커짐
				size++;
				cnt=0;
			}
			if(!findAndEat()) break; //bfs로 더이상 갈 곳이 없으면 false 리턴//false리턴하면 도움 요청
		}
		System.out.println(sec);
	}
	static class Pointer implements Comparable<Pointer>{//물고기들의 좌표
		int r,c;
		public Pointer(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public int compareTo(Pointer o) {//r 오름차순 정렬 후 c 오름차순 정렬
			if(Integer.compare(this.r, o.r)!=0) return Integer.compare(this.r, o.r);
			else return Integer.compare(this.c, o.c);
		}
	}
	private static boolean findAndEat() {
		boolean [][] visited=new boolean[N][N];
		PriorityQueue<Pointer> pq=new PriorityQueue<>();//탐색 좌표 받기
		pq.offer(new Pointer(r,c));
		visited[r][c]=true;
		int secCnt=0;
		while(!pq.isEmpty()) {
			int pqSize=pq.size();
			PriorityQueue<Pointer> temp = new PriorityQueue<>();
			for (int i = 0; i < pqSize; i++) {//현재 큐 비울때까지 반복//즉 하루치 행동
				Pointer now=pq.poll();//현재 위치 받기
				if(map[now.r][now.c]!=0 && map[now.r][now.c]<size) {//먹을 수 있다
					map[r][c]=0;
					map[now.r][now.c]=9;
					M--;//물고기 개수 -
					cnt++;//먹은 물고기 갯수 +
					r=now.r;
					c=now.c;
					sec+=secCnt++;
					return true;
				}
				for (int d = 0; d < 4; d++) {//4방 탐색하여 갈 수 있는곳 찾기
					int nr=now.r+dr[d];
					int nc=now.c+dc[d];
					if(check(nr,nc)&&!visited[nr][nc]&&map[nr][nc]<=size) {//갈 수 있는 위치이며 방문한 적 없음
						visited[nr][nc]=true;
						temp.offer(new Pointer(nr,nc));
					}
				}
			}
			secCnt++;
			pq=temp;
		}
		return false;
	}
	private static boolean check(int r, int c) {
		return 0<=r&&r<N&&0<=c&&c<N;
	}
}
