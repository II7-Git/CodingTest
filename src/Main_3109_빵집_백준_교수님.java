import java.util.Scanner;

public class Main_3109_빵집_백준_교수님 {

	static int R,C;
	static char [][] map;
	static int [] dr= {-1,0,1};
	static int [] dc= {1,1,1};
	static int cnt;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		R=scann.nextInt();
		C=scann.nextInt();
		map=new char [R][C];
		for (int i = 0; i < R; i++) {
			map[i]=scann.next().toCharArray();
		}
		int ans=0;
		for (int i = 0; i < R; i++) {//왼쪽 열부터 출발
			if(dfs(i,0)) ans++;
		}
		
		System.out.println(ans);
	}
	private static boolean dfs(int r,int c) {
		for(int d=0;d<3;d++) {
			int nr=r+dr[d];
			int nc=c+dc[d];
			if(!check(nr,nc))continue;
			
			if(nc==C-1) {//도착
				return true;
			}
			map[nr][nc]='X';
			if(dfs(nr,nc)) return true;
		}
		return false;
	}
	private static boolean check(int r, int c) {
		return r>=0&&r<R&&c<C;
	}
}
