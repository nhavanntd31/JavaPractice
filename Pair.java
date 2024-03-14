public class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getX() {
        return first;
    }

    public B getY() {
        return second;
    }
    public void plus(Pair<A,B> value){

    }

    public void setX(A first) {
        this.first = first;
    }
    public void setXY(A first, B second) {
        this.first = first;
        this.second = second;

    }


    public void setY(B second) {
        this.second = second;
    }
}
