package jogjamedianet.com.jogjamedianet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import jogjamedianet.com.jogjamedianet.App.AppController;
import jogjamedianet.com.jogjamedianet.Prefs.UserInfo;
import jogjamedianet.com.jogjamedianet.Util.Server;

public class RegistrasiPelanggan extends AppCompatActivity {


    ProgressDialog pDialog;
    EditText txtnamaperusahaan,txtjenisusaha,txtnamapelanggan,txtalamatpelanggan,txtkelpelanggan,txtkecpelanggan,txtkotapelanggan,txtkodepospelanggan,txtnotlppelanggan;
    EditText txtnofaxpelanggan,txtnohppelanggan,txtemailpelanggan,txtpekerjaanpelanggan,txtnoidpelanggan,txtnonpwp,txtjenislayanan;
    EditText txtnamapenagih,txtalamatpenagih,txtkelpenagih,txtkecpenagih,txtkotapenagih,txtkodepospenagih,txtnotlppenagih;
    EditText txtnofaxpenagih,txtnohppenagih,txtemailpenagih,txtcarapembayaran,txtwaktupembayaran,txtstatustempattinggal,tgllahir;
    Button masukandatapel;
    ListView lvjkpelanggan;
    Intent intent;
    int success;
    int position;
    ConnectivityManager conMgr;
    UserInfo session;

    private String url = Server.URL + "RegistrasiPelanggan.php";

    private static final String TAG = RegistrasiPelanggan.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_pelanggan);



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

        masukandatapel = (Button) findViewById(R.id.enterpelanggan);
        txtnamaperusahaan = (EditText) findViewById(R.id.namaperusahaan);
        txtjenisusaha = (EditText) findViewById(R.id.JenisUsaha);
        txtnamapelanggan = (EditText) findViewById(R.id.namapelanggan2);
        txtalamatpelanggan = (EditText) findViewById(R.id.alamatpelanggan);
        txtkelpelanggan= (EditText) findViewById(R.id.KelPelanggan);
        txtkecpelanggan= (EditText) findViewById(R.id.KecPelanggan);
        txtkotapelanggan = (EditText) findViewById(R.id.kotapelanggan);
        txtkodepospelanggan = (EditText) findViewById(R.id.kodepospelanggan);
        txtnotlppelanggan = (EditText) findViewById(R.id.notlppelanggan);
        txtnofaxpelanggan = (EditText) findViewById(R.id.nofaxpelanggan);
        txtnohppelanggan = (EditText) findViewById(R.id.nohppelanggan);
        txtemailpelanggan = (EditText) findViewById(R.id.emailpelanggan);
        txtpekerjaanpelanggan = (EditText) findViewById(R.id.pekerjaanpelanggan);
        txtnoidpelanggan = (EditText) findViewById(R.id.noidpelanggan);
        txtnonpwp = (EditText) findViewById(R.id.nonpwp);
        txtjenislayanan= (EditText) findViewById(R.id.jenislayanan);
        txtnamapenagih= (EditText) findViewById(R.id.namapenagih);
        txtalamatpenagih= (EditText) findViewById(R.id.alamatpenagih);
        txtkelpenagih= (EditText) findViewById(R.id.KelPenagih);
        txtkecpenagih= (EditText) findViewById(R.id.KecPenagih);
        txtkotapenagih= (EditText) findViewById(R.id.kotapenagih);
        txtkodepospenagih= (EditText) findViewById(R.id.kodepospenagih);
        txtnotlppenagih= (EditText) findViewById(R.id.notlppenagih);
        txtnofaxpenagih= (EditText) findViewById(R.id.nofaxpenagih);
        txtnohppenagih= (EditText) findViewById(R.id.nohppenagih);
        txtemailpenagih= (EditText) findViewById(R.id.emailpenagih);
        txtcarapembayaran= (EditText) findViewById(R.id.carapembayaran);
        txtwaktupembayaran= (EditText) findViewById(R.id.waktupembayaran);
        txtstatustempattinggal= (EditText) findViewById(R.id.statustempattinggal);
        lvjkpelanggan = (ListView) findViewById(R.id.jkelaminpelanggan);
        tgllahir = (EditText) findViewById(R.id.txttgllahir);

        masukandatapel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String jklpelanggan = (String) lvjkpelanggan.getItemAtPosition(position);
                String namaperusahaan = txtnamaperusahaan.getText().toString();
                String jenisusaha = txtjenisusaha.getText().toString();
                String namapelanggan = txtnamapelanggan.getText().toString();
                String alamatpelanggan = txtalamatpelanggan.getText().toString();
                String kelurahanpelanggan = txtkelpelanggan.getText().toString();
                String kecamatanpelanggan = txtkecpelanggan.getText().toString();
                String kotapelanggan = txtkotapelanggan.getText().toString();
                int kodepospelanggan= Integer.parseInt(txtkodepospelanggan.getText().toString());
                int notlppelanggan= Integer.parseInt(txtnotlppelanggan.getText().toString());
                int nofaxpelanggan= Integer.parseInt(txtnofaxpelanggan.getText().toString());
                int nohppelanggan= Integer.parseInt(txtnohppelanggan.getText().toString());
                String emailpelanggan = txtemailpelanggan.getText().toString();
                String tgllahirpelanggan = tgllahir.getText().toString();
                String pekerjaanpelanggan = txtpekerjaanpelanggan.getText().toString();
                int noidpelanggan= Integer.parseInt(txtnoidpelanggan.getText().toString());
                int nonpwppelanggan= Integer.parseInt(txtnonpwp.getText().toString());
                String jenislayanan= txtjenislayanan.getText().toString();
                String namapenagih = txtnamapenagih.getText().toString();
                String alamatpenagih= txtalamatpenagih.getText().toString();
                int kodepospenagih= Integer.parseInt(txtkodepospenagih.getText().toString());
                int notlppenagih= Integer.parseInt(txtnotlppenagih.getText().toString());
                int nofaxpenagih= Integer.parseInt(txtnofaxpenagih.getText().toString());
                int nohppenagih= Integer.parseInt(txtnohppenagih.getText().toString());
                String emailpenagih = txtemailpenagih.getText().toString();
                String carapembayaran  = txtcarapembayaran.getText().toString();
                String waktupembayaran = txtwaktupembayaran.getText().toString();
                String tempattinggal = txtstatustempattinggal.getText().toString();
                String idPegawai ;
                idPegawai = session.getKeyID();

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkRegisterPelanggan(namaperusahaan,jenisusaha,namapelanggan,alamatpelanggan,kelurahanpelanggan,kecamatanpelanggan,kotapelanggan,kodepospelanggan
                            ,notlppelanggan,nofaxpelanggan,nohppelanggan,emailpelanggan ,tgllahirpelanggan,jklpelanggan,pekerjaanpelanggan,noidpelanggan,nonpwppelanggan,jenislayanan
                            ,namapenagih,alamatpenagih,kodepospenagih,notlppenagih,nofaxpenagih,nohppenagih,emailpenagih,carapembayaran,waktupembayaran,tempattinggal,idPegawai);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }


            }

        });

    }

    private void checkRegisterPelanggan(final String namaperusahaan,final String jenisusaha,final String namapelanggan,final String alamatpelanggan, final String kelurahanpelanggan,final String kecamatanpelanggan, final String kotapelanggan, final int kodepospelanggan
            ,final int notlppelanggan,final int nofaxpelanggan,final int nohppelanggan,final String emailpelanggan ,final String tgllahirpelanggan,final String jklpelanggan,final String pekerjaanpelanggan,final int noidpelanggan,final int nonpwppelanggan,final String jenislayanan
            ,final String namapenagih,final String alamatpenagih,final int kodepospenagih,final int notlppenagih,final int nofaxpenagih,final int nohppenagih,final String emailpenagih,final String carapembayaran,final String waktupembayaran,final String tempattinggal,final String idPegawai) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Register ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully Register!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();


                        Intent intent = new Intent(RegistrasiPelanggan.this,Home.class);
                     /*   txt_username.setText("");
                        txt_password.setText("");
                        txt_confirm_password.setText("");
                    */
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
                params.put("namaperusahaan", namaperusahaan);
                params.put("jenisusaha", jenisusaha);
                params.put("namapelanggan", namapelanggan);
                params.put("alamatpelanggan",alamatpelanggan );
                params.put("kelurahanpelanggan", kelurahanpelanggan);
                params.put("kecamatanpelanggan", kecamatanpelanggan);
                params.put("kotapelanggan", kotapelanggan);
                params.put("kodepospelanggan", String.valueOf(kodepospelanggan));
                params.put("notlppelanggan", String.valueOf(notlppelanggan));
                params.put("nofaxpelanggan", String.valueOf(nofaxpelanggan));
                params.put("nohppelanggan", String.valueOf(nohppelanggan));
                params.put("emailpelanggan", emailpelanggan);
                params.put("tgllahirpelanggan", tgllahirpelanggan);
                params.put("jklpelanggan", jklpelanggan);
                params.put("pekerjaanpelanggan", pekerjaanpelanggan);
                params.put("noidpelanggan", String.valueOf(noidpelanggan));
                params.put("nonpwppelanggan", String.valueOf(nonpwppelanggan));
                params.put("jenislayanan", jenislayanan);
                params.put("namapenagih", namapenagih);
                params.put("alamatpenagih", alamatpenagih);
                params.put("kodepospenagih", String.valueOf(kodepospenagih));
                params.put("notlppenagih", String.valueOf(notlppenagih));
                params.put("nofaxpenagih", String.valueOf(nofaxpenagih));
                params.put("nohppenagih", String.valueOf(nohppenagih));
                params.put("emailpenagih", emailpenagih);
                params.put("carapembayaran", carapembayaran);
                params.put("waktupembayaran", waktupembayaran);
                params.put("tempattinggal", tempattinggal);

                // how to parse this one ?? (integer)



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
}
