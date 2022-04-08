import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1828_냉장고_정올 {

	static class Chemi implements Comparable<Chemi>{
		int minTemp;
		int maxTemp;
		public Chemi(int minTemp, int maxTemp) {
			super();
			this.minTemp = minTemp;
			this.maxTemp = maxTemp;
		}
		@Override
		public int compareTo(Chemi o) {
			
			return this.minTemp!=o.minTemp?this.minTemp-o.minTemp:maxTemp-o.maxTemp;
		}
	}
	
	static int N;
	static int cnt;
	static Chemi [] che;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		cnt=0;
		
		che=new Chemi[N];
		for (int i = 0; i < N; i++) {
			String []  str=br.readLine().split(" ");
			che[i]=new Chemi(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
		}
		
		check();
		System.out.println(cnt);
	}
	private static void check() {
		Arrays.sort(che);//최저 온도 낮은 순 정렬
		
		int nowMax=che[0].maxTemp;
		cnt++;
		for (int i = 1,size=che.length; i < size; i++) {
			if(nowMax<che[i].minTemp) {
				nowMax=che[i].maxTemp;
				cnt++;
			}else if(nowMax>che[i].maxTemp) {//nowMax 갱신
				nowMax=che[i].maxTemp;
			}
		}
	}

}
