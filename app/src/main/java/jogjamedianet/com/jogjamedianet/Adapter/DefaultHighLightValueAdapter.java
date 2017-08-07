package jogjamedianet.com.jogjamedianet.Adapter;

/**
 * Created by mery on 8/3/2017.
 */
public class DefaultHighLightValueAdapter implements IValueAdapter {

    public DefaultHighLightValueAdapter() {

    }

    @Override
    public String value2String(double value) {
        return value + "";
    }
}
