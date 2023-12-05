package src;

/**
 * Custom Vector, included various operations
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class Vector {

    public double x;
    public double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the dot product between two vectors
     * @param other, other vector
     * @return the value of the dot product
     */
    public double dot(Vector other) {
        return x * other.x + y * other.y;
    }
    /**
     * Calculates the normal vector of a vector
     * @return the value of the normal vector
     */
    public Vector normal() {
        return new Vector(-1 * y, x);
    }

    /**
     * Calculates the normalized, length 1, vector
     * @return the normalized vector
     */
    public Vector normalize() {
        double d = Math.sqrt(x * x + y * y);
        if (d == 0) {
            d = 1;
        }
        return new Vector(x / d, y / d);
    }

    /**
     * Calculates the subtraction of two vectors
     * @param other, other vector
     * @return the difference
     */
    public Vector sub(Vector other) {
        return new Vector(x - other.x, y - other.y);
    }

    /**
     * Calculates the summation of two vectors
     * @param other, other vector
     * @return the sum
     */
    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    /**
     * Scales a vector by a constant
     * @param scalar, other vector
     * @return the scaled vector
     */
    public Vector multiply(double scalar) {
        return new Vector(this.x * scalar, this.y * scalar);
    }
}
