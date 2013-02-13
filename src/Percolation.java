public class Percolation {
    private final byte[] cells;
    private int N;
    private WeightedQuickUnionUF uf;
    private boolean percolates;

    public Percolation(int N) {
        this.N = N;
        int size = N*N;
        cells = new byte[size];
        uf = new WeightedQuickUnionUF(size);
        for (int i = 1; i <= N; i++) {
            markConnectedToTop(idx(1, i));
            markConnectedToBottom(idx(N, i));
        }
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

    private void markOpen(int idx) {
        cells[idx] = (byte) (cells[idx] | 1);
        if (isConnectedToTop(uf.find(idx)) && isConnectedToBottom(uf.find(idx)))
            percolates = true;
    }

    private void markConnectedToTop(int idx) {
        cells[idx] = (byte) (cells[idx] | 2);
    }

    private void markConnectedToBottom(int idx) {
        cells[idx] = (byte) (cells[idx] | 4);
    }

    private boolean isConnectedToTop(int idx) {
        return (cells[idx] & 2) != 0;
    }

    private boolean isConnectedToBottom(int idx) {
        return (cells[idx] & 4) != 0;
    }

    private boolean isOpen(int idx) {
        return (cells[idx] & 1) != 0;
    }

    private void connect(int idx, int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N)
            return;
        int toIdx = idx(i, j);
        if (isOpen(toIdx)) {
            int proot = uf.find(idx);
            int proot2 = uf.find(toIdx);
            uf.union(idx, toIdx);
            int root = uf.find(idx);
            if (isConnectedToTop(proot) || isConnectedToTop(proot2)) {
                markConnectedToTop(root);
            }
            if (isConnectedToBottom(proot) || isConnectedToBottom(proot2)) {
                markConnectedToBottom(root);
            }
            if (!percolates && isConnectedToTop(root) && isConnectedToBottom(root)) {
                percolates = true;
            }
        }
    }
    public void open(int i, int j) {
        int idx = idx(i, j);
        markOpen(idx);
        connect(idx, i - 1, j);
        connect(idx, i + 1, j);
        connect(idx, i, j - 1);
        connect(idx, i, j + 1);
    }
    public boolean isOpen(int i, int j) {
        return isOpen(idx(i, j));
    }
    public boolean isFull(int i, int j) {
        return isOpen(i, j) && isConnectedToTop(uf.find(idx(i, j)));
    }
    public boolean percolates() {
        return percolates;
    }
}