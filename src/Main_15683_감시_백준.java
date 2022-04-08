import java.util.ArrayList;
import java.util.Scanner;

public class Main_15683_감시_백준 {

	//1,3,4번은 4가지 방향 // 2는 두가지 방향 //5번은 한가지 방향만 확인하면 된다.
	static int N,M,min,C;//C는카메라 갯수 저장
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	static char [][] map;
	static ArrayList<Integer> camera;
	static ArrayList<int []> cameraPoint;
	static int [] dir;//방향 저장할 배열
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		M=scann.nextInt();
		map=new char[N][M];
		camera=new ArrayList<>(); //카메라 종류 기억
		cameraPoint=new ArrayList<>();//카메라 위치 기억
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j]=scann.next().charAt(0);
				int temp=map[i][j]-'0';
				if(temp>=1&&temp<=5) {//cctv의 위치이다
					camera.add(temp);
					cameraPoint.add(new int[] {i,j});
				}
			}
		}
		//알고리즘
		min=Integer.MAX_VALUE;//사각지대 구할 곳
		C=camera.size();
		dir=new int[C];
		dfs(0);//카메라 갯수
		
		System.out.println(min);
	}
	
	private static void dfs(int cnt) {
		if(cnt==C) {//방향이 다 정해졌다
			for (int i = 0; i < camera.size(); i++) {
				int [] rc=cameraPoint.get(i);
				see(camera.get(i),rc[0],rc[1],dir[i]);
			}
			min=Math.min(min, countMap());
			return;
		}
		int cameraType=camera.get(cnt);//카메라 종류 꺼내기
		if(cameraType==5) {//전방위 체크하기에 한가지 경우
			dir[cnt]=0;
			dfs(cnt+1);
			//blind()
		}else if(cameraType==2) {//2가지 경우만 존재
			for (int d = 0; d < 2; d++) {
				dir[cnt]=d;
				dfs(cnt+1);
			}
		}else {//4방 체크 해야한다
			for (int d = 0; d < 4; d++) {
				dir[cnt]=d;
				dfs(cnt+1);
			}
		}
		
	}
	private static void see(int camera, int r, int c, int d) {//카메라 타입에 맞게 보는 방향을 # 처리 시키자
		if(camera==5) {//4방
			for (int i = 0; i < 4; i++) {
				checkSee(r,c,i);
			}
		}else if(camera==2) {
			if(d==0) {//세로 감시
				checkSee(r,c,0);
				checkSee(r,c,2);
			}else {//가로 감시
				checkSee(r,c,1);
				checkSee(r,c,3);
			}
		}else if(camera==1){//1방 감시
			checkSee(r,c,d);
		}else if(camera==3) {//90도 감시
			for (int i = 0; i < 2; i++) {
				checkSee(r,c,(d+i)%4);
			}
		}else if(camera==4) {//3방 감시
			for (int i = 0; i < 4; i++) {
				if(d==i)continue;
				checkSee(r, c, i);
			}
		}
	}
	
	private static void checkSee(int r, int c, int d) {//d방향으로 쭉 체크
		int nr=r+dr[d];
		int nc=c+dc[d];
		while(check(nr,nc)) {
			if(map[nr][nc]=='6') break;
			if(map[nr][nc]=='0') map[nr][nc]='#';
			nr+=dr[d];
			nc+=dc[d];
		}
	}
	
	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<M;
	}

	public static int countMap() {
		int cnt=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]=='0')cnt++;
				if(map[i][j]=='#')map[i][j]='0';
			}
		}
		return cnt;
	}
}
