package exercise;

// BEGIN
public class Segment {
    private Point point1;
    private Point point2;

    public Segment(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getBeginPoint() {
        return point1;
    }

    public Point getEndPoint() {
        return point2;
    }

    public Point getMidPoint() {
        int diffX = Math.max(point1.getX() ,point2.getX()) -
                Math.min(point1.getX() ,point2.getX());
        int diffY = Math.max(point1.getY() ,point2.getY()) -
                Math.min(point1.getY() ,point2.getY());
        int x = Math.max(point1.getX() ,point2.getX()) - diffX / 2;
        int y = Math.max(point1.getY() ,point2.getY()) - diffY / 2;
        Point midPoint  = new Point(x, y);
        return midPoint;
    }
}
// END
