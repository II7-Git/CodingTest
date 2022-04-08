import java.util.Arrays;
import java.util.Scanner;

public class Main_2563_색종이 {

	static int [][] map;
	static int h,w;
	static int N;
	static int overlapSize=0;
	static int cnt=0;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		map=new int[100][100];
		for(int i=0;i<N;i++) {
			w=scann.nextInt();
			h=scann.nextInt();
			for (int j = 0; j < 10; j++) {
				Arrays.fill(map[h+j],w,w+10,1 );
			}
		}
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(map[i][j]==1) cnt++;
			}
		}
		System.out.println(cnt);
	}
	
}
