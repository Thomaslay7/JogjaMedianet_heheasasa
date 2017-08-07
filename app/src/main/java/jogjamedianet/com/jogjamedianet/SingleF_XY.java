package jogjamedianet.com.jogjamedianet;

/**
 * Created by mery on 8/3/2017.
 */
public class SingleF_XY {
    private float x;
    private float y;

    private static SingleF_XY value;

    private SingleF_XY() {

    }

    public synchronized static SingleF_XY getInstance() {
        if (value == null) {
            value = new SingleF_XY();
        }
        return value;
    }

    public float getX() {
        return x;
    }

    public SingleF_XY setX(float x) {
        this.x = x;
        return this;
    }

    public float getY() {
        return y;
    }

    public SingleF_XY setY(float y) {
        this.y = y;
        return this;
    }

    @Override
    public String toString() {
        return "U_XY  x: " + x + " y:" + y;
    }
}
