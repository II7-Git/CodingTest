import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_16926_배열돌리기_백준 {
	static int N,M,R;
	static int [][]	map;
	static int cir;
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[] str=br.readLine().split(" ");
		N=Integer.parseInt(str[0]);
		M=Integer.parseInt(str[1]);
		R=Integer.parseInt(str[2]);
		map=new int [N][M];
		cir=Math.min(N, M)/2;//이루는 원형 갯수
		for (int i = 0; i < N; i++) {
			String[] st=br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st[j]);
			}
		}//배열 읽기 완료
		Queue<Integer> [] ques=new LinkedList [cir];
		for (int i = 0; i <cir; i++) {
			ques[i]=new LinkedList<>();
		}
		for (int i = 0; i <cir; i++) {//왼쪽 구석부터 큐에 적립
			for (int j = i; j <M-i; j++) {//위쪽 큐 삽입
				ques[i].offer(map[i][j]);
			}
			for (int j = i+1; j <N-i; j++) {//오른쪽
				ques[i].offer(map[j][M-1-i]);
			}
			for (int j =M-i-2; j >=i; j--) {//위쪽 큐 삽입
				ques[i].offer(map[N-1-i][j]);
			}
			for (int j = N-i-2; j >=i+1; j--) {//오른쪽
				ques[i].offer(map[j][i]);
			}
		}
		for (int i = 0; i < R; i++) {//회전
			for (int j = 0; j < cir; j++) {
				ques[j].offer(ques[j].poll());
			}
		}
		
		for (int i = 0; i <cir; i++) {//큐에 내용을 다시 적
			for (int j = i; j <M-i; j++) {//위쪽 큐 삽입
				map[i][j]=ques[i].poll();
			}
			for (int j = i+1; j <N-i; j++) {//오른쪽
				map[j][M-i-1]=ques[i].poll();
			}
			for (int j =M-i-2; j >=i; j--) {//위쪽 큐 삽입
				map[N-i-1][j]=ques[i].poll();
			}
			for (int j = N-i-2; j >=i+1; j--) {//오른쪽
				map[j][i]=ques[i].poll();
			}
		}
		
		for (int i = 0; i < N; i++) {
			StringBuilder sb=new StringBuilder();
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]).append(" ");
			}
			System.out.println(sb.toString());
		}
	}

}
