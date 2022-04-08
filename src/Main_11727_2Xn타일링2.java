import java.util.Scanner;

public class Main_11727_2Xn타일링2 {

	//f(n)= 1*f(n-1) + 2*f(n-2); 의 형태를 가짐 
	//f(n-1)에서 추가하는 경우의 수가 하나 
	//f(n-2)에서 추가할 경우의 수는 가로로 두개 놓인 경우의 수, 2*2 정사각형 블록이 들어간 경우 해서 총 두개
	static int N;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		
		int [] memo=new int [N+2];
		
		memo[1]=1;
		memo[2]=3;
		
		for (int i = 3; i < N+1; i++) {
			memo[i]=(memo[i-1]+2*memo[i-2])%10007;
		}
		System.out.println(memo[N]);
	}

}
