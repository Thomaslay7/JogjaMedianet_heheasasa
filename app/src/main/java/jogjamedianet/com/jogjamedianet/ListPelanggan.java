package jogjamedianet.com.jogjamedianet;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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

    public void cek(final String idpeg) {

        final String namaperu, namapel, jenis,id;
        namaperu = session.getKeyNamaperusahaan();
        namapel = session.getKeyNamapelanggan();
        jenis = session.getKeyJenisusaha();
        id=session.getKeyIdPelanggan();


        list_data = new ArrayList<HashMap<String, String>>();
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Load Data...");
        showDialog();

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "response " + response.toString());
                hideDialog();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("id", json.getString("id"));
                        map.put("namaperusahaan",json.getString("namaperusahaan"));
                        map.put("jenis_usaha", json.getString("jenis_usaha"));
                        map.put("nama_pelanggan", json.getString("nama_pelanggan"));
                        map.put("ID_Pegawai", idpeg);
                       // txt.setText(json.getString("namaperusahaan"));
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
   //===================================================================================================================
   /*
    List<UserInfo> subjectsList;

    int success;
    ConnectivityManager conMgr;
    String myJSON;
    JsonArrayRequest jsonArrayRequest ;

    ProgressDialog pDialog;
    private String url = Server.URL + "ListPelanggan.php";

    private static final String TAG = ListPelanggan.class.getSimpleName();
    private static final String TAG_RESULTS="result";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_JENISUSAHA = "jenis_usaha";
    private static final String TAG_NAMAPELANGGAN = "nama_pelanggan";
    private static final String TAG_NAMAPERUSAHAAN = "namaperusahaan";
    JSONArray peoples = null;
    ArrayList<HashMap<String, String>> personList;
    String tag_json_obj = "json_obj_req";

    private ArrayList<subjects> PelangganItems = new ArrayList<subjects>();

    ListView list;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;
    //  private Call<APIOrderListPengajar> callGuru;
    // private RestClient.GitApiInterface service;

    //  private ArrayList<APIOrderListPengajar.ResponBean> GuruItems = new ArrayList<APIOrderListPengajar.ResponBean>();
    private String idp;
    private String namadepan;
    private String namabelakang;
    private String tempatlahir;
    private String tanggallahir;
    private String jeniskelamin;
    private String nomortlp;
    private String emailmurid;
    private String alamatmurid;
    private Intent intent;
    private UserInfo userinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pelanggan);
        list = (ListView) findViewById(R.id.listView);
        userinfo = new UserInfo(getApplicationContext());
        userinfo        = new UserInfo(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        rvView.setLayoutManager(layoutManager);
        rvView.setAdapter(adapter);
        idp=userinfo.getKeyID();
        String idpeg=idp;
        fetchData(idpeg);
      /*  if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {

        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

        }
    personList = new ArrayList<HashMap<String,String>>();
        //getData();

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
                                intent = new Intent(ListPelanggan.this,RegistrasiPelanggan.class);
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




 /*   public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost(url);

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }


    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String jenisusaha = c.getString(TAG_JENISUSAHA);
                String namapel = c.getString(TAG_NAMAPELANGGAN);
                String namaperu = c.getString(TAG_NAMAPERUSAHAAN);

                HashMap<String,String> persons = new HashMap<String,String>();

                persons.put(TAG_NAMAPERUSAHAAN,namaperu);
                persons.put(TAG_JENISUSAHA,jenisusaha);
                persons.put(TAG_NAMAPELANGGAN,namapel);

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    ListPelanggan.this, personList, R.layout.cardview,
                    new String[]{TAG_NAMAPERUSAHAAN,TAG_JENISUSAHA,TAG_NAMAPELANGGAN},
                    new int[]{R.id.txtnamaperusahaan, R.id.txtjenisusaha, R.id.txtnamapelanggan}
            );

            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void fetchData(String id)
    {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Register ...");
        showDialog();
      //  JSONArray array;
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "List Pelangan Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);
                    JSONArray jArray = new JSONArray();
                    String namaperusahaan= userinfo.getKeyNamaperusahaan();
                    String namapelanggan = userinfo.getKeyNamapelanggan();
                    String jenisusaha =userinfo.getKeyJenisusaha();
                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Data Pelanggan Anda...", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        ArrayList<String> animalNames = new ArrayList<>();
                        animalNames.add(namaperusahaan);

                       // PelangganItems.add();
                  //      subjectsList.add(userinfo);

                   //     recyclerViewadapter = new RecyclerViewCardViewAdapter(subjectsList, this);

                   //     recyclerView.setAdapter(recyclerViewadapter);

                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
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
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("namaperusahaan", namadepan);
                params.put("nama_pelanggan", namabelakang);
              /*  params.put("jenis_usaha", jabatan);
                params.put("jeniskelamin",jk );
                params.put("username", username);
                params.put("password", password);
                params.put("confirm_password", confirm_password);

                return params;
            }

        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
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
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle presses on the action bar items
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        // Code you want run when activity is clicked

                        userinfo.setLoggin(false);
                        userinfo.clearUserInfo();
                        return true;
                    default:
                        return super.onOptionsItemSelected(item);
                }
            }


    @Override
    public void onItemClick(View view, int position) {

    }
    */
//}

