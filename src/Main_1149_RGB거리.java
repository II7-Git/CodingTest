import java.util.Scanner;

public class Main_1149_RGB거리 {

	static int N;
	//특정 집이 R,G,B 일 때 값+ 그전에 집이 R일 떈 G,B 중 최솟값 G일땐 R,B 중 최솟값... 이런식으로 비교해서 R,G,B 각각의 값일 때 최솟값을 구하면 된다//1번집은 RGB 금액을 각각 줘야한다
	
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		N=scann.nextInt();
		int [] R,G,B;
		R=new int[N+1];
		G=new int[N+1];
		B=new int[N+1];
		
		R[1]=scann.nextInt();
		G[1]=scann.nextInt();
		B[1]=scann.nextInt();
		
		for (int i = 2; i <= N; i++) {
			int r=scann.nextInt();
			int g=scann.nextInt();
			int b=scann.nextInt();
			
			R[i]=Math.min(r+G[i-1], r+B[i-1]);//빨간색을 칠했을 때 전 집의 색을 칠할떄까지의 최소 비용을 더한 값
			G[i]=Math.min(g+R[i-1], g+B[i-1]);
			B[i]=Math.min(b+G[i-1], b+R[i-1]);
		}
		
		int min=Math.min(R[N], G[N]);
		min=Math.min(min, B[N]);
		
		System.out.println(min);
	}
}
