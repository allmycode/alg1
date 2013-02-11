public class Main {
    public static void main(String[] args) {
        for (int k = 0; k < 10; k++) {
            Percolation p = new Percolation(1000);
            for (int i = 1; i <= 1000; i++) {
                for (int j = 1; j <= 1000; j++) {
                    if (StdRandom.random() < 0.593) {
                        p.open(i, j);
                        if (p.percolates()) {
                            break;
                        }
                    }
                }
                if (p.percolates()) {
                    break;
                }
            }
            end:
            if (p.percolates())
                System.out.println("percolates");
            else
                System.out.println("not percolates");
        }
    }
}
