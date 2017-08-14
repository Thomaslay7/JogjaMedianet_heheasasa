package jogjamedianet.com.jogjamedianet.Prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;
import java.util.List;

import jogjamedianet.com.jogjamedianet.subjects;

/**
 * Created by mery on 7/13/2017.
 */
public class UserInfo {
    private static final String TAG = UserInfo.class.getSimpleName();
    private static final String PREF_NAME = "userinfo";
    private static final String KEY_IS_LOGGED_IN = "IsLoggedIn";
    //untuk pegawai
    private  static  final String KEY_ID_PELANGGAN="id";
    private static final String KEY_ID = "ID_Pegawai";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_NAMA_DEPAN = "NamaDepan";
    private static final String KEY_NAMA_BELAKANG = "NamaBelakang";
    private static final String KEY_JENIS_KELAMIN = "JenisKelamin";
    private static final String KEY_JABATAN = "Jabatan";
    //untuk pelanggan
    private static final String KEY_NAMAPERUSAHAAN = "namaperusahaan";
    private static final String KEY_JENISUSAHA="jenis_usaha";
    private static final String KEY_NAMAPELANGGAN="nama_pelanggan";
    private static final String KEY_ALAMATPELANGGAN="alamatpelanggan";
    private static final String KEY_KELURAHANPELANGAN= "kelurahan_pelanggan";
    private static final String KEY_KECAMATAN="kec_pelanggan";
    private static final String KEY_KOTA="kota_pelanggan";
    private static final String KEY_KODEPOS="kodepos_pelanggan";
    private static final String KEY_NOTELP="no_telp_pelanggan";
    private static final String KEY_FAX="no_fax_pelanggan";
    private static final String KEY_NOHP="no_hp_pelanggan";
    private static final String KEY_EMAIL="emailpelanggan";
    private static final String KEY_TANGGAL_LAHIR="tanggal_lahir_pelanggan";
    private static final String KEY_JENISKELAMIN="jenis_kelamin_pelanggan";
    private static final String KEY_PEKERJAAN="pekerjaan";
    private static final String KEY_NOIDENTITAS="no_identitas";
    private static final String KEY_NPWP="NPWP";
    private static final String KEY_LAYANAN="layanan";
    private static final String KEY_CARAPEMBAYARAN="cara_pembayaran";
    private static final String KEY_WAKTUPEMBAYARAN="waktu_pembayaran";
    private static final String KEY_STATUS_TEMPAT_TINGGAL="status_tempat_tinggal";
    private static final String KEY_NIK="NIK";
    List<subjects> Pelanggan ;

    //`pelanggan`(`id`, `namaperusahaan`, `jenis_usaha`, `nama_pelanggan`, `alamatpelanggan`, `kelurahan_pelanggan`,
    // `kec_pelanggan`, `kota_pelanggan`, `kodepos_pelanggan`, `no_telp_pelanggan`, `no_fax_pelanggan`, `no_hp_pelanggan`,
    // `emailpelanggan`, `tanggal_lahir_pelanggan`, `jenis_kelamin_pelanggan`, `pekerjaan`, `no_identitas`,
    //`NPWP`, `layanan`, `cara_pembayaran`, `waktu_pembayaran`, `status_tempat_tinggal`, `ID_Pegawai`)

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;
    public String getKeyIdPelanggan() {
        return prefs.getString(KEY_ID_PELANGGAN,"");
    }

    public  String getKeyNamaperusahaan() {
        return prefs.getString(KEY_NAMAPERUSAHAAN,"");
    }

    public  String getKeyJenisusaha() {
        return prefs.getString(KEY_JENISUSAHA,"");
    }

    public String getKeyNamapelanggan() {
        return prefs.getString(KEY_NAMAPELANGGAN,"");
    }

    public String getKeyAlamatpelanggan() {
        return prefs.getString(KEY_ALAMATPELANGGAN,"");
    }

    public String getKeyKelurahanpelangan() {
        return prefs.getString(KEY_KELURAHANPELANGAN,"");
    }

    public String getKeyKecamatan() {
        return prefs.getString(KEY_KECAMATAN,"");
    }

    public String getKeyKota() {
        return prefs.getString(KEY_KOTA,"");
    }

    public  String getKeyKodepos() {
        return prefs.getString(KEY_KODEPOS,"");
    }

    public  String getKeyNotelp() {
        return prefs.getString(KEY_NOTELP,"");
    }

    public String getKeyFax() {
        return prefs.getString(KEY_FAX,"");
    }

    public  String getKeyNohp() {
        return prefs.getString(KEY_NOHP,"");
    }

    public  String getKeyEmail() {
        return prefs.getString(KEY_EMAIL,"");
    }

    public String getKeyTanggalLahir() {
        return prefs.getString(KEY_TANGGAL_LAHIR,"");
    }

    public  String getKeyJeniskelamin() {
        return prefs.getString(KEY_JENISKELAMIN,"");
    }

    public  String getKeyPekerjaan() {
        return prefs.getString(KEY_PEKERJAAN,"");
    }

    public  String getKeyNoidentitas() {
        return prefs.getString(KEY_NOIDENTITAS,"");
    }

    public String getKeyNpwp() {
        return prefs.getString(KEY_NPWP,"");
    }

    public  String getKeyLayanan() {
        return prefs.getString(KEY_LAYANAN,"");
    }

    public  String getKeyCarapembayaran() {
        return prefs.getString(KEY_CARAPEMBAYARAN,"");
    }

    public  String getKeyWaktupembayaran() {
        return prefs.getString(KEY_WAKTUPEMBAYARAN,"");
    }

    public  String getKeyStatusTempatTinggal() {
        return prefs.getString(KEY_STATUS_TEMPAT_TINGGAL,"");
    }

    public List<subjects> getPelanggan()
    {
        //function to return the final populated list
        return Pelanggan;
    }
    public void setLoggin(boolean isLoggedin){
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedin);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedin(){return prefs.getBoolean(KEY_IS_LOGGED_IN, false);}
    public UserInfo(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREF_NAME, 0);
        editor = prefs.edit();
    }

    public void setId(String id){
        editor.putString(KEY_ID, id);
        editor.commit();
    }

    public void setUsername(String username){
        editor.putString(KEY_USERNAME, username);
        editor.commit();
    }

    public void setNamadepan(String namadepan){
        editor.putString(KEY_NAMA_DEPAN, namadepan);
        editor.commit();
    }

    public void setNamaBelakang(String namabelakang){
        editor.putString(KEY_NAMA_BELAKANG, namabelakang);
        editor.commit();
    }

    public void setJabatan(String jabatan){
        editor.putString(KEY_JABATAN, jabatan);
        editor.commit();
    }
    public void setJenisKelamin(String jeniskelamin){
        editor.putString(KEY_JENIS_KELAMIN, jeniskelamin);
        editor.commit();
    }
    public void setNIK(String nik){
        editor.putString(KEY_NIK, nik);
        editor.commit();
    }

    public void clearUserInfo(){
        editor.clear();
        editor.commit();
    }

    public String getKeyID(){return prefs.getString(KEY_ID,"");}

    public String getKeyUsername(){return prefs.getString(KEY_USERNAME, "");}

    public String getKeyNamaDepan(){return prefs.getString(KEY_NAMA_DEPAN, "");}

    public String getKeyNamaBelakang(){return prefs.getString(KEY_NAMA_BELAKANG, "");}

    public String getKeyJabatan(){return prefs.getString(KEY_JABATAN, "");}

    public String getKeyJenisKelamin(){return prefs.getString(KEY_JENIS_KELAMIN, "");}


    public  String getKeyNik() {
        return prefs.getString(KEY_NIK, "");
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();

        // user data
        user.put(KEY_ID, prefs.getString(KEY_ID, null));
        user.put(KEY_NAMA_DEPAN, prefs.getString(KEY_NAMA_DEPAN, null));
        user.put(KEY_NAMA_BELAKANG, prefs.getString(KEY_NAMA_BELAKANG, null));
        user.put(KEY_JENIS_KELAMIN, prefs.getString(KEY_JENIS_KELAMIN, null));
        user.put(KEY_JABATAN, prefs.getString(KEY_JABATAN, null));
        // return user
        return user;
    }

}
