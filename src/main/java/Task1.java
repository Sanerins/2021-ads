import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[][] arr = new int[3][n];
        for (int i = 0; i < n; i++) {
            arr[0][i] = in.nextInt();
            arr[2][i] = -1;
        }
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[0][i] >= arr[0][j] && max < arr[1][j]) {
                    max = arr[1][j];
                    arr[2][i] = j;
                }
            }
            arr[1][i] = max + 1;
        }
        int max = 0;
        int iMax = -1;
        for (int i = 0; i < n; i++) {
            if (arr[1][i] > max) {
                max = arr[1][i];
                iMax = i;
            }
        }
        LinkedList<Integer> sequence = new LinkedList<>();
        while (iMax != -1) {
            sequence.addFirst(arr[0][iMax]);
            iMax = arr[2][iMax];
        }
        while (!sequence.isEmpty()) {
            out.print(sequence.removeFirst() + " ");
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
