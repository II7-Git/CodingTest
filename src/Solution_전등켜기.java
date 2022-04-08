import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_전등켜기 {

	static int T,N;
	static int []light; 
	static int []now;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N=Integer.parseInt(br.readLine());
			light=new int [N+1];
			StringTokenizer st= new StringTokenizer(br.readLine()," ");
			for (int i = 1; i < N+1; i++) {
				light[i]=Integer.parseInt(st.nextToken());
			}
			now=new int[N+1];
			boolean same=false;
			int cnt=1;//첫번째부터 변환 시작
			int count=0;//불을 눌렀을 경우 세기
			while(!same) {
				if(light[cnt]==now[cnt]) {//현재 전등은 건들게 없으니 패스
					cnt++;
					if(cnt==N) same=true;
					continue;
				}
				
				for (int i = cnt; i < N+1; i+=cnt) {//cnt부터 cnt번째마다 자리의 전구를 바꾸어야한다.
					if(now[i]==0) now[i]=1;
					else now[i]=0;
				}
				count++;//전구 눌렀으니까 count++
				
				//cnt 뒤의 자리부터 다 맞는지 체크 맞으면 종료해도 좋다
				boolean isSame=true;
				for (int i = cnt+1; i < N+1; i++) {
					if(light[i]!=now[i]) {
						isSame=false;
						cnt=i;//cnt는 i번째 자리부터 탐색하면 된다
						break;
					}
				}
				if(isSame) same=true;
			}
			System.out.println("#"+t+" "+count);
		}
	}
}
