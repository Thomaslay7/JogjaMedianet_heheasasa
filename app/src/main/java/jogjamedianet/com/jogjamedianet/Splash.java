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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View easySplashScreenView = new EasySplashScreen(Splash.this)
                .withFullScreen()
                .withSplashTimeOut(4000)
                .withTargetActivity(login.class)
                .withBackgroundResource(android.R.color.white)
                .withHeaderText("")
                .withFooterText("©JogjaMedianet 2017")
                .withBeforeLogoText("")
                .withLogo(R.drawable.tower)
                .withAfterLogoText("Selamat Datang!")
                .create();

        setContentView(easySplashScreenView);

    }
}

