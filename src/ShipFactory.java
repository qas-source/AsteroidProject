package src;

public class ShipFactory {


    public Ship makeShip(int x, int y) {
        return new Ship(x, y);
    }
}
