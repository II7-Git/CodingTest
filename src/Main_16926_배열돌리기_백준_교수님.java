import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_16926_배열돌리기_백준_교수님 {
	static int N,M,R;
	static int [][]	map;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[] str=br.readLine().split(" ");
		N=Integer.parseInt(str[0]);
		M=Integer.parseInt(str[1]);
		R=Integer.parseInt(str[2]);
		map=new int [N][M];
		//input
		for (int i = 0; i < N; i++) {
			String[] st=br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st[j]);
			}
		}//배열 읽기 완료
		//로직 구현
		int K=Math.min(N, M)/2;//겹 구하기
		for (int r = 0; r < R; r++) {//회전 수
			for(int cnt=0;cnt<K;cnt++) { // cnt겹
				int temp=map[cnt][cnt];
				//이동+스왑-> 오른쪽으로 이동
				for (int i = cnt; i < M-cnt; i++) {
					if(i+1==M-cnt) break;
					map[cnt][i]=map[cnt][i+1];
				}
				
				//이동+스왑->아래로 이동
				for (int i = cnt; i < N-cnt; i++) {
					if(i+1==N-cnt) break;
					map[i][M-cnt-1]=map[i+1][M-cnt-1];
				}
				//이동+스왑->왼쪽으로 이동
				for (int i = M-cnt-1; i > cnt; i--) {
					if(i==cnt) break;
					map[N-cnt-1][i]=map[N-cnt-1][i+1];
				}
				
				//j,j-> j+1,j 이동
				map[cnt+1][cnt]=temp;
			}
		}
	}

}
