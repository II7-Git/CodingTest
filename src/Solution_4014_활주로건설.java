import java.util.Scanner;

public class Solution_4014_활주로건설 {

	static int T,N,X;
	static int [][]map;
	public static void main(String[] args) {
		Scanner scann= new Scanner(System.in);
		
		T=scann.nextInt();
		
		for (int t = 1; t <= T; t++) {
			N=scann.nextInt();
			X=scann.nextInt();
			int answer=0;
			map=new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j]=scann.nextInt();
				}
			}
			
			answer=count();
			System.out.println("#"+t+" "+answer);
		}
	}
	
	
	private static int count() {
		//가로방향 검사
		int widthCnt=Check(0);
		//세로방향 검사
		int heightCnt=Check(1);
		return widthCnt+heightCnt;
	}


	private static int Check(int oper) {//0일땐 가로 검사 1일떈 세로검사
		int count=0;
		for (int i = 0; i < N; i++) {
			boolean canBuild=true;//활주로 건설 가능 여부
			int h=0;
			
			if(oper==0) {
				h=map[i][0];//현재 높이 기록
			}else if(oper==1) {
				h=map[0][i];//현재 높이 기록
			}
			boolean shouldBuild=false;//높이가 달라져서 지어야한다
			int cnt=1;//경사로 지을 자원 확보됐는지 //처음에 자기 자리가 있으니까 일단 하나 확보하고 시작  
			
			for (int j = 1; j < N; j++) {//0은 넣고 시작하기에 1부터 시작
				//현재 높이 받아오기
				int cur=0;
				if(oper==0) {
					cur=map[i][j];
				}else if(oper==1) {
					cur=map[j][i];
				}
				
				//전과의 높이에 따른 처리
				if(h==cur) {//전과 같은 높이
					cnt++;//같은 높이면 cnt++;
					if(shouldBuild) {//내리막길을 지어야하는 상황이라면
						if(cnt>=X) {
							shouldBuild=false;
							cnt=0;
						}
					}
				}else if(h-cur==-1) {//오르막길이라면
					if(shouldBuild) {//경사로를 지어야하는 상황인데 높이가 또 바뀌었다
						canBuild=false;
						break;
					}
					//경사로가 전에 지어져야한다.
					//높이 바꿔주고 경사로가 지어질 수 있는지 확인
					if(cnt>=X) {//지어질 수 있다
						cnt=1;//경사로 짓고 높이 바뀌었으니 cnt 초기화
					}else {//경사로 못지음
						canBuild=false;
						break;
					}
					h=cur;//높이 변경
				}else if(h-cur==1) {//내리막길
					if(shouldBuild) {//경사로를 지어야하는 상황인데 높이가 또 바뀌었다
						canBuild=false;
						break;
					}
					
					//높이 바꿔주고 경사로 지어야됨을 알려준다
					h=cur;
					cnt=1;//cnt 초기화
					shouldBuild=true;
				}else {//높이차이가 2 이상
					canBuild=false;
					break;
				}
			}
			
			if(shouldBuild) canBuild=false;//다 끝났는데도 경사로가 건설되어야한다.따라서 불가
			if(canBuild) count++;
		}
		return count;
	}
}
