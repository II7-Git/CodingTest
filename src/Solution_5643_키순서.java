import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5643_키순서 {
	static int N,M,T;
	static int [][] map;
	static int MAX=100000;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for (int t = 1; t <=T; t++) {
			
			N=Integer.parseInt(br.readLine());
			M=Integer.parseInt(br.readLine());
			
			map=new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(map[i], MAX);
				map[i][i]=0;
			}
			//
			StringTokenizer st;
			for (int i = 0; i < M; i++) {
				st=new StringTokenizer(br.readLine()," ");
				int a=Integer.parseInt(st.nextToken())-1;
				int b=Integer.parseInt(st.nextToken())-1;
				
				map[a][b]=1;//a<b
			}
			
			//플로이드 와셜
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						
						if(map[i][j]>map[i][k]+map[k][j]) {
							map[i][j]=map[i][k]+map[k][j];
						}
					}
				}
			}
			
			
			int cnt=N;
			for (int i = 0; i < N; i++) {
				boolean know=true;
				for (int j = 0; j < N; j++) {
					if(map[i][j]==MAX && map[j][i]==MAX) {//둘 사이에 오거나 가는 길이 없으면 불가
						know=false;
						break;
					}
				}
				if(!know) cnt--;
			}
			System.out.println("#"+t+" "+cnt);
		}
	}
}
