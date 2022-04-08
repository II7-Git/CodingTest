import java.util.Scanner;
//시간 초과 날 것//그리디 방식을 찾아보자
public class Main_2839_설탕배달_백준_dfs {
	
	static int N;
	static int min;
	
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		min=Integer.MAX_VALUE;
		sugarMove(N, 0 ,0);
		
		System.out.println(min==Integer.MAX_VALUE?-1:min);
	}

	private static void sugarMove(int weight, int t, int f) {
		if(weight<0) { //가능한 횟수 최대한 줄이기
			return;
		}
		if(weight==0) {
			min=Math.min(min, t+f);
			return ;
		}
		sugarMove(weight-5,t+1,f);//5의 회수 증가
		sugarMove(weight-3,t,f+1);//3의 회수 증가
	}
}
