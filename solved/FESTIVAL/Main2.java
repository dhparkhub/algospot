import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] costs = new int[N];
            int[] costsSum = new int[N];
            for (int j = 0; j < N; j++) {
                costs[j] = Integer.parseInt(st.nextToken());
                costsSum[j] = (j == 0) ? costs[j] : costsSum[j - 1] + costs[j];
            }

            System.out.println(minRentalCost(N, L, costsSum));
        }
    }

    private static double minRentalCost(int N, int L, int[] costsSum) {
        double minCost = Double.MAX_VALUE;
        for (int i = L; i <= N; i++) {
            for (int j = 0; j <= N - i; j++) {
                int sum = (j == 0) ? costsSum[i - 1] : costsSum[i + j - 1] - costsSum[j - 1];
                minCost = Math.min(minCost, (double) sum / i);
            }
        }
        return minCost;
    }
}
