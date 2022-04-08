import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16935_배열돌리기3_백준 {
	static int N,M,R,Q;
	static int [][]	map;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[] str=br.readLine().split(" ");
		N=Integer.parseInt(str[0]);
		M=Integer.parseInt(str[1]);
		R=Integer.parseInt(str[2]);
		map=new int [N][M];
		for (int i = 0; i < N; i++) {
			String[] st=br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st[j]);
			}
		}//배열 읽기 완료
		StringTokenizer st= new StringTokenizer(br.readLine()," ");//연산 받기
		while(st.hasMoreTokens()) {
			int oper=Integer.parseInt(st.nextToken());
			switch (oper) {
			case 1:
				turn1();
				break;
			case 2:
				turn2();
				break;
			case 3:
				turn3();
				break;
			case 4:
				turn4();
				break;
			case 5:
				turn5();
				break;
			case 6:
				turn6();
				break;
			}
		}
		//출력
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	static void turn1() {//상하반전
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M; j++) {
				int temp=map[i][j];
				map[i][j]=map[N-1-i][j];
				map[N-1-i][j]=temp;
			}
		}
	}
	static void turn2() {//좌우반전
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M/2; j++) {
				int temp=map[i][j];
				map[i][j]=map[i][M-1-j];
				map[i][M-1-j]=temp;
			}
		}
	}
	static void turn3() {//오른쪽 90도 회전
		int [][] tempMap=new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tempMap[j][N-1-i]=map[i][j];
			}
		}
		int temp=M;
		M=N;
		N=temp;
		map=tempMap;
	}
	static void turn4() {//왼쪽 90도 회전
		int [][] tempMap=new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tempMap[M-1-j][i]=map[i][j];
			}
		}
		int temp=M;
		M=N;
		N=temp;
		map=tempMap;
	}
	static void turn5() {//부분배열 후 시계방향 이동
		int [][] tempMap=new int [N][M];
		int rc=N/2;//row Center
		int cc=M/2;//col Center
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int nr=i;
				int nc=j;
				if(i<rc&&j<cc) {//1번 부분배열
					nc=j+cc;
				}else if(i<rc&&j>=cc) {//2번 부분배열
					nr=i+rc;
				}else if(i>=rc&&j>=cc) {//3번 부분배열
					nc=j-cc;
				}else {//4번부분배열
					nr=i-rc;
				}
				tempMap[nr][nc]=map[i][j];
			}
		}
		map=tempMap;
	}
	static void turn6() {//부분배열 후 시계방향 이동
		int [][] tempMap=new int [N][M];
		int rc=N/2;//row Center
		int cc=M/2;//col Center
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int nr=i;
				int nc=j;
				if(i<rc&&j<cc) {//1번 부분배열
					nr=i+rc;
				}else if(i<rc&&j>=cc) {//2번 부분배열
					nc=j-cc;
				}else if(i>=rc&&j>=cc) {//3번 부분배열
					nr=i-rc;
				}else {//4번부분배열
					nc=j+cc;
				}
				tempMap[nr][nc]=map[i][j];
			}
		}
		map=tempMap;
	}
}
