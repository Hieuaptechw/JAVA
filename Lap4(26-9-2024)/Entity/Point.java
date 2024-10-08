package Entity;

public class Point {
    private double x;
    private double y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public double disance(Point p){
        return Math.sqrt(Math.pow(this.x-p.x, 2) + Math.pow(this.y-p.y,2));
    }
    @Override
    public String toString() {

        return String.format("| %-5.2f | %-5.2f |", x, y);
    }
}
