import java.util.Scanner;

public class Main_3040_백설공주와일곱난쟁이_백준 {

	static int [] dwarf;
	static boolean [] v;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		dwarf=new int[9];
		v=new boolean[9];
		for (int i = 0; i < dwarf.length; i++) {
			dwarf[i]=scann.nextInt();
		}
		combiDwarf(0,0,0);
	}
	
	private static void combiDwarf(int cnt, int start, int total) {
		if(cnt==7) {
			if(total==100) {
				for (int i = 0; i < dwarf.length; i++) {
					if(v[i]) System.out.println(dwarf[i]);
				}
			}
			return;
		}
		
		for (int i = start; i < dwarf.length; i++) {
			v[i]=true;
			combiDwarf(cnt+1, i+1, total+dwarf[i]);
			v[i]=false;
		}
	}
}
