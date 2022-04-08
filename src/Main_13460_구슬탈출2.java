import java.util.Scanner;

public class Main_13460_구슬탈출2 {

	static int N,M;
	static char [][] map;
	
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	static int count=-1;
	static int rr,rc;
	static int br,bc;
	static int d=-1;
	public static void main(String[] args) {
		Scanner scann= new Scanner(System.in);
		N=scann.nextInt();
		M=scann.nextInt();
		map=new char[N][M];
		
		for (int i = 0; i < N; i++) {
			char[] ch=scann.next().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j]=ch[j];
				if(map[i][j]=='R') {//빨간 공과 파란공 위치 설정
					rr=i;
					rc=j;
				}
				if(map[i][j]=='B') {
					br=i;
					bc=j;
				}
			}
		}
		
		//빨간색 기준으로 이동 가능 위치 검사, 파란색 움직임도 체크
		//방향을 정하고 이동시켜야한다
		//cnt=search(0);
		//출력
		System.out.println(count);
	}
	

}
