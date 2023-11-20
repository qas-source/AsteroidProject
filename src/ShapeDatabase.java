package src;

/**
 * This is a singleton
 */


public final class ShapeDatabase {

    public static ShapeDatabase instance;

    public ShapeDatabase(){
        System.out.println("SignletonMade");
    }

    public static ShapeDatabase getShapes(){
        if (instance == null) {
            instance = new ShapeDatabase();
        }
        return instance;
    }

}
