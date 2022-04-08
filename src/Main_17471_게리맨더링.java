import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_17471_게리맨더링 {

	static int N;
	static int [] pop;//인구수 저장
	static int min;
	static ArrayList<ArrayList<Integer>> graph;
	public static void main(String[] args) {
		//구역을 서브셋으로 나눈 후 bfs등을 써서 모든 구역이 연결됐는지 확인
		//두 구역간의 격차 구하기
		Scanner scann=new Scanner(System.in);
		
		N=scann.nextInt();
		//0칸은 비워놓자
		//인구수
		pop=new int[N+1];
		for (int i = 1; i <=N; i++) {
			pop[i]=scann.nextInt();
		}
		
		//그래프 생성
		graph=new ArrayList<>();
		for (int i = 0; i <=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		
		//그래프 연결
		for (int i = 1; i <=N; i++) {
			int n=scann.nextInt();
			for (int j = 0; j < n; j++) {
				graph.get(i).add(scann.nextInt());
			}
		}
		min=Integer.MAX_VALUE;
		subset(0,new boolean[N+1]);
		
		System.out.println((min==Integer.MAX_VALUE)?-1:min);
	}
	private static void subset(int cnt,boolean[] v) {
		if(cnt==N) {
			boolean isConnect=checkConnect(v);
			
			if(!isConnect) return;
			//연결되었다면 인구수 비교
			int a=0;
			int b=0;
			for (int i =1; i <=N; i++) {
				if(v[i]) a+=pop[i];
				else b+=pop[i];
			}

			min=Math.min(min, Math.abs(a-b));
			return;
		}
		
		v[cnt+1]=true;
		subset(cnt+1, v);
		v[cnt+1]=false;
		subset(cnt+1,v);
	}
	private static boolean checkConnect(boolean[] v) {//각각이 연결되어있는지 검사
		
		boolean[] visted=new boolean[N+1];
		int startA=-1;
		int startB=-1;
		
		for (int i = 1; i <=N; i++) {
			if(startA!=-1&&startB!=-1) break;
			
			if(v[i]) startA=i;
			else startB=i;
		}
		
		if(startA==-1||startB==-1) return false;//한쪽으로 마을이 쏠려있다
		
		Queue<Integer> que=new LinkedList<Integer>();
		
		//A마을 검사
		que.offer(startA);
		visted[startA]=true;
		while (!que.isEmpty()) {
			int temp=que.poll();
			
			for (int i = 0; i < graph.get(temp).size(); i++) {
				int now=graph.get(temp).get(i);
				if(v[now] && !visted[now]) {
					visted[now]=true;
					que.offer(now);
				}
			}
		}
		//B마을 검사
		que.clear();
		que.offer(startB);
		visted[startB]=true;
		while (!que.isEmpty()) {
			int temp=que.poll();
			
			for (int i = 0; i < graph.get(temp).size(); i++) {
				int now=graph.get(temp).get(i);
				if(!v[now] && !visted[now]) {
					visted[now]=true;
					que.offer(now);
				}
			}
		}
		//모든 마을 검사됐는지 확인
		
		for (int i = 1; i <= N; i++) {
			if(!visted[i]) return false;
		}
		
		return true;
	}
}
