import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2577_회전초밥_정올 {

	static int N,d,k,c;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		d=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		
		int [] food=new int[d+1]; // 음식의 종류 수 만큼 배열 생성 //1이상  d 이하
		
		int maxFood=0;//다르게 먹을 수 있는 음식의 수
		if(d<k+1) {
			maxFood=d;
		}else {
			maxFood=k+1;
		}
		
		int max=0;
		
		Queue<Integer> que=new LinkedList<Integer>();
		Queue<Integer> firstQue=new LinkedList<Integer>();//처음 큐 기록 //마지막 회전초밥때 사용
		int cnt=0;//각 연결 초밥마다 음식 갯수를 셀 변수
		
		for (int i = 0; i < k; i++) {//먼저 k개 연결 큐 생성
			int now=Integer.parseInt(br.readLine());
			
			que.offer(now);
			firstQue.offer(now);
			if(food[now]++ ==0)cnt++;//연결 접시에서 처음 먹는 음식//0이었다면 음식 가짓수에 cnt++;
		}
		
		if(food[c]==0) {//쿠폰 음식 사용 가능
			max=cnt+1;
		}else {
			max=cnt;//쿠폰 음식을 이미 먹었기에 불가
		}
		
		
		//나머지 모두 처리//회전 초밥이기에 다시 제자리로 올때까지 반복해야한다
		for (int i = 0,end=N-k; i < N; i++) {
			if(max==maxFood) break;//이미 최대로 먹을 수 있는 가짓수 도달
			
			//맨 앞에 음식 버리기
			int removeFood=que.poll(); //이미 추가해놨던 것이기에 무조건 1이상이다
			if(--food[removeFood] == 0) {//제거 했는데 0이라면 음식cnt에서도 -1
				cnt--;
			}
			
			//음식 추가
			int addFood=0;
			if(i<end) {//회전 초밥의 자리를 br 에서 가져온다
				addFood=Integer.parseInt(br.readLine());
			}else {//회전초밥 형태는 완성되었고 다시 처음 자리로 가기 전까지의 조합이 남아있기에 firstque를 활용해 확인하는 부분
				addFood=firstQue.poll();
			}
			if(food[addFood]==0) cnt++;//음식 가짓수에 추가 가능
			food[addFood]++;
			
			que.offer(addFood);
			
			if(food[c]==0) {//쿠폰 음식 사용 가능
				max=Math.max(max, cnt+1);
			}else {
				max=Math.max(max, cnt);
			}
		}
		
		
		System.out.println(max);
	}

}
