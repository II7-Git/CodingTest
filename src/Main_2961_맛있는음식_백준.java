import java.util.Scanner;

public class Main_2961_맛있는음식_백준 {

	static int N;
	static int [][] taste;
	static int diff;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		
		taste= new int[N][2];
		for (int i = 0; i < N; i++) {
			taste[i][0]=scann.nextInt();
			taste[i][1]=scann.nextInt();
		}
		diff=Integer.MAX_VALUE;
		subset(0,1,0);
		System.out.println(diff);
	}
	private static void subset(int cnt, int ts, int tb) {
		if(cnt==N) {
			if(tb==0) {
				return;
			}
			diff=Math.min(diff, Math.abs(ts-tb));
			return;
		}
		subset(cnt+1, ts*taste[cnt][0], tb+taste[cnt][1]);
		subset(cnt+1, ts, tb);
	}
	
}
