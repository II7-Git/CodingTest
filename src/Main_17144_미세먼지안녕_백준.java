import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17144_미세먼지안녕_백준 {

	static int R,C,T;
	static int [][]map;
	
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	static int [] cR;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken());
		cR=new int[2];//클리너 r위치만 알면 된다
	}
	static void swip() {//que던 스택이던 차례로 넣고 돌리자
		
	}
	static void spread() {
		Queue<int[]> pointer=new LinkedList<>();//r,c,weight;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]>0) {//먼지가 퍼질 수 있다
					pointer.offer(new int[] {i,j,map[i][j]});
				}
			}
		}
		//
		int [][] newMap=new int[R][C];
		newMap[cR[0]][0]=-1;
		newMap[cR[1]][0]=-1;//공기청정기 위치 파악
		
		while(!pointer.isEmpty()) {
			int [] now=pointer.poll();
			int cnt=0;//몇방향 퍼지는지 계산
			int r=now[0];
			int c=now[1];
			int side=now[2]/5;//퍼지는 곳에 먼지
			for (int d = 0; d < 4; d++) {
				int nr=r+dr[d];
				int nc=c+dc[d];
				if(!check(nr,nc)) continue;
				cnt++;
				newMap[nr][nc]+=side;
			}
			newMap[r][c]+=(now[2]-(cnt*side));
		}
		map=newMap;
	}

	private static boolean check(int r, int c) {//공기청정기 위치도 못퍼진다
		return r>=0&&r<R&&c>=0&&c<C&&map[r][c]!=-1;
	}
}
