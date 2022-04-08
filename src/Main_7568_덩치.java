import java.util.Scanner;

public class Main_7568_덩치 {

	static int N;
	static int w[];
	static int h[];
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		w=new int[N];
		h=new int[N];
		
		for (int i = 0; i < N; i++) {
			w[i]=scann.nextInt();
			h[i]=scann.nextInt();
		}
		
		for (int i = 0; i < N; i++) {
			int cnt=1;
			for (int j = 0; j < N; j++) {
				if(i==j) continue;
				if(w[i]<w[j]&&h[i]<h[j])
					cnt++;
			}
			System.out.print(cnt+" ");
		}
	}
}
