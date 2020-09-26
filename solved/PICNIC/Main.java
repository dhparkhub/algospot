import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static class Solution {
        private final int n;
        private final boolean[][] friends;
        private final boolean[] visited = new boolean[10];

        Solution(int n, boolean[][] friends) {
            this.n = n;
            this.friends = friends;
        }

        private int solve() {
            return maxRelation(0, 0);
        }

        private int maxRelation(int i, int c) {
            if (c == n) return 1;
            int ret = 0;
            for (int j = i + 1; j < n; j++) {
                if (visited[j]) continue;
                if (!friends[i][j]) continue;
                if (c == n - 2) return 1;
                visited[i] = visited[j] = true;
                for (int k = i + 1; k < n; k++) {
                    if (visited[k]) continue;
                    ret += maxRelation(k, c + 2);
                }
                visited[i] = visited[j] = false;
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
