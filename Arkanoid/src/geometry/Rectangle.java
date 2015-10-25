package geometry;
import java.util.ArrayList;

/**
 * This Object will create rectangles to be displayed
 * on the screen.
 *
 * @author Yossi Silberhaft and Binyamin Greenberg.
 */

public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line top;
    private Line bottom;
    private Line left;
    private Line right;
    private java.awt.Color color;

    /**
     * Creates a new Rectangle with its corner, width and height.
     * @param upperLeft corner Coordinate of the rectangle.
     * @param width of the rectangle.
     * @param height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
    this.upperLeft = upperLeft;
    this.width = width;
    this.height = height;
    left  = new Line(this.getUpperLeft(),
       new Point(this.getUpperLeft().getX(),
        this.getUpperLeft().getY() + this.getHeight()));
    top = new Line(this.getUpperLeft(),
         new Point(this.getUpperLeft().getX()
             + this.getWidth(),
               this.getUpperLeft().getY()));

    bottom = new Line(left.end(), new Point(left.end().getX()
            + this.getWidth(), left.end().getY()));

    right = new Line(top.end(), bottom.end());
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with a specific line.
     * @param line to be checked against.
     * @return the list of intersection Points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {

    ArrayList<Point> listofIntersection = new ArrayList<Point>();
    if (bottom.isIntersecting(line)) {
        listofIntersection.add(bottom.intersectionWith(line));
    }
    if (top.isIntersecting(line)) {
        listofIntersection.add(top.intersectionWith(line));
    }
    if (left.isIntersecting(line)) {
        listofIntersection.add(left.intersectionWith(line));
    }
    if (right.isIntersecting(line)) {
        listofIntersection.add(right.intersectionWith(line));
    }
    return listofIntersection;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
    return width;
    }
    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
    return height;
    }
    /**
     * @return the Top of the line.
     */
    public Line getTop() {
    return top;
    }
    /**
     * @return the Bottom of the line.
     */
    public Line getBottom() {
    return bottom;
    }
    /**
     * @return the upper Left point of the rectangle.
     */
    public Point getUpperLeft() {
    return upperLeft;
    }
}
