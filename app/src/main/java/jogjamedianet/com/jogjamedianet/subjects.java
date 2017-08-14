package jogjamedianet.com.jogjamedianet;

import android.content.Context;
import android.content.SharedPreferences;

import jogjamedianet.com.jogjamedianet.Prefs.UserInfo;

/**
 * Created by mery on 7/26/2017.
 */
public class subjects {
    UserInfo session;
    private String KEY_ID = session.getKeyID();
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
    private static final String KEY_IDPegawai = "ID_Pegawai";
    //`pelanggan`(`id`, `namaperusahaan`, `jenis_usaha`, `nama_pelanggan`, `alamatpelanggan`, `kelurahan_pelanggan`,
    // `kec_pelanggan`, `kota_pelanggan`, `kodepos_pelanggan`, `no_telp_pelanggan`, `no_fax_pelanggan`, `no_hp_pelanggan`,
    // `emailpelanggan`, `tanggal_lahir_pelanggan`, `jenis_kelamin_pelanggan`, `pekerjaan`, `no_identitas`,
    //`NPWP`, `layanan`, `cara_pembayaran`, `waktu_pembayaran`, `status_tempat_tinggal`, `ID_Pegawai`)

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public static String getKEY_IDPegawai() {
        return KEY_IDPegawai;
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

    public String getKEY_ID() {
        return KEY_ID;
    }

    public void setKEY_ID(String KEY_ID) {
        this.KEY_ID = KEY_ID;
    }
}
