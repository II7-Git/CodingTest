import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_1600_말이되고픈원숭이 {

	static int K, W, H;
	static int[][] map;
	static boolean[][][] v;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int[] hr = { -2, -2, 2, 2, 1, -1, 1, -1 };
	static int[] hc = { 1, -1, 1, -1, 2, 2, -2, -2 };

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);

		K = scann.nextInt();
		W = scann.nextInt();
		H = scann.nextInt();
		map = new int[H][W];
		v = new boolean[H][W][K + 1]; //말점프가 K번 남았을 때 도착한 위치에 중복 확인
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = scann.nextInt();
			}
		}

		Queue<Monkey> que = new LinkedList<>();
		que.offer(new Monkey(0, 0, 0, K));

		v[0][0][0] = true;
		int val = -1;
		while (!que.isEmpty()) {
			Monkey cur = que.poll();

			if (cur.r == H - 1 && cur.c == W - 1) {
				val = cur.cnt;
				break;
			}
			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;
			int k = cur.k;
			//System.out.println(r+","+c+","+cnt+","+k);
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!check(nr, nc))
					continue;

				if (map[nr][nc] == 0 && !v[nr][nc][k]) {
					v[nr][nc][k] = true;
					que.offer(new Monkey(nr, nc, cnt + 1, k));
				}
			}
			if (k != 0) {//말 이동 방법 가능
				for (int h = 0; h < 8; h++) {

					int nr = r + hr[h];
					int nc = c + hc[h];

					if (!check(nr, nc))
						continue;

					if (map[nr][nc] == 0 && !v[nr][nc][k-1]) {//말 점프 한번 쓰는 경우
						v[nr][nc][k-1] = true;
						que.offer(new Monkey(nr, nc, cnt + 1, k - 1));
					}
				}
			}
		}
		System.out.println(val);
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}

	static class Monkey {
		int r;
		int c;
		int cnt;
		int k;

		public Monkey(int r, int c, int cnt, int k) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.k = k;
		}

	}
}
