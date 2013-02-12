public class Percolation {
    private final boolean[] opens;
    private int N;
    private int inputIdx;
    private int outputIdx;
    private WeightedQuickUnionUF uf;
    public Percolation(int N) {
        this.N = N;
        int size = N*N;
        opens = new boolean[size];
        inputIdx = size;
        outputIdx = size+1;
        uf = new WeightedQuickUnionUF(size + 2);
    }

    private int idx(int i, int j) {
        if (i <= 0 || i > N) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
        if (j <= 0 || j > N) {
            throw new IndexOutOfBoundsException("row index j out of bounds");
        }
        return (i-1)*N + (j-1);
    }

    private void connect(int idx, int i, int j) {
        if (isOpen(i, j))
            uf.union(idx, idx(i, j));
    }
    public void open(int i, int j) {
        int idx = idx(i, j);
        opens[idx] = true;

        if (i > 1)
            connect(idx, i - 1, j);
        else
            uf.union(idx, inputIdx);

        if (i < N)
            connect(idx, i + 1, j);
        else
            uf.union(idx, outputIdx);

        if (j > 1)
            connect(idx, i, j - 1);

        if (j < N)
            connect(idx, i, j + 1);
    }
    public boolean isOpen(int i, int j) {
        return opens[idx(i, j)];
    }
    public boolean isFull(int i, int j) {
        return uf.connected(inputIdx, idx(i, j));
    }
    public boolean percolates() {
        return uf.connected(inputIdx, outputIdx);
    }
}