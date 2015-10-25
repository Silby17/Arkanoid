package geometry;
/**
 * @author Yossi Silberhaft and Binyamin Greenberg.
 * This Class is to create the Object Point.
 */

public class Point {
    /**
     * Creates the x value of the point.
     */
    private double x;
    /**
     * Creates the y value of the point.
     */
    private double y;

    /**
     * @param x - Will assign the x value to the constructor.
     * @param y - Will assign the y value to the constructor.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This Method will calculate the Distance.
     *
     * @param other - is another point.
     * @return the Distance.
     */
    public double distance(Point other) {
        double x1 = other.getX();
        double y1 = other.getY();
        double distance = Math.sqrt(((x1 - x) * (x1 - x))
                + ((y1 - y) * (y1 - y)));
        return distance;
    }

    /**
     * This Method will check to see if the Points are equal.
     *
     * @param other - another point.
     * @return true if the points are equal and false otherwise.
     */
    public boolean equals(Point other) {
        if (other.getX() == x && other.getY() == y) {
            return true;
        }
        return false;
    }

    /**
     * This Method will return the x value of the point.
     *
     * @return the x value of the point.
     */
    public double getX() {
        return x;
    }

    /**
     * This Method will return the y value of the point.
     *
     * @return the y value of the point.
     */
    public double getY() {
        return y;
    }

    /**
     * Method that will find the mid point of the line.
     *
     * @param p is the point.
     * @return the mid point.
     */
    public Point midpoint(Point p) {
        double ex = (p.getX() + x) / 2;
        double wy = (p.getY() + y) / 2;
        return new Point(ex, wy);
    }
}