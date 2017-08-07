package jogjamedianet.com.jogjamedianet;

/**
 * Created by mery on 8/3/2017.
 */
public class SingleD_XY {
    private double x;
    private double y;

    private static SingleD_XY value;

    private SingleD_XY() {

    }

    public synchronized static SingleD_XY getInstance() {
        if (value == null) {
            value = new SingleD_XY();
        }
        return value;
    }

    public double getX() {
        return x;
    }

    public SingleD_XY setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public SingleD_XY setY(double y) {
        this.y = y;
        return this;
    }

    @Override
    public String toString() {
        return "U_XY  x: " + x + " y:" + y;
    }
}
