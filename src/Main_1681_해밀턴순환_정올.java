import java.util.Scanner;

public class Main_1681_해밀턴순환_정올 {

	static int N;
	static int[][] map;
	static int min;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		map=new int[N][N];
		for (int i = 0; i <N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j]=scann.nextInt();
			}
		}
		min=Integer.MAX_VALUE;
		npr(0,0,new boolean [N],0);
		
		if(N==1) {//회사밖에 없다
			System.out.println(0);
		}else {
			System.out.println(min);
		}
		
	}
	private static void npr(int cnt,int prePos, boolean[] v, int total) {//prePos 전위치
		if(cnt==N-1) {
			if(map[prePos][0]==0) return;
			total+=map[prePos][0];//회사로 돌아오는 비용
			min=Math.min(min, total);
			return;
		}
		
		if(cnt==0) {//회사에서 출발
			//v[0]=true;
			for (int i = 1; i < N; i++) {//회사 제외
				if(map[prePos][i]==0) continue;
				v[i]=true;
				npr(cnt+1,i,v,map[prePos][i]);
				v[i]=false;
			}
		}
		else {
			for (int i = 1; i < N; i++) {
				if(v[i]||i==prePos||map[prePos][i]==0) continue;
				
				int temp = total+map[prePos][i];
				if(temp>min) continue;
				
				v[i]=true;
				npr(cnt+1,i,v,temp);
				v[i]=false;
			}
		}
	}
}
