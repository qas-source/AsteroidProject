package src;

/**
 * Projection used for collision
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class Projection {

    public double max;
    public double min;

    /**
     * Generates projection based on vector and an axis
     * @param object, Vector that is projected
     * @param axis, Axis that is projected on to
     */
    public Projection(Collidable object, Vector axis){
        Vector[] vertices = object.getVertices();
        min = axis.dot(vertices[0]);
        max = min;

        for (Vector vertex: vertices) {
            double p = axis.dot(vertex);

            if (p < min){
                min = p;
            } else if (p > max) {
                max = p;
            }
        }
    }

    /**
     * Checks if overlaps with other projections
     * @param other, other projection
     * @return true is overlap
     */
    public boolean overlap(Projection other) {
        // Check if this projections overlaps
        return (!(other.max < min || max < other.min));
    }

}
