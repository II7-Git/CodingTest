import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767_프로세서연결 {

	static int T, N;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int min;
	static int cntCore;
	static boolean[] v;
	static ArrayList<int[]> list;
	static int size;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];

			list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (!(i == 0 || i == N - 1 || j == 0 || j == N - 1) && map[i][j] == 1) {
						list.add(new int[] { i, j });
					}
				}
			}
			size = list.size();
			// System.out.println(size);
			// 맵 그리기 완료
			v = new boolean[size];
			cntCore = -1;
			min = Integer.MAX_VALUE;
			dfs(0, 0, 0);// cnt,length,connectCore

			System.out.println("#" + t + " " + min);
		}
	}

	private static void dfs(int cnt, int len, int cCnt) {// 서순이 중요하지 않다... 차피 제외하는 경우도 체크하기에
		if (cnt == size) {
			if (cCnt > cntCore) {
				cntCore = cCnt;
				min = len;
				// System.out.println("len : "+len+" cnt : "+cCnt);
			} else if (cCnt == cntCore) {
				min = Math.min(min, len);
				// System.out.println("len : "+len+" cnt : "+cCnt);
			}
			return;
		}
		if (cntCore - cCnt > size - cnt)
			return;

		int r = list.get(cnt)[0];
		int c = list.get(cnt)[1];

		for (int d = 0; d < 5; d++) {
			if (d == 4) {// 아무데도 연결안하고 넘김
				dfs(cnt + 1, len, cCnt);
				continue;
			}

			boolean canConnect = true;
			int end = 0;
			if (d == 0) {
				end = r;
			} else if (d == 1) {
				end = N - 1 - c;
			} else if (d == 2) {
				end = N - 1 - r;
			} else if (d == 3) {
				end = c;
			}

			int nr = r + dr[d];
			int nc = c + dc[d];
			for (int j = 0; j < end; j++) {
				if (map[nr][nc] != 0) {
					canConnect = false;
					break;
				}
				nr = nr + dr[d];
				nc = nc + dc[d];
			}

			if (canConnect) {// 연결 가능
				nr = r + dr[d];
				nc = c + dc[d];
				for (int j = 0; j < end; j++) {
					map[nr][nc] = 2;
					nr = nr + dr[d];
					nc = nc + dc[d];
				}
				dfs(cnt + 1, len + end, cCnt + 1);
				nr = r + dr[d];
				nc = c + dc[d];
				for (int j = 0; j < end; j++) {
					map[nr][nc] = 0;
					nr = nr + dr[d];
					nc = nc + dc[d];
				}
			}
		}

	}
}
