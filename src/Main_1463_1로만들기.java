import java.util.Scanner;

public class Main_1463_1로만들기 {

	// N을 받았을 떄 DP 배열에 N-1 ,N/2,N/3 값이 존재하면 그 연산 값에 1만 더하면 될 것 // 즉 전 3개의 값이 다 존재하면 그 중 가장 연산값 작은 것 사용
	static int N;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);

		N=scann.nextInt();
		int [] dp=new int [N+1];
		
		dp[1]=0;//1은 목적 수이기에 0번 연산으로 도달
		if(N>=2) dp[2]=1;//2는 2로 나누면 1이 됨
		if(N>=3) dp[3]=1;//3도 동일
		
		for(int i=4;i<=N;i++) {
			int temp=Integer.MAX_VALUE;
			
			if(dp[i-1]!=0) temp=Math.min(temp, dp[i-1]);
			if(i%2==0 && dp[i/2]!=0) temp=Math.min(temp, dp[i/2]);
			if(i%3==0 && dp[i/3]!=0) temp=Math.min(temp, dp[i/3]);
			
			//if(temp==Integer.MAX_VALUE) continue;
			
			dp[i]=temp+1;
		}
		System.out.println(dp[N]);
	}
}
