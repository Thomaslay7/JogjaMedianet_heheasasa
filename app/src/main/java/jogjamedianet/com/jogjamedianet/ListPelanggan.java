package jogjamedianet.com.jogjamedianet;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;

import jogjamedianet.com.jogjamedianet.Adapter.AdapterList;
import jogjamedianet.com.jogjamedianet.App.AppController;
import jogjamedianet.com.jogjamedianet.Prefs.UserInfo;
import jogjamedianet.com.jogjamedianet.Util.Server;

public class ListPelanggan extends AppCompatActivity {

    private static final String TAG = ListPelanggan.class.getSimpleName();
    ProgressDialog pDialog;
    private RecyclerView lvhape;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String url;
    ArrayList<HashMap<String, String>> list_data;
    private Intent intent;
    private UserInfo session;
    TextView txt;
    String tag_json_obj = "json_obj_req";
    ConnectivityManager conMgr;
    SwipeRefreshLayout swLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pelanggan);
        url = Server.URL + "ListPelanggan.php";
        lvhape = (RecyclerView) findViewById((R.id.lvhape));
        LinearLayoutManager llm = new LinearLayoutManager((this));
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lvhape.setLayoutManager(llm);
        txt=(TextView) findViewById(R.id.txt);
        requestQueue = Volley.newRequestQueue(ListPelanggan.this);


        session = new UserInfo(getApplicationContext());

        session = new UserInfo(this);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }
        String idPgw;
        idPgw = session.getKeyID().toString();

        //MENGECEK APAKAH KONEKSI INTERNET ADA ATAU TIDAK
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {

            cek(idPgw);

        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }






        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (
                        new BottomNavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.action_favorites:
                                        intent = new Intent(ListPelanggan.this, ListPelanggan.class);
                                        startActivity(intent);
                                        break;

                                    case R.id.action_schedules:
                                        intent = new Intent(ListPelanggan.this, RegistrasiPelanggan.class);
                                        startActivity(intent);
                                        break;
                                    case R.id.action_music:
                                        intent = new Intent(ListPelanggan.this, Home.class);
                                        startActivity(intent);
                                        break;

                                }
                                return true;
                            }
                        });


    }
//PROSEDUR CEK BERFUNGSI UNTUK MEMANGGIL DATA PELANGGAN BERDASARKAN ID PEGAWAI (USER)
    public void cek(final String idpeg) {

        final String namaperu, namapel, jenis,id;
        namaperu = session.getKeyNamaperusahaan();
        namapel = session.getKeyNamapelanggan();
        jenis = session.getKeyJenisusaha();
        id=session.getKeyIdPelanggan();


        list_data = new ArrayList<HashMap<String, String>>();
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Tunggu Sebentar...");
        showDialog();


        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "response " + response.toString());
                hideDialog();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                    //SEMUA DATA DITAMPILKAN DENGAN JSON ARRAY. SEDANGKAN YANG DITAMPILKAN TERGANTUNG YANG DI PUT.

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("id", json.getString("id"));
                        map.put("namaperusahaan",json.getString("namaperusahaan"));
                        map.put("jenis_usaha", json.getString("jenis_usaha"));
                        map.put("nama_pelanggan", json.getString("nama_pelanggan"));
                        map.put("ID_Pegawai", idpeg);
                       // MENAMBAH DATA YANG TELAH DI GET DIATAS PADA LIST DATA (CLASS ADAPTER ITEM)
                        list_data.add(map);
                        AdapterList adapter = new AdapterList(ListPelanggan.this, list_data);
                        lvhape.setAdapter(adapter);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {


            @Override
            protected HashMap<String, String> getParams() {
                // Posting parameters to login url
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("id",id);
                params.put("namaperusahaan", namaperu);
                params.put("jenis_usaha", jenis);
                params.put("nama_pelangan", namapel);
                params.put("ID_Pegawai", idpeg);
              //  list_data.add(params);
               // AdapterList adapter = new AdapterList(ListPelanggan.this, list_data);
                //lvhape.setAdapter(adapter);
                return params;
            }

        };

        //MEREQUEST DATA DENGAN VOLLEY
        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
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
