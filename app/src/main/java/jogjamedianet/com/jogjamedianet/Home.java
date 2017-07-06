package jogjamedianet.com.jogjamedianet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jogjamedianet.com.jogjamedianet.Preferences.SessionManager;

public class Home extends AppCompatActivity {

    Button btn_logout;
    TextView txt_id, txt_username;
    String id, username;
    SessionManager sessions;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);


        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:

                                intent = new Intent(Home.this, ListPelanggan.class);
                                startActivity(intent);
                                break;

                            case R.id.action_schedules:
                                intent = new Intent(Home.this, RegistrasiPelanggan.class);
                                startActivity(intent);
                                break;
                            case R.id.action_music:

                                break;

                        }
                        return true;
                    }
                });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_settings:
                // Code you want run when activity is clicked
                sessions.logoutUser();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

