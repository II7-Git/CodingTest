import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_탑 {

	static int N;
	static int nowNum;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> tap=new Stack<>();
		Stack<Integer> tapNum=new Stack<>();
		
		N=Integer.parseInt(br.readLine());
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			nowNum=Integer.parseInt(st.nextToken());
			
			if(tap.isEmpty()) {//만약 탑 스택이 비어있다면 수신받을 탑이 없다는 소리이므로 0 출력후  현재탑 push
				System.out.print(0+" ");
				tap.push(nowNum);
				tapNum.push(i);
			}
			else {//수신할 수 있는 탑이 있을 수 있다
				while(!tap.isEmpty()) {
					if(tap.peek()>=nowNum) {//탑 스택의 가장 가까운 탑을 꺼냈을 때 현재 자신보다 타워 크기가 높으면 수신 가능한탑이므로 출력
						System.out.print(tapNum.peek()+" ");
						break;
					}
					else {//수신 불가한 현재 탑보다 낮은 탑이 스택에 있기에 pop해서 제거해준다
						tap.pop();
						tapNum.pop();
					}
				}
				//반복해서 만약 탑 스택의 전부를 비교해도 수신할 수 없다면 0을 출력
				if(tap.isEmpty()) {
					System.out.print(0+" ");
				}
				//현재탑과 위치를 스택에 넣어준다
				tap.push(nowNum);
				tapNum.push(i);
			}
		}
	}
}
