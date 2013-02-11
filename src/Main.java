public class Main {
    public static void main(String[] args) {
        int N = 2;
        Percolation p = new Percolation(N);
        int c = 0;
        while (!p.percolates()) {
            int i = StdRandom.uniform(1, N+1);
            int j = StdRandom.uniform(1, N+1);
            if (!p.isOpen(i, j)) {
                p.open(i, j);
                c++;
            }
        }
        System.out.println(c);
    }
}
