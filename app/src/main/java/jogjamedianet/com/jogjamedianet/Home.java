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

import java.util.HashMap;

import jogjamedianet.com.jogjamedianet.Preferences.SessionManager;
import jogjamedianet.com.jogjamedianet.Prefs.UserInfo;
import jogjamedianet.com.jogjamedianet.Prefs.UserSession;

public class Home extends AppCompatActivity {

    TextView txt_id, txt_username;
    String id, username;
    SessionManager sessions;
    private Button logout;
    private TextView tvUsername, tvjabatan,tvnamadepan,tvnamabelakang,tvjeniskelamin;

    private UserSession userSession;

    private UserInfo userinfo;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userinfo = new UserInfo(getApplicationContext());

        userinfo        = new UserInfo(this);
        userSession     = new UserSession(this);
        logout          = (Button)findViewById(R.id.btnReport);
        tvnamadepan         = (TextView)findViewById(R.id.key_namadepan);
        tvnamabelakang         = (TextView)findViewById(R.id.key_namabelakang);
        tvjeniskelamin         = (TextView)findViewById(R.id.key_jeniskelamin);
        tvjabatan         = (TextView)findViewById(R.id.key_jabatan);


/*        if(!userSession.isUserLoggedin()){
           startActivity(new Intent(this, login.class));
            finish();
        }
*/
        HashMap<String, String> user = userinfo.getUserDetails();

      // String username = userinfo.getKeyUsername();
        String namaDepan = userinfo.getKeyNamaDepan();
        String namaBelakang = userinfo.getKeyNamaBelakang();
        String jabatan =userinfo.getKeyJabatan();
        String jeniskelamin = userinfo.getKeyJenisKelamin();

        //tvUsername.setText(username);
        tvnamadepan.setText(namaDepan);
        tvnamabelakang.setText(namaBelakang);
        tvjabatan.setText(jabatan);
        tvjeniskelamin.setText(jeniskelamin);

        logout.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          startActivity(new Intent(Home.this, Report.class));
                                          finish();
                                      }
                                  });

         //   id = getIntent().getStringExtra(TAG_ID);
           // username = getIntent().getStringExtra(TAG_USERNAME);

            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

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
                                intent = new Intent(Home.this, ListPelanggan.class);
                                startActivity(intent);
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

                userinfo.setLoggin(false);
                userinfo.clearUserInfo();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

