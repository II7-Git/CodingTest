import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1992_쿼드트리_백준 {

	static int [][] map;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		for (int i = 0; i < N; i++) {
			char [] ch=br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j]=ch[j]-'0';
			}
		}//배열 초기화 완료
		sb=new StringBuilder();
		quadTree(0,0,N);//시작 r,c좌표 ,배열크기N
		System.out.println(sb.toString());
	}
	private static void quadTree(int r, int c, int N) {
		if(N==1) {
			sb.append(map[r][c]);
			return;
		}
		boolean zero=true;
		boolean one=true;
		for (int i = r; i < N+r; i++) {
			for (int j = c; j < N+c; j++) {
				//if((!zero)&&(!one)) break;//둘다 false이면 출력
				if(zero&&(map[i][j]==1)) {
					zero=false;//0으로만 채워질 가능성은 없다
				}
				if(one&&(map[i][j]==0)) {
					one=false;
				}
			}
			//if((!zero)&&(!one)) break;//둘다 false이면 출력
		}
		if(zero) {
			sb.append(0);
		}else if(one) {
			sb.append(1);
		}else {//4분할내서 4방향 쿼드트리 해야한다,단 괄호는 쳐야함
			int R=N/2;
			sb.append("(");
			quadTree(r,c,R);//왼쪽 위
			quadTree(r,c+R,R);//오른쪽 위
			quadTree(r+R,c,R);//왼쪽 아래
			quadTree(r+R,c+R,R);//오른쪽 아래
			sb.append(")");
		}
	}
}
