import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1220_Magnetic {
	static int[][] map;
	static int cnt;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int T=10;
		for (int t = 1; t <= T; t++) {
			N=Integer.parseInt(br.readLine());
			map=new int [N][N];
			StringTokenizer st;
			cnt=0;
			for (int i = 0; i < N; i++) {
				st=new StringTokenizer(br.readLine()," ");
				for (int j = 0; j < N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			counting();//교착 상태의 갯수 세기
			
			System.out.println("#"+t+" "+cnt);
		}
	}
	private static void counting() {//무조건 빨강 나오고 파랑이 나온다
		int count=0; //교착 상태 갯수 세기
		for (int i = 0; i < N; i++) {//줄별로 파악 //즉 map[j][i]형태
			count=0;
			boolean n=false;//만나면 체크
			for (int j = 0; j < N; j++) {
				if(map[j][i]==1 && !n) {//
					n=true;
				}
				else if(map[j][i]==2 && n) {//이미 전에 빨간색을 만난적이 있고 파란색이 나왔다-> 교착상태
					n=false;
					count++;
				}
			}
			cnt+=count;
		}
	}
}
