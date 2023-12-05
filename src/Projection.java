package src;

public class Projection {

    public double max;
    public double min;

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

    public boolean overlap(Projection other) {
        // Check if this projection overlaps with the passed one
        return (!(other.max < min || max < other.min));
    }

}
