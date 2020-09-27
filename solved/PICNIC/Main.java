import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static class Solution {
        private final int n;
        private final boolean[][] friends;
        private final boolean[] taken = new boolean[10];

        Solution(int n, boolean[][] friends) {
            this.n = n;
            this.friends = friends;
        }

        private int solve() {
            return maxRelation();
        }

        private int maxRelation() {
            int firstFree = -1;
            for (int i = 0; i < n; i++) {
                if (!taken[i]) {
                    firstFree = i;
                    break;
                }
            }

            if (firstFree == -1) return 1;

            int ret = 0;
            for (int i = firstFree + 1; i < n; i++) {
                if (!taken[i] && friends[firstFree][i]) {
                    taken[firstFree] = taken[i] = true;
                    ret += solve();
                    taken[firstFree] = taken[i] = false;
                }
            }

            return ret;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            boolean[][] friends = new boolean[n][n];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                friends[x][y] = friends[y][x] = true;
            }

            Solution solution = new Solution(n, friends);
            output.append(solution.solve()).append('\n');
        }

        System.out.print(output.toString());
    }
}
