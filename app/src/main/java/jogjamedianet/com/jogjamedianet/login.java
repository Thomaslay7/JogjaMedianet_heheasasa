package jogjamedianet.com.jogjamedianet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

public class login extends AppCompatActivity {



        private UserInfo userinfo;

        ProgressDialog pDialog;
        Button btn_register, btn_login;
        EditText txt_username, txt_password;
        Intent intent;

        int success;
        String user,id;
        ConnectivityManager conMgr;

        private String url = Server.URL + "login.php";

        private static final String TAG = login.class.getSimpleName();

        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";

        public final static String TAG_USERNAME = "username";

        public final static String TAG_NAMADEPAN = "NamaDepan";
        public final static String TAG_NAMABELAKANG = "NamaBelakang";
        public final static String TAG_JENISKELAMIN = "JenisKelamin";
        public final static String TAG_JABATAN = "Jabatan";
        public final static String TAG_NIK = "NIK";
        public final static String TAG_ID = "id";

        String tag_json_obj = "json_obj_req";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

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

            btn_login = (Button) findViewById(R.id.btn_login);
            btn_register = (Button) findViewById(R.id.btn_register);
            txt_username = (EditText) findViewById(R.id.txt_username);
            txt_password = (EditText) findViewById(R.id.txt_password);

            // Session manager
          //  session = new UserSession(getApplicationContext());
            userinfo = new UserInfo(getApplicationContext());
            // Check if user is already logged in or not
            if (userinfo.isLoggedin()) {
                // User is already logged in. Take him to main activity
                Intent intent = new Intent(login.this, Home.class);
                startActivity(intent);
                finish();
            }

            btn_login.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    String username = txt_username.getText().toString();
                    String password = txt_password.getText().toString();

                    // mengecek kolom yang kosong
                    if (username.trim().length() > 0 && password.trim().length() > 0) {
                        if (conMgr.getActiveNetworkInfo() != null
                                && conMgr.getActiveNetworkInfo().isAvailable()
                                && conMgr.getActiveNetworkInfo().isConnected()) {
                            checkLogin(username, password);
                        } else {
                            Toast.makeText(getApplicationContext() ,"No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        // Prompt user to enter credentials
                        Toast.makeText(getApplicationContext() ,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                    }
                }
            });

            btn_register.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    intent = new Intent(login.this, Register.class);
                    finish();
                    startActivity(intent);
                }
            });

        }

    public void setPasswordVisibility(View v)
    {
        EditText txtPassword = (EditText)findViewById(R.id.txt_password);
        CheckBox ckpas=(CheckBox)findViewById(R.id.checkBox);
        if(ckpas.isChecked())
            txtPassword.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        else
            txtPassword.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    private void checkLogin(final String username, final String password) {
            pDialog = new ProgressDialog(this);
            pDialog.setCancelable(false);
            pDialog.setMessage("Logging in ...");
            showDialog();


            StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.e(TAG, "Login Response: " + response.toString());
                    hideDialog();

                    try {
                        JSONObject jObj = new JSONObject(response);
                        success = jObj.getInt(TAG_SUCCESS);

                        // Check for error node in json
                        if (success == 1) {

                            String id=jObj.getString((TAG_ID));
                            String username = jObj.getString(TAG_USERNAME);
                            String namadepan = jObj.getString(TAG_NAMADEPAN);
                            String namabelakang = jObj.getString(TAG_NAMABELAKANG);
                            String jeniskelamin = jObj.getString(TAG_JENISKELAMIN);
                            String jabatan = jObj.getString(TAG_JABATAN);
                            String nik = jObj.getString(TAG_NIK);




                            Log.e("Successfully Login!", jObj.toString());
                            Toast.makeText(getApplicationContext() ,"Welcome!", Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),
                                    jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                            userinfo.setLoggin(true);
                            userinfo.setId(id);
                            userinfo.setUsername(username);
                            userinfo.setNamadepan(namadepan);
                            userinfo.setNamaBelakang(namabelakang);
                            userinfo.setJenisKelamin(jeniskelamin);
                            userinfo.setJabatan(jabatan);
                            userinfo.setNIK(nik);
                            Intent intent = new Intent(login.this,Home.class);


                         //  intent.putExtra(TAG_ID, id);
                           // intent.putExtra(TAG_USERNAME, username);




                            finish();
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                   "username atau password salah", Toast.LENGTH_LONG).show();

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
                    params.put("username",username);
                    params.put("password", password);

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
