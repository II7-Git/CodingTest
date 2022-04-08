import java.util.Scanner;

public class Main_11726_2Xn타일링 {

	//f(n)= 1*f(n-1) + 1*f(n-2); 의 형태를 가짐 f(n-1)에서 추가하는 경우의 수가 하나 f(n-2)에서 추가할 수 있는 경우의 수도 가로로 두개 놓인 한가지밖에 없다
	static int N;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		
		int [] memo=new int [N+1];
		
		memo[0]=0;
		memo[1]=1;
		if(N>=2) memo[2]=2; //N이 1이라면 이 배열이 만들어지지 않기에 주의해야한다
		
		for (int i = 3; i <= N; i++) {
			memo[i]=(memo[i-1]+memo[i-2])%10007;
		}
		System.out.println(memo[N]);
	}

}
