package src;

public class Vector {

    public double x;
    public double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double dot(Vector node) {
        return x * node.x + y * node.y;
    }

    public Vector normal() {
        // The normal of this vector
        return new Vector(-1 * y, x);
    }

    public Vector normalize() {
        // The unit vector of this one
        double d = Math.sqrt(x * x + y * y);
        if (d == 0) {
            d = 1;
        }
        return new Vector(x / d, y / d);
    }

    public Vector sub(Vector other) {
        return new Vector(x - other.x, y - other.y);
    }

    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }
}
