import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main_2239_스도쿠_백준 {

	static int[][] map;
	static int N;
	static ArrayList<int[]> pointer;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		map=new int[9][9];
		N=0;
		pointer=new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			char[] ch=br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				int temp=ch[j]-'0';
				map[i][j]=temp;
				if(temp==0) {
					N++; //0 위치 갯수 알기
					pointer.add(new int[] {i,j});
				}
			}
		}
		
		sudoku(0);
	}
	private static boolean sudoku(int cnt) {
		if(cnt==N) {
			StringBuilder sb=new StringBuilder();
			for (int i = 0; i <9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			
			System.out.println(sb.toString());
			return true;
		}
		int r=pointer.get(cnt)[0];
		int c=pointer.get(cnt)[1];
		for (int i = 1; i <=9; i++) {
			map[r][c]=i;
			if(check(r,c)) {
				if(sudoku(cnt+1)) return true;//check 되면 다음 진행
			}
			map[r][c]=0;
		}
		return false;
	}
	
	private static boolean check(int r, int c) {
		boolean [] col=new boolean[10]; //1~9까지
		boolean [] row=new boolean[10];
		boolean [] square=new boolean[10];
		//가로 체크
		for (int i = 0; i < 9; i++) {
			if(map[r][i]==0) continue;//0일경우는 계산 제외
			if(row[map[r][i]]) return false;
			row[map[r][i]]=true;
		}
		//세로 체크
		for (int i = 0; i < 9; i++) {
			if(map[i][c]==0) continue;//0일경우는 계산 제외
			if(col[map[i][c]]) return false;
			col[map[i][c]]=true;
		}
		//네모 구역 체크
		int sr=(r/3)*3;//시작할 위치 sr*3에서 3칸씩 탐색하면 된다
		int sc=(c/3)*3;
		for (int i = sr; i < sr+3; i++) {
			for (int j = sc; j < sc+3; j++) {
				if(map[i][j]==0) continue;//0일경우는 계산 제외
				if(square[map[i][j]]) return false;
				square[map[i][j]]=true;
			}
		}
		
		return true;
	}

}
