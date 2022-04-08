import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main_1759_암호만들기_백준 {

	//4 6 이면 6 C 4 한뒤 조건 맞는지 검색 후 정렬만 하자
	static int L,C;
	static char [] cha;
	static char [] choice;
	static StringBuilder sb;
	static ArrayList<String> str= new ArrayList<>();
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		L=scann.nextInt();
		C=scann.nextInt();
		cha=new char[C];
		for (int i = 0; i < C; i++) {
			cha[i]=scann.next().charAt(0);
		}
		sb=new StringBuilder();
		combi(0,0,new char[L]);
		Collections.sort(str);
		sb.setLength(0);
		for(String st:str) {
			sb.append(st).append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	private static void combi(int cnt, int start,char[] choice) {
		if(cnt==L) {
			//모음 1개 이상 자음 2개 이상인지 검사, 맞으면 정렬후 sb에 더하기
			int aCnt=0;//모음 카운팅
			int zCnt=0;//자음 카운팅
			for (char c: choice) {
				if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u') aCnt++;
				else zCnt++;
			}
			
			if(aCnt>=1&&zCnt>=2) {
				sb.setLength(0);
				char[] copy=choice.clone();//원본 손상 방지
				Arrays.sort(copy);
				for (char ch:copy) {
					sb.append(ch);
				}
				str.add(sb.toString());
			}
			return;
		}
		
		for (int i = start; i < C; i++) {
			choice[cnt]=cha[i];
			combi(cnt+1, i+1,choice);
		}
	}
}
