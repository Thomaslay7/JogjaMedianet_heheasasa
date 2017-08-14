package jogjamedianet.com.jogjamedianet;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import jogjamedianet.com.jogjamedianet.App.AppController;
import jogjamedianet.com.jogjamedianet.Prefs.UserInfo;
import jogjamedianet.com.jogjamedianet.Util.Server;

public class RegistrasiPelanggan extends AppCompatActivity {


    ProgressDialog pDialog;
    EditText txtnamaperusahaan,txtjenisusaha,txtnamapelanggan,txtalamatpelanggan,txtkelpelanggan,txtkecpelanggan,txtkotapelanggan,txtkodepospelanggan,txtnotlppelanggan;
    EditText txtnofaxpelanggan,txtnohppelanggan,txtemailpelanggan,txtpekerjaanpelanggan,txtnoidpelanggan,txtnonpwp,txtjenislayanan;
    private  EditText tgllahir;
    Button masukandatapel;
    TextView txtIdPeg;
    CheckBox internet,tv,tunai,transfer,autodebet,bulanan,tigabulan,enambulan,dubelasbulan,rumah,kontrak,kost;
    private RadioGroup radioSexGroup;
    private RadioButton rbP;
    private RadioButton rbL;
    Intent intent;
    int success;
    int position;
    ConnectivityManager conMgr;
    private UserInfo session;

    private String url = Server.URL + "RegistrasiPelanggan.php";

    private static final String TAG = RegistrasiPelanggan.class.getSimpleName();
    public static  final int DATE_DIALOG_ID=0;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_pelanggan);


        session = new UserInfo(getApplicationContext());

        session  = new UserInfo(this);

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
        internet= (CheckBox) findViewById(R.id.cbxinternet);
        tv= (CheckBox) findViewById(R.id.cbxtv);
        tunai= (CheckBox) findViewById(R.id.cbxTunai);
        transfer= (CheckBox) findViewById(R.id.cbxTransfer);
        autodebet= (CheckBox) findViewById(R.id.cbxAuto);
        bulanan= (CheckBox) findViewById(R.id.cbxbulanan);
        tigabulan= (CheckBox) findViewById(R.id.cbxtiga);
        enambulan= (CheckBox) findViewById(R.id.cbxenam);
        dubelasbulan= (CheckBox) findViewById(R.id.cbxdua);
        rumah= (CheckBox) findViewById(R.id.cbxrumah);
        kontrak= (CheckBox) findViewById(R.id.cbxkontrak);
        kost= (CheckBox) findViewById(R.id.cbxkost);

        radioSexGroup=(RadioGroup)findViewById(R.id.radioGroup);
        tgllahir = (EditText) findViewById(R.id.txttgllahir);
        rbP=(RadioButton)findViewById(R.id.rbPerempuan);
        rbL=(RadioButton)findViewById(R.id.rbLakilaki);
        txtIdPeg=(TextView)findViewById(R.id.txtIdPegawai);
        tgllahir.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch (View v, MotionEvent event)
            {
                if(v==tgllahir)

                    showDialog(DATE_DIALOG_ID);
                return false;
            }
        });
        masukandatapel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!validate()) {

                    return;
                }

                String namaperusahaan = txtnamaperusahaan.getText().toString();
                String jenisusaha = txtjenisusaha.getText().toString();
                String namapelanggan = txtnamapelanggan.getText().toString();
                String alamatpelanggan = txtalamatpelanggan.getText().toString();
                String kelurahanpelanggan = txtkelpelanggan.getText().toString();
                String kecamatanpelanggan = txtkecpelanggan.getText().toString();
                String kotapelanggan = txtkotapelanggan.getText().toString();
                String kodepospelanggan= txtkodepospelanggan.getText().toString();
                String notlppelanggan= txtnotlppelanggan.getText().toString();
                String nofaxpelanggan= txtnofaxpelanggan.getText().toString();
                String nohppelanggan= txtnohppelanggan.getText().toString();
                String emailpelanggan = txtemailpelanggan.getText().toString();

                String tgllahirpelanggan = tgllahir.getText().toString();
                String pekerjaanpelanggan = txtpekerjaanpelanggan.getText().toString();
                String noidpelanggan= txtnoidpelanggan.getText().toString();
                String nonpwppelanggan= txtnonpwp.getText().toString();
                String layanan="";
                String carapembayaran  = "";
                String waktupembayaran = "";
                String tempattinggal = "";
                String jeniskelaminS="";
                if(tv.isChecked())
                {
                    layanan="TV Kabel";
                }
                else if(internet.isChecked())
                {
                    layanan="Internet";
                }
                 if(tunai.isChecked())
                {
                   carapembayaran="Tunai";
                }
                else if(transfer.isChecked())
                {
                    carapembayaran="Transfer Bank";
                }
                else if(autodebet.isChecked())
                {
                    carapembayaran="Auto Debet Kartu Kredit";
                }
                if(kontrak.isChecked())
                {
                    tempattinggal="Kontrak";
                }
                else if(kost.isChecked())
                {
                    tempattinggal="Kost";
                }
                else if(rumah.isChecked())
                {
                    tempattinggal="Rumah Sendiri";
                }
                if(bulanan.isChecked())
                {
                   waktupembayaran="Bulanan";
                }
                else if(tigabulan.isChecked())
                {
                    waktupembayaran="3 Bulanan";
                }

                if(enambulan.isChecked()) {
                    waktupembayaran= "6 Bulanan";
                }
                else if(dubelasbulan.isChecked()) {
                    waktupembayaran= "12 Bulanan";
                }
                if(rbL.isChecked()) {
                    jeniskelaminS= "Laki - laki";
                }
                else if(rbP.isChecked()) {
                    jeniskelaminS= "Perempuan";
                }
                String idPgw ;
                idPgw = session.getKeyID().toString();
               // session.setId(idPgw);
                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkRegisterPelanggan(namaperusahaan,jenisusaha,namapelanggan,alamatpelanggan,kelurahanpelanggan,kecamatanpelanggan,kotapelanggan,kodepospelanggan
                            ,notlppelanggan,nofaxpelanggan,nohppelanggan,emailpelanggan ,tgllahirpelanggan,jeniskelaminS,pekerjaanpelanggan,noidpelanggan,nonpwppelanggan,layanan
                            ,carapembayaran,waktupembayaran,tempattinggal,idPgw);




                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }


            }

        });

    }
    protected Dialog onCreateDialog(int id)
    {
        Calendar c= Calendar.getInstance();
        int cyear=c.get(Calendar.YEAR);
        int cmonth=c.get(Calendar.MONTH);
        int cday=c.get(Calendar.DAY_OF_MONTH);
        switch (id)
        {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,mDateSetListener,cyear,cmonth,cday);
        }
        return null;

    }
    private DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfmonth)
        {
            String date_selected=String.valueOf(year)+"/"
                    +String.valueOf(monthOfYear+1)+"/"
                    +String.valueOf(dayOfmonth);
            tgllahir.setText(date_selected);
        }
    };
    private void checkRegisterPelanggan(final String namaperusahaan,final String jenisusaha,final String namapelanggan,final String alamatpelanggan, final String kelurahanpelanggan,final String kecamatanpelanggan, final String kotapelanggan, final  String kodepospelanggan
            ,final  String notlppelanggan,final  String nofaxpelanggan,final  String nohppelanggan,final String emailpelanggan ,final String tgllahirpelanggan,final String jklpelanggan,final String pekerjaanpelanggan,final  String noidpelanggan,final  String nonpwppelanggan,final String jenislayanan
            ,final String carapembayaran,final String waktupembayaran,final String tempattinggal,final String idPegawai) {
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
                        intent = new Intent(RegistrasiPelanggan.this, ListPelanggan.class);
                        finish();
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();


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
                params.put("jenis_usaha", jenisusaha);
                params.put("nama_pelanggan", namapelanggan);
                params.put("alamatpelanggan",alamatpelanggan );
                params.put("kelurahan_pelanggan", kelurahanpelanggan);
                params.put("kec_pelanggan", kecamatanpelanggan);
                params.put("kota_pelanggan", kotapelanggan);
                params.put("kodepos_pelanggan", String.valueOf(kodepospelanggan));
                params.put("no_telp_pelanggan", String.valueOf(notlppelanggan));
                params.put("no_fax_pelanggan", String.valueOf(nofaxpelanggan));
                params.put("no_hp_pelanggan", String.valueOf(nohppelanggan));
                params.put("emailpelanggan", emailpelanggan);
                params.put("tanggal_lahir_pelanggan", tgllahirpelanggan);
                params.put("jenis_kelamin_pelanggan", jklpelanggan);
                params.put("pekerjaan", pekerjaanpelanggan);
                params.put("no_identitas", String.valueOf(noidpelanggan));
                params.put("NPWP", String.valueOf(nonpwppelanggan));
                params.put("layanan", jenislayanan);
                params.put("cara_pembayaran", carapembayaran);
                params.put("waktu_pembayaran", waktupembayaran);
                params.put("status_tempat_tinggal", tempattinggal);
                params.put("ID_Pegawai", idPegawai);
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

    public boolean validate() {
        boolean valid = true;


        if (txtnamaperusahaan.getText().toString().isEmpty()) {
            txtnamaperusahaan.setError("Nama Perusahaan tidak boleh kosong");
            valid = false;
        } else {
            txtnamaperusahaan.setError(null);
        }

        if (txtnamapelanggan.getText().toString().isEmpty()) {
            txtnamapelanggan.setError("Nama Pelanggan tidak boleh kosong");
            valid = false;
        } else {
            txtnamapelanggan.setError(null);
        }

        if (txtemailpelanggan.getText().toString().isEmpty() /*|| !android.util.Patterns.EMAIL_ADDRESS.matcher(txtemailpelanggan).matches()*/) {
            txtemailpelanggan.setError("Email tidak sesuai dengan format");
            valid = false;
        } else {
            txtemailpelanggan.setError(null);
        }




        if (txtjenisusaha.getText().toString().isEmpty()) {
            txtjenisusaha.setError("Jenis Usaha tidak boleh kosong");
            valid = false;
        } else {
            txtjenisusaha.setError(null);
        }

        if (txtnohppelanggan.getText().toString().isEmpty()) {
            txtnohppelanggan.setError("Nomor HP tidak boleh kosong");
            valid = false;
        } else {
           txtnohppelanggan.setError(null);
        }

        if (txtnofaxpelanggan.getText().toString().isEmpty() ) {
            txtnofaxpelanggan.setError("Nomor Fax tidak boleh kosong");
            valid = false;
        } else {
            txtnofaxpelanggan.setError(null);
        }
        if (!rbL.isChecked()&&!rbP.isChecked()) {
            rbL.setError("Jenis kelamin tidak boleh kosong");
            rbP.setError("Jenis kelamin tidak boleh kosong");
            valid = false;
        } else {
            rbL.setError(null);
            rbP.setError(null);
        }
        if (tgllahir.getText().toString().isEmpty()) {
            tgllahir.hasFocusable();
            tgllahir.setError("Tanggal lahir tidak boleh kosong");
            valid = false;
        } else {
            tgllahir.setError(null);
        }
        if (txtalamatpelanggan.getText().toString().isEmpty()) {
            txtalamatpelanggan.setError("Alamat tidak boleh kosong");
            valid = false;
        } else {
            txtalamatpelanggan.setError(null);
        }
        if (txtkecpelanggan.getText().toString().isEmpty()) {
            txtkecpelanggan.setError("Kecamatan  tidak boleh kosong");
            valid = false;
        } else {
            txtkecpelanggan.setError(null);
        }
        if (txtkelpelanggan.getText().toString().isEmpty()) {
            txtkelpelanggan.setError("Kelurahan  tidak boleh kosong");
            valid = false;
        } else {
            txtkelpelanggan.setError(null);
        }
        if (txtkodepospelanggan.getText().toString().isEmpty()) {
            txtkodepospelanggan.setError("Kodepos  tidak boleh kosong");
            valid = false;
        } else {
            txtkodepospelanggan.setError(null);
        }
        if (txtkotapelanggan.getText().toString().isEmpty()) {
            txtkotapelanggan.setError("Kota  tidak boleh kosong");
            valid = false;
        } else {
            txtkotapelanggan.setError(null);
        }
        if (txtnonpwp.getText().toString().isEmpty()) {
            txtnonpwp.setError("NPWP  tidak boleh kosong");
            valid = false;
        } else {
            txtnonpwp.setError(null);
        }
        if (txtpekerjaanpelanggan.getText().toString().isEmpty()) {
            txtpekerjaanpelanggan.setError("Pekerjaan Pelanggan tidak boleh kosong");
            valid = false;
        } else {
            txtpekerjaanpelanggan.setError(null);
        }
        if (txtnoidpelanggan.getText().toString().isEmpty()) {
            txtnoidpelanggan.setError("Nomor Identitas tidak boleh kosong");
            valid = false;
        } else {
            txtnoidpelanggan.setError(null);
        }
        return valid;
    }
}
