import java.util.Arrays;
import java.util.Scanner;

public class Main_1987_알파벳_백준 {

	static int R,C;
	static char[][] map;
	static int []dr= {-1,0,1,0};
	static int []dc= {0,1,0,-1};
	static int max;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		R=scann.nextInt();
		C=scann.nextInt();
		map=new char[R][C];
		for (int i = 0; i <R; i++) {
			map[i]=scann.next().toCharArray();
		}
		max=Integer.MIN_VALUE;
		alpha(0,0,new boolean[26],0);//r,c,알파벳 나왔는지 배열
		System.out.println(max);
	}
	private static void alpha(int r, int c, boolean[] v,int cnt) {
		cnt++;
		char A=map[r][c];//현재 위치 알파벳 받기
		int index=A-'A';
		if(v[index]) {//이미 나왔던 알파벳이라는 뜻이다//max 비교 후 끝내라
			cnt--;
			max=Math.max(max, cnt);
			return;
		}
		if(cnt==26||cnt==R*C) {
			max=Math.max(max, cnt);
			return;
		}
		//안나왔던 알파벳
		v[index]=true;
		map[r][c]='0';//갔던 위치임을 표시
		boolean cango=false;
		for (int d = 0; d < 4; d++) {
			int nr=r+dr[d];
			int nc=c+dc[d];
			if(!check(nr,nc))continue;
			alpha(nr,nc,v,cnt);
			cango=true;
		}
		v[index]=false;
		map[r][c]=A;
		if(!cango) {//더이상 갈 수 있는 곳이 없으면 끝
			max=Math.max(max, cnt);
			return;
		}
	}
	private static boolean check(int r, int c) {
		return r>=0&&r<R&&c>=0&&c<C&&map[r][c]!='0';
	}
}
