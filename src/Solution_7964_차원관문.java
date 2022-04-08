import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_7964_차원관문 {

	static int T,N,D;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			N=Integer.parseInt(st.nextToken());
			D=Integer.parseInt(st.nextToken());
			int needs=0;
			int cnt=0;
			st=new StringTokenizer(br.readLine()," ");
			for (int i = 0; i < N; i++) {
				int now =Integer.parseInt(st.nextToken());
				if(now==0) {//0일땐 cnt++ 
					cnt++;
					if(cnt==D) {//cnt가 제한거리가 됐으면 차원관문 하나 필요
						needs++;
						cnt=0;
					}
				}else if(now==1) {//1일땐 cnt=0;
					cnt=0;
				}
			}
			
			System.out.println("#"+t+" "+needs);
		}
	}

}
