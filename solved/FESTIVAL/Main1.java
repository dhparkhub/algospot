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
            for (int j = 0; j < N; j++) {
                costs[j] = Integer.parseInt(st.nextToken());
            }

            System.out.println(minRentalCost(N, L, costs));
        }
    }

    private static double minRentalCost(int N, int L, int[] costs) {
        double minCost = Double.MAX_VALUE;
        for (int i = L; i <= N; i++) {
            for (int j = 0; j <= N - i; j++) {
                int sum = getSum(costs, j, j + i - 1);
                minCost = Math.min(minCost, (double) sum / i);
            }
        }
        return minCost;
    }

    private static int getSum(int[] costs, int from, int to) {
        int sum = 0;
        for (int i = from; i <= to; i++) {
            sum += costs[i];
        }
        return sum;
    }
}
