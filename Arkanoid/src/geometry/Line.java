package geometry;
import java.awt.geom.Line2D;


/**
 * @author Yossi Silberhaft and Binyamin Greenberg
 *
 * This Class will create an object Line using
 * two point objects.
 */
public class Line {
    /**
     * Create two points.
     */
    private Point p1;
    private Point p2;
    /**
     * Create our constructors for the lines.
     *
     * @param start - Will be the starting point of the line.
     * @param end - Will be the end point of the line.
     */
    public Line(final Point start, final Point end) {
        p1 = start;
        p2 = end;
    }

    /**
     * @param x1 - The x value of point p1.
     * @param y1 - The y value of point p1.
     * @param x2 - The x value of point p2.
     * @param y2 - The y value of point p2.
     */
    public Line(final double x1,
            final double y1, final double x2, final double y2) {
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
    }
    /**
     * This method will return the length of the line.
     *
     * @return the length of the line.
     */
    public final double length() {
        return p1.distance(p2);
    }
    /**
     * This method will calculate the Mid-Point of the line.
     *
     * @return the mid-point of the line.
     */
    public final Point middle() {
        return new Point((p1.getX() + p2.getX()) / 2,
                (p1.getY() + p2.getY()) / 2);
    }
    /**
     * This method will return the starting point of the line.
     * @return starting point of the line.
     */
    public final Point start() {
        return p1;
    }
    /**
     * This method will return the end point of the line.
     *
     * @return the end point of the line.
     */
    public final Point end() {
        return p2;
    }
    /**
     * This Method will check if the given lines intersect.
     *
     * @param other - another line.
     * @return true if they intersect and false otherwise.
     */
    public final boolean isIntersecting(final Line other) {
        double x1 = p1.getX();
        double x2 = p2.getX();
        double y1 = p1.getY();
        double y2 = p2.getY();

        double x3 = other.start().getX();
        double x4 = other.end().getX();
        double y3 = other.start().getY();
        double y4 = other.end().getY();

        return Line2D.linesIntersect(x1, y1, x2, y2,
                x3, y3, x4, y4);


    }

    /**
     * This method will calculate the intersection point.
     *
     * @param other - another Line.
     * @return the point of intersection.
     */
    public final Point intersectionWith(final Line other) {
        if (this.isIntersecting(other)) {
            double x1 = p1.getX();
            double x2 = p2.getX();
            double y1 = p1.getY();
            double y2 = p2.getY();
            double a1 = (y2 - y1);
            double b1 = (x1 - x2);
            double c1 = (a1 * x1) + (b1 * y1);

            double x3 = other.start().getX();
            double x4 = other.end().getX();
            double y3 = other.start().getY();
            double y4 = other.end().getY();
            double a2 = (y4 - y3);
            double b2 = (x3 - x4);
            double c2 = (a2 * x3) + (b2 * y3);

            double det = (a1 * b2) - (a2 * b1);
            double x = (b2 * c1 - b1 * c2) / det;
            double y = (a1 * c2 - a2 * c1) / det;

            Point middlePoint = new Point(x, y);
            return middlePoint;
        }
        return null;
    }
    /**
     * This method will check if the lines are equal to each other.
     *
     * @param other - another line.
     * @return true if the lines are equal and false otherwise.
     */
    public final boolean equals(final Line other) {
        if ((other.start().equals(p1) && other.end().equals(p2))
                ||    (other.start().equals(p2)
                        && other.end().equals(p1))) {
            return true;
        }
        return false;
    }
    /**
     * This Method will check if the points are on the line.
     *
     * @param line - Gets the line to be checked.
     * @param point - Gets the Point to be checked.
     * @return true if the point is on the line
     * and false otherwise.
     */
    public static boolean checkPointOnLine(final Line line,
        final Point point) {
        double x1 = line.start().getX();
        double x2 = line.end().getX();
        double y1 = line.start().getY();
        double y2 = line.end().getY();

        if (Line2D.ptSegDist(x1, y1, x2, y2,
                point.getX(), point.getY()) < 0.001) {
            return true;
        }
        return false;
    }

    /**
     * This will find the closest intersection
     * to the start of the line.
     * @param rect is the rectangle received.
     * @return the closest intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        } else {
            Point closest = rect.intersectionPoints(this).get(0);
            for (int i = 0; i < rect.intersectionPoints(this).size(); i++) {
                if (this.start().distance(rect.intersectionPoints(this)
                        .get(i))
                        < this.start().distance(closest)) {
                    closest = rect.intersectionPoints(this).get(i);
                }
            }
            return closest;
        }

    }
}