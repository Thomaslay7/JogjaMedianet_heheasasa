package jogjamedianet.com.jogjamedianet;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jogjamedianet.com.jogjamedianet.App.AppController;
import jogjamedianet.com.jogjamedianet.Prefs.UserInfo;
import jogjamedianet.com.jogjamedianet.Util.Server;

/**
 * Created by mery on 8/25/2017.
 */
public class Report extends AppCompatActivity {

private String url= Server.URL+"report.php";
private static final String TAG = Report.class.getSimpleName();
public static  final int DATE_DIALOG_ID=0;
public  static  final  int DATE_DIALOG_IDD=1;
private Button kembali;
private UserInfo session;
private LineChart mChart;
private EditText tgll,tglend;
        String tag_json_obj = "json_obj_req";
        List<Entry> x;
        ArrayList<String> y;
private Button btntgl,back;
        ConnectivityManager conMgr;

@Override
protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_report);

        btntgl = (Button) findViewById(R.id.btntgl);
        back= (Button) findViewById(R.id.btnKembali);
        tgll =(EditText) findViewById(R.id.txttgl);
        tglend =(EditText) findViewById(R.id.txttglend);
        kembali = (Button) findViewById(R.id.btnKembali);
        //mChart digunakan untuk mengeset garis, dan kemungkinan - kemungkinan yang terjadi pada Chart.
        mChart = (LineChart) findViewById(R.id.linechart);
        mChart.setDrawGridBackground(false);
        mChart.setDescription("Laporan Jumlah Pelanggan yang Diperoleh Pegawai ");
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(true);

        //x bertipe entry, artinya x adalah variabel yang menampung data yang akan menentukan titik letak dari data yang ada.
        x = new ArrayList<com.github.mikephil.charting.data.Entry>();
        y = new ArrayList<String>();

        XAxis xl = mChart.getXAxis();
        xl.setAvoidFirstLastClipping(true);
        //menginisialisasi bahwa yl adalah sumbu vertical ke atas.
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setInverted(false);

        //menginisailsasi bahwa xl adalah sumbu horizontal ke kiri.
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
        // get the legend (only possible after setting data)

        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        mChart.getViewPortHandler().setMaximumScaleY(2f);
        mChart.getViewPortHandler().setMaximumScaleX(2f);

        mChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);
        mChart.setExtraLeftOffset(15f);
        mChart.setExtraRightOffset(15f);
        //  dont forget to refresh the drawing
        mChart.invalidate();
        session = new UserInfo(getApplicationContext());
        session = new UserInfo(this);


        tgll.setOnTouchListener(new View.OnTouchListener(){
public boolean onTouch (View v, MotionEvent event)
        {
        if(v==tgll)

        showDialog(DATE_DIALOG_ID);
        return false;
        }
        });

        tglend.setOnTouchListener(new View.OnTouchListener(){
public boolean onTouch (View v, MotionEvent event)
        {
        if(v==tglend)

        showDialog(DATE_DIALOG_IDD);
        return false;
        }
        });

        btntgl.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        String from = tgll.getText().toString() ;
        String  to = tglend.getText().toString();


        ambilData(from,to);

        }
        });

        back.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        startActivity(new Intent(Report.this, Home.class));

        finish();
        }
        });


        }

        //prosedur ini berfungsi untuk mengambil data berdasarkan date range.

        private void ambilData(final String from,final String to)
        {
        x.clear();
        y.clear();
        mChart.invalidate();
        mChart.clear();
        StringRequest strReq = new StringRequest(Request.Method.POST, url,
        new Response.Listener<String>() {

        @Override
        public void onResponse(String response) {

        Log.d(TAG, "Response: " + response);

        try {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("result");

        for (int i = 0; i < jsonArray.length();  i++) {
        JSONObject json = jsonArray.getJSONObject(i);

                //menginisialisasi variabel penampung dengan data dari json
        String namapegawai = json.getString("NamaPegawai");
        String jmlhpelanggan = json.getString("JumlahPelanggan");

        //menginisialisasi bahwa garis x datanya diperoleh dari jumlah pelanggan
        x.add(new com.github.mikephil.charting.data.Entry(Float.parseFloat(jmlhpelanggan.toString()),i));

        y.add(i,namapegawai);


        }

                //mengeset garis x beserta warna dan panjang tinggi nya radius koordinatnya
        LineDataSet set1 = new LineDataSet(x, "X :Nama Pegawai     - Y: Jumlah Pegawai yang diperoleh");
        set1.setLineWidth(5f);
        set1.setCircleRadius(25f);
        set1.setColor(Color.CYAN);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);
        set1.setCircleColor(Color.RED);
        LineData data = new LineData(y, set1);

        mChart.setData(data);
        mChart.invalidate();

        } catch (Exception e) {
        e.printStackTrace();
        }
        }
        }, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
        Log.e(TAG, "Error: " + error.getMessage());
        }
        })

        {

        @Override
        protected Map<String, String> getParams() {
        // Posting parameters to login url
        Map<String, String> params = new HashMap<String, String>();
        params.put("from_date",from);
        params.put("to_date",to);
        return params;
        }

        };
        // menambah data dan melemparkannya dengan volley
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);


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

                case DATE_DIALOG_IDD:
                return new DatePickerDialog(this,mDateSetListenerend,cyear,cmonth,cday);
                }
                return null;

        }


//mengeset format tanggal
        private DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfmonth)
        {
                String date_selected=String.valueOf(year)+"/"
                +String.valueOf(monthOfYear+1)+"/"
                +String.valueOf(dayOfmonth);
                tgll.setText(date_selected);
                //tglend.setText(date_selected);
                }
                };
                private DatePickerDialog.OnDateSetListener mDateSetListenerend=new DatePickerDialog.OnDateSetListener(){
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfmonth)
                        {
                String date_selected=String.valueOf(year)+"/"
                +String.valueOf(monthOfYear+1)+"/"
                +String.valueOf(dayOfmonth);
                // tgll.setText(date_selected);
                tglend.setText(date_selected);
                }
                };

}
