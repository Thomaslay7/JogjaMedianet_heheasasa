package jogjamedianet.com.jogjamedianet.Prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by mery on 7/13/2017.
 */
public class UserSession {
    private static final String TAG = UserSession.class.getSimpleName();
    private static final String PREF_NAME = "login";
    private static final String KEY_IS_LOGGED_IN = "isLoggedin";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;
    int PRIVATE_MODE = 0;
    public UserSession(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = prefs.edit();
    }

    public void setLoggin(boolean isLoggedin){
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedin);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedin(){return prefs.getBoolean(KEY_IS_LOGGED_IN, false);}
}
