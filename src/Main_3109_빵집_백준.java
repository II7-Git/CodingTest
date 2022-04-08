import java.util.Scanner;

public class Main_3109_빵집_백준 {

	static int R,C;
	static char [][] map;
	static int [] dr= {-1,0,1};
	static int dc= 1;//무조건 오른쪽으로만 간다
	static int cnt;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		R=scann.nextInt();
		C=scann.nextInt();
		map=new char [R][C];
		for (int i = 0; i < R; i++) {
			char [] ch=scann.next().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j]=ch[j];
			}
		}
		cnt=0;
		boolean [] cango=new boolean[R];
		for (int i = 0; i < R; i++) {//왼쪽 열부터 출발
			go(i,0);//왼쪽부터 출발
		}
		
		for (boolean can:cango) {
			if(can) cnt++;
		}
		System.out.println(cnt);
	}
	private static boolean go(int r,int c) {
		map[r][c]='O';
		if(c==C-1) {
			return true;
		}
		boolean cango=false;
		int[] nr=new int[] {r+dr[0],r+dr[1],r+dr[2]};
		int nc=c+1;
		for (int i = 0; i < 3; i++) {
			if(check(nr[i],nc)) {
				cango=go(nr[i],nc);
			}
			if(cango) break;
		}
		return cango;
	}
	private static boolean check(int r, int c) {
		return r>=0&&r<R&&c<C&&(map[r][c]=='.');
	}
}
