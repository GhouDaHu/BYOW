package core;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equal(Point obj) {
        return (this.x == obj.x && this.y == obj.y);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
