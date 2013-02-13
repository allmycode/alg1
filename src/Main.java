public class Main {
    public static void main(String[] args) {
        Percolation p = new Percolation(1);
        dump(p, 1);
        p.open(1, 1);
        dump(p, 1);
        PercolationStats s = new PercolationStats(200, 10);
        System.out.println(s.stddev());
    }

    private static void dump(Percolation p, int N) {
        StdOut.println(p.percolates());
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                StdOut.print(p.isOpen(i, j) ? (p.isFull(i, j) ? "#" : "+") : "0");
            }
            StdOut.println();
        }
    }
}
