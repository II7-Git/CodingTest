import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main_16236_아기상어_백준_교수님 {

	static class Shark{
		int r;
		int c;
		int size=2;
		int qunt; //먹은 물고기 수
		int time;
		public Shark(int r, int c, int size, int qunt, int time) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			if(this.size==qunt) {
				this.size++;
				this.qunt=0;
			}
			else {
				this.qunt = qunt;
			}
			this.time = time;
		}
		public Shark() {
		}
	}
	
	static class Fish implements Comparable<Fish>{

		int r;
		int c;
		int size;
		int d; //상어와의  거리 나중에 계산해서 채워넣을 것 ->상어가 먹을 수 있는  물고기를 찾고 거리순 위 하
		
		public Fish(int r, int c, int size, int d) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.d = d;
		}

		public Fish() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public int compareTo(Fish f) {
			int rr=Integer.compare(this.d, f.d);
			if(rr==0) {
				int u=Integer.compare(this.r, f.r);//위
				if(u==0) {
					return Integer.compare(this.c, f.c);//왼쪽
				}
				else {
					return u;
				}
			}else return rr;//거리가 1 순위
		}
	}
	
	static int N,M;
	static int[][] map;
	static Shark shark;
	static int cnt;
	static List<Fish> fishq=new ArrayList<>();
	static List<Fish> ff=new ArrayList<>();
	
	public static void main(String[] args) {//bfs
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		M=0;
		map=new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j]=scann.nextInt();
				
				if(map[i][j]==9) {//아기 상어의 위치
					shark=new Shark(i, j, 2, 0, 0);
				}
			}
		}
		//배열 구성 완료
		cnt=0;
		dfs();
		System.out.println(cnt);
	}
	private static void dfs() {
		fishq.clear();//물고기 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]>0 && map[i][j]<7) {
					fishq.add(new Fish(i, j, map[i][j], 0));
				}
			}
		}
		ff.clear();
		for (Fish sfish : fishq) {
			if(sfish.size<shark.size) {
				/*int kk=distance(shark.r , shark.c , sfish.r, sfish.c, shark.size);//bfs
				if(kk!=-1) {
					sfish.d=kk; //상어와의 거리
					ff.add(sfish);
				}*/
			}
		}
	}
	
}
