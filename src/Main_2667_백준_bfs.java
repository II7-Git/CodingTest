import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2667_백준_bfs {

	static int N;
	static int [][]map;
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	static ArrayList<Integer> groups;
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		for (int i = 0; i < N; i++) {
			char [] ch=br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j]=ch[j]-'0';
			} 
		}
		groups=new ArrayList<>();//그룹 새로 생길 때 추가시키고 그룹내에서 집 하나 추가되면 더하기 하자
		int group=2;//1은 집을 뜻하니 그룹은 2부터 시작해보자 
		//int index=0;//groups에서 index를 통해 가져오자
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==1) {
					groups.add(0);//그룹 단지 생성 //index== group-2
					bfs(i,j,group);
					group++;
				}
			}
		}
		Collections.sort(groups);
		StringBuilder sb=new StringBuilder();
		sb.append(groups.size()).append("\n");
		for(int num:groups) {
			sb.append(num).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static void bfs(int r, int c, int g) {
		Queue<int [] > que=new LinkedList<>();
		que.offer(new int[]{r,c});
		map[r][c]=g;
		groups.set(g-2, groups.get(g-2)+1);
		while(!que.isEmpty()) {
			int [] point=que.poll();
			for (int d = 0; d < 4; d++) {
				int nr=point[0]+dr[d];
				int nc=point[1]+dc[d];
				if(!check(nr,nc)) continue;
				
				if(map[nr][nc]==1) {
					que.offer(new int[]{nr,nc});
					map[nr][nc]=g;
					groups.set(g-2, groups.get(g-2)+1);
				}
			}
		}
	}
	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<N;
	}

}
