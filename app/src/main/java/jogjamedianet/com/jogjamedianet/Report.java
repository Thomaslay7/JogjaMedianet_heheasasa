package jogjamedianet.com.jogjamedianet;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jogjamedianet.com.jogjamedianet.App.AppController;
import jogjamedianet.com.jogjamedianet.Prefs.UserInfo;
import jogjamedianet.com.jogjamedianet.Util.Server;

/**
 * Created by mery on 7/13/2017.
 */
public class Report  extends AppCompatActivity {

    private String url=Server.URL+"report.php";
    private static final String TAG = Report.class.getSimpleName();
    private UserInfo session;
    private LineChart mChart;
    String tag_json_obj = "json_obj_req";
    ConnectivityManager conMgr;
    List<com.github.mikephil.charting.data.Entry> x;
    ArrayList<String> y;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.report);

//        url = Server.URL + "report.php";

        mChart = (LineChart) findViewById(R.id.linechart);
        //  mChart.setOnChartGestureListener(this);
        //  mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);
        mChart.setDescription("Laporan Jumlah Pelanggan yang Diperoleh Pegawai ");
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(true);
        x = new ArrayList<com.github.mikephil.charting.data.Entry>();
        y = new ArrayList<String>();

        XAxis xl = mChart.getXAxis();
        xl.setAvoidFirstLastClipping(true);
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setInverted(false);
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(true);
        // get the legend (only possible after setting data)

        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        mChart.getViewPortHandler().setMaximumScaleY(2f);
        mChart.getViewPortHandler().setMaximumScaleX(2f);

        leftAxis.setAxisMaxValue(7f);
        leftAxis.setAxisMinValue(0f);
        mChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);
        mChart.setExtraLeftOffset(15f);
        mChart.setExtraRightOffset(15f);
        //  dont forget to refresh the drawing
        mChart.invalidate();
        session = new UserInfo(getApplicationContext());
        session = new UserInfo(this);
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
                ambilData();
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }
        // add data
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {

            ambilData();

        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


        // l.setPosition(LegendPosition.LEFT_OF_CHART);

        // no description text
  /*      mChart.setDescription("Laporan Semua Karyawan");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);
*/
  //      LimitLine upper_limit = new LimitLine(130f, "Upper Limit");
       /* upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f, 10f, 0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(10f);

        LimitLine lower_limit = new LimitLine(-30f, "Lower Limit");
        lower_limit.setLineWidth(4f);
        lower_limit.enableDashedLine(10f, 10f, 0f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(10f);

          leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaxValue(220f);
        leftAxis.setAxisMinValue(-50f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        mChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);

        //  dont forget to refresh the drawing
        mChart.invalidate();
        */
    }

/*
    private ArrayList<String> setXAxisValues(){
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("10");
        xVals.add("20");
        xVals.add("30");
        xVals.add("30.5");
        xVals.add("40");

        return xVals;
    }

    private ArrayList<com.github.mikephil.charting.data.Entry> setYAxisValues(){
        ArrayList<com.github.mikephil.charting.data.Entry> yVals = new ArrayList<com.github.mikephil.charting.data.Entry>();
        yVals.add(new com.github.mikephil.charting.data.Entry(60, 0));
        yVals.add(new com.github.mikephil.charting.data.Entry(48, 1));
        yVals.add(new com.github.mikephil.charting.data.Entry(70.5f, 2));
        yVals.add(new com.github.mikephil.charting.data.Entry(100, 3));
        yVals.add(new com.github.mikephil.charting.data.Entry(180.9f, 4));

        return yVals;
    }

    private void setData() {
        ArrayList<String> xVals = setXAxisValues();

        ArrayList<com.github.mikephil.charting.data.Entry> yVals = setYAxisValues();

        LineDataSet set1;


        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "Nama Pegawai");

        set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);

    }
*/
    private void ambilData()
    {
        String tag_string_req = "req_chart";

        StringRequest strReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG, "Response: " + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("result");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject json = jsonArray.getJSONObject(i);

                                String namapegawai = json.getString("NamaPegawai");
                                String jmlhpelanggan = json.getString("JumlahPelanggan");
                                //Double.parseDouble(jmlhpelanggan), i)
                             x.add(new com.github.mikephil.charting.data.Entry(Float.parseFloat(jmlhpelanggan.toString()),i));

                                y.add(namapegawai);

                            }
                            LineDataSet set1 = new LineDataSet(x, "X : Jumlah Pelanggan yang Diperoleh");
                            set1.setLineWidth(5f);
                            set1.setCircleRadius(25f);
                            set1.setColor(Color.BLACK);
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
        });
        strReq.setRetryPolicy(new RetryPolicy() {

            @Override
            public void retry(VolleyError arg0) throws VolleyError {
            }

            @Override
            public int getCurrentTimeout() {
                return 0;
            }

            @Override
            public int getCurrentRetryCount() {
                return 0;
            }
        });
        strReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

   /* @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            mChart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    @Override
    public void onValueSelected(com.github.mikephil.charting.data.Entry e, int dataSetIndex, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOWHIGH", "low: " + mChart.getLowestVisibleXIndex() + ", high: " + mChart.getHighestVisibleXIndex());
        Log.i("MIN MAX", "xmin: " + mChart.getXChartMin() + ", xmax: " + mChart.getXChartMax() + ", ymin: " + mChart.getYChartMin() + ", ymax: " + mChart.getYChartMax());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
    */
}
