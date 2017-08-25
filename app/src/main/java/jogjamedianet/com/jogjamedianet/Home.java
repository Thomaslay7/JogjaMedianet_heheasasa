package jogjamedianet.com.jogjamedianet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import jogjamedianet.com.jogjamedianet.Prefs.UserInfo;
public class Home extends AppCompatActivity {

    private Button report,btnlogout;
    private TextView tvUsername, tvjabatan,tvnamadepan,tvnamabelakang,tvjeniskelamin,tvnik;
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
        report         = (Button)findViewById(R.id.btnReport);
        btnlogout       =(Button) findViewById(R.id.btnLogOut);
        tvnamadepan     = (TextView)findViewById(R.id.key_namadepan);
        tvnamabelakang  = (TextView)findViewById(R.id.key_namabelakang);
        tvjeniskelamin  = (TextView)findViewById(R.id.key_jeniskelamin);
        tvjabatan       = (TextView)findViewById(R.id.key_jabatan);
        tvnik           =(TextView) findViewById(R.id.txtnik);

     //membuat variabel untuk menampung variabel text yang akan di set dengan data yang sesuai pada sharedprefence / user info

        String namaDepan = userinfo.getKeyNamaDepan();
        String namaBelakang = userinfo.getKeyNamaBelakang();
        String jabatan =userinfo.getKeyJabatan();
        String jeniskelamin = userinfo.getKeyJenisKelamin();
        String nik= userinfo.getKeyNik();
        //tvUsername.setText(username);
        tvnamadepan.setText(namaDepan);
        tvnamabelakang.setText(namaBelakang);
        tvjabatan.setText(jabatan);
        tvjeniskelamin.setText(jeniskelamin);
        tvnik.setText(nik);
        report.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          startActivity(new Intent(Home.this, Report.class));

                                          finish();
                                      }
                                  });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, login.class));
                userinfo.clearUserInfo();
                finish();
            }
        });

            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:

                                intent = new Intent(Home.this,ListPelanggan.class);
                                startActivity(intent);
                                break;

                            case R.id.action_schedules:
                                intent = new Intent(Home.this, RegistrasiPelanggan.class);
                                startActivity(intent);
                                break;
                            case R.id.action_music:
                                intent = new Intent(Home.this, Home.class);
                                startActivity(intent);
                                break;

                        }
                        return true;
                    }
                });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

