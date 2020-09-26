import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static class Solution {
        private final int[] mr = {-1, -1, 0, 1, 1, 1, 0, -1};
        private final int[] mc = {0, 1, 1, 1, 0, -1, -1, -1};
        private final char[][] map;
        private final boolean[][][] visited = new boolean[5][5][10];
        private String word;

        Solution(char[][] map) {
            this.map = map;
        }

        private boolean hasWord(String word) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    Arrays.fill(visited[i][j], false);
                }
            }

            this.word = word;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (findWord(i, j, 0)) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean findWord(int r, int c, int idx) {
            visited[r][c][idx] = true;
            if (map[r][c] != word.charAt(idx)) return false;
            if (idx == word.length() - 1) return true;
            for (int i = 0; i < 8; i++) {
                int nr = r + mr[i];
                int nc = c + mc[i];
                if (!isValid(nr, nc)) continue;
                if (visited[nr][nc][idx + 1]) continue;
                if (findWord(nr, nc, idx + 1)) {
                    return true;
                }
            }

            return false;
        }

        private boolean isValid(int r, int c) {
            return r >= 0 && r < 5 && c >= 0 && c < 5;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[5][5];
        for (int i = 0; i < C; i++) {
            for (int r = 0; r < 5; r++) {
                st = new StringTokenizer(br.readLine());
                String temp = st.nextToken();
                for (int c = 0; c < 5; c++) {
                    map[r][c] = temp.charAt(c);
                }
            }

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            List<String> words = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                words.add(st.nextToken());
            }

            Solution solution = new Solution(map);
            StringBuilder output = new StringBuilder();
            for (String word : words) {
                boolean flag = solution.hasWord(word);
                output.append(word).append(' ').append(flag ? "YES" : "NO").append('\n');
            }

            System.out.print(output.toString());
        }
    }
}
