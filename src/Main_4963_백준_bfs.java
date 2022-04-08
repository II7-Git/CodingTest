import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_4963_백준_bfs {

	static int W,H;
	static int [][] map;
	static int []dr= {-1,-1,0,1,1,1,0,-1};
	static int []dc= {0,1,1,1,0,-1,-1,-1};
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		while(true) {
			W=scann.nextInt();
			H=scann.nextInt();
			if(W==0&&H==0)break;
			map= new int[H][W];
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					map[i][j]=scann.nextInt();
				}
			}
			int cnt=0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if(map[i][j]==1) {
						cnt++;
						bfs(i,j,cnt+1);//(i,j)에서 8방 이동 시작
					}
				}
			}
			System.out.println(cnt);
		}
	}
	//1로 연결된 지점 모두 연결->1이면 가
	//2group->3group...
	private static void bfs(int r, int c, int g) {
		Queue<int[]> que=new LinkedList<>();
		que.offer(new int[]{r,c});
		while(!que.isEmpty()) {
			int [] point=que.poll();
			map[point[0]][point[1]]=g;
			for (int d = 0; d < 8; d++) {
				int nr=point[0]+dr[d];
				int nc=point[1]+dc[d];
				if(!check(nr,nc)) continue;
				
				if(map[nr][nc]==1) {
					map[nr][nc]=g;
					que.offer(new int[]{nr,nc});
				}
			}
		}
	}
	private static boolean check(int r, int c) {
		return r>=0&&r<H&&c>=0&&c<W;
	}
}
