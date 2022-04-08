import java.util.Scanner;

public class Main_BJ_1074_Z {

	static int N,r,c,cnt;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		r=scann.nextInt();
		c=scann.nextInt();
		cnt=0;
		z(0,0,1<<N);//w 한변의 길이
		//System.out.println(cnt);
	}
	private static void z(int rr, int cc, int w) {
		if(rr==r && cc==c) {//찾는 위치에 도달
			System.out.println(cnt);
			return;
		}
		//범위 내
		if(r>=rr && r<rr+w && c>=cc && c <cc+w) {
			int hw=w/2;
			z(rr,cc,hw); //11
			z(rr,cc+hw,hw); //3
			z(rr+hw,cc,hw); //7
			z(rr+hw,cc+hw,hw); //5
		}else {
			cnt+=w*w;
		}
	}
}
