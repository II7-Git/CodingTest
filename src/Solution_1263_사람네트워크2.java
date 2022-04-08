import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1263_사람네트워크2 {

	static int T,N;
	static int [][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());

		for (int t = 1; t <=T; t++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			N=Integer.parseInt(st.nextToken());
			map=new int[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <N; j++) {
					int temp=Integer.parseInt(st.nextToken());
					if(temp==0 && i!=j) {
						temp=Integer.MAX_VALUE/1000;
					}
					map[i][j]=temp;
				}
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
			
			int min=Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int temp=0;
				for (int j = 0; j < N; j++) {
					if(map[i][j]!=Integer.MAX_VALUE/1000) {
						temp+=map[i][j];
					}
				}
				if(temp!=0) {
					min=Math.min(min, temp);
				}
			}
			System.out.println("#"+t+" "+min);
		}
	}
}
