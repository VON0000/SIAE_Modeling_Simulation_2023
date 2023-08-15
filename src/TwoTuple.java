public class TwoTuple<X, Y> {
    public final X x;
    public final Y y;
    public TwoTuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
    public X getLeft() {
		return x;
	}

	public Y getRight() {
		return y;
	}
}
