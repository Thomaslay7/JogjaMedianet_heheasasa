package jogjamedianet.com.jogjamedianet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;
import jogjamedianet.com.jogjamedianet.Prefs.UserInfo;

/**
 * Created by mery on 7/21/2017.
 */
public class Splash extends AppCompatActivity {
    private UserInfo sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sessions = new UserInfo(this);

        View easySplashScreenView = new EasySplashScreen(Splash.this)
                .withFullScreen()
                // .withSplashTimeOut(4000)
                .withBackgroundResource(android.R.color.white)
                .withHeaderText("")
                .withFooterText("©JogjaMedianet 2017")
                .withBeforeLogoText("")
                .withLogo(R.drawable.tower)
                .withAfterLogoText("Selamat Datang!")
                .create();

        setContentView(easySplashScreenView);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                if (!sessions.isLoggedin()) {
                    Intent i = new Intent(Splash.this, login.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(Splash.this, Home.class);
                    startActivity(i);
                }

            }
        }, 0);
    }
}
