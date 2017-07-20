package jogjamedianet.com.jogjamedianet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jogjamedianet.com.jogjamedianet.Preferences.SessionManager;

public class ListPelanggan extends AppCompatActivity {

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;
    //  private Call<APIOrderListPengajar> callGuru;
    // private RestClient.GitApiInterface service;

    //  private ArrayList<APIOrderListPengajar.ResponBean> GuruItems = new ArrayList<APIOrderListPengajar.ResponBean>();
    private Integer id;
    private String namadepan;
    private String namabelakang;
    private String tempatlahir;
    private String tanggallahir;
    private String jeniskelamin;
    private String nomortlp;
    private String emailmurid;
    private String alamatmurid;
    private Intent intent;
    SessionManager sessions;
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pelanggan);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        rvView = (RecyclerView) findViewById(R.id.rv_mainorder);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        rvView.setLayoutManager(layoutManager);
        adapter = new RecycleViewOrderAdapter(ListPelanggan.this, GuruItems);
        rvView.setAdapter(adapter);
        sessions = new SessionManager(this);
        fetchData();

        Bundle b=getIntent().getExtras();
//        id=b.getInt("idmurid");
//        namadepan=b.getString("namadepan");
//        namabelakang=b.getString("namabelakang");
//        tempatlahir=b.getString("tempatlahir");
//        tanggallahir=b.getString("tanggallahir");
//        jeniskelamin=b.getString("jeniskelamin");
//        nomortlp=b.getString("nomortlp");
//        emailmurid=b.getString("emailmurid");
        //  alamatmurid=b.getString("alamatmurid");




        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
//                                intent = new Intent(ListOrderActivity.this, Testing.class);
                                intent = new Intent(ListOrderActivity.this,ListGuruActivity.class);
                                startActivity(intent);
                                break;

                            case R.id.action_schedules:

                                break;
                            case R.id.action_music:
                                //  Bundle extras = new Bundle();
//                                extras.putInt("idmurid",id);
//                                extras.putString("namadepan",namadepan);
//                                extras.putString("namabelakang",namabelakang);
//                                extras.putString("tempatlahir",tempatlahir);
//                                extras.putString("tanggallahir",tanggallahir);
//                                extras.putString("jeniskelamin",jeniskelamin);
//                                extras.putString("nomorlp",nomortlp);
//                                extras.putString("emailmurid",emailmurid);
                                //  extras.putString("alamatmurid",alamatmurid);

                                intent = new Intent(ListOrderActivity.this, ProfilActivity.class);
                                //   intent.putExtras(extras);
                                startActivity(intent);
                                break;

                        }
                        return true;
                    }
                });


    }

    public void fetchData()
    {
        final ProgressDialog progressDialog = new ProgressDialog(ListOrderActivity.this,
                R.style.ProgressDialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Mengambil Data Guru...");
        progressDialog.show();


        //       String ID=sessions.getUserDetails().get(SessionManager.KEY_USERID);
        service = RestClient.getClient();
        callGuru = service.orderdetil(Integer.parseInt(sessions.getUserDetails().get(SessionManager.KEY_USERID)));
        callGuru.enqueue(new Callback<APIOrderListPengajar>() {
            @Override
            public void onResponse(Response<APIOrderListPengajar> response) {
                Log.d("ListGuruFetching", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    APIOrderListPengajar result = response.body();
                    Log.d("ListGuruFetching", "response = " + new Gson().toJson(result));
                    if (result != null) {

                        GuruItems.clear();

                        List<APIOrderListPengajar.ResponBean> ResponseItems = result.getRespon();

                        if(ResponseItems!=null)
                        {
                            for (APIOrderListPengajar.ResponBean Responitem : ResponseItems) {
                                GuruItems.add(Responitem);
                                adapter.notifyDataSetChanged();
                            }
                        }
                        progressDialog.dismiss();
                    }

                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(getApplicationContext(), "Gagal Ambil Data", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi Ke Internet Gagal", Toast.LENGTH_SHORT).show();
                Log.d("ListGuruFetching", t.getMessage()+t.toString());
                progressDialog.dismiss();

            }
        });

    }
}
*/
}