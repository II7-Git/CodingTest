import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_1158_요세푸스 {
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		int N=scann.nextInt();
		int K=scann.nextInt();
		Queue<Integer> queue=new LinkedList<Integer>();
		StringBuilder sb=new StringBuilder();
		sb.append("<");
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}//큐 삽입
		int temp=0;
		while(!queue.isEmpty()) {
			if(queue.size()<=1) {//남은거 모두 출력 후 종료
				temp=queue.poll();
				sb.append(temp).append(">");
			}else {
				for (int i = 1; i < K; i++) {//K번 전까지만 poll offer
					temp=queue.poll();
					queue.offer(temp);
				}
				temp=queue.poll();
				sb.append(temp).append(", ");
			}
		}
		
		System.out.println(sb.toString());
	}

}
