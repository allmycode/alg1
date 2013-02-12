public class Main {
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(2,3);
        p.open(2,2);
        p.open(3,2);
        p.open(3,1);
        assert p.isFull(3,1) == false;

    }
}
