import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D2_1859_백만장자2 {

	static int T;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int N=Integer.parseInt(br.readLine());
			long deposit=0;//최종 금액 저장소
			
			int [] costs=new int[N];
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			for (int n = 0; n < N; n++) {
				costs[n]=Integer.parseInt(st.nextToken());
			}
			
			//역순으로 탐방
			//더 높은값 나오기 전까지 차이 더하기
			int max=0;
			for (int i = N-1; i >= 0; i--) {
				if(max<costs[i]) {// 더 큰 값을 만났을 때
					max=costs[i];
				}
				else {
					deposit+=(long)(max-costs[i]);
				}
			}
			
			System.out.println("#"+t+" "+deposit);
		}
	}

	
}
