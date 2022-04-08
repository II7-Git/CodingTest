import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_D2_1859_백만장자 {

	static int T;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int N=Integer.parseInt(br.readLine());
			long money=0;
			Stack<Integer> stack=new Stack<>();
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			for (int i = 0; i < N; i++) {
				stack.push(Integer.parseInt(st.nextToken()));
			}

			long max=-1;
			long product=0;//물건 가격
			long cnt=0;//물건 갯수 카운팅
			while(!stack.isEmpty()) {
				int temp=stack.pop();
				if(max<temp) {//물건을 팔아야할 때
					money+=((max*cnt)-product);
					cnt=0;
					product=0;
					max=temp;
				}else {
					product+=temp;
					cnt++;
				}
			}
			
			money+=((max*cnt)-product);
			System.out.println("#"+t+" "+money);
		}
	}

	
}
