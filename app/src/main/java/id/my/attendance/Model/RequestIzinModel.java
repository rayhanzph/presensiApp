package id.my.attendance.Model;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class RequestIzinModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jenis_izin")
    @Expose
    private String jenisIzin;
    @SerializedName("pegawai_id")
    @Expose
    private Integer pegawaiId;
    @SerializedName("keterangan_izin")
    @Expose
    private String keteranganIzin;
    @SerializedName("tgl_pengajuan_izin")
    @Expose
    private String tglPengajuanIzin;
    @SerializedName("tgl_awal_izin")
    @Expose
    private String tglAwalIzin;
    @SerializedName("tgl_akhir_izin")
    @Expose
    private String tglAkhirIzin;
    @SerializedName("tgl_persetujuan_izin")
    @Expose
    private String tglPersetujuanIzin;
    @SerializedName("bukti")
    @Expose
    private String bukti;
    @SerializedName("status_pengajuan")
    @Expose
    private Integer statusPengajuan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJenisIzin() {
        return jenisIzin;
    }

    public void setJenisIzin(String jenisIzin) {
        this.jenisIzin = jenisIzin;
    }

    public Integer getPegawaiId() {
        return pegawaiId;
    }

    public void setPegawaiId(Integer pegawaiId) {
        this.pegawaiId = pegawaiId;
    }

    public String getKeteranganIzin() {
        return keteranganIzin;
    }

    public void setKeteranganIzin(String keteranganIzin) {
        this.keteranganIzin = keteranganIzin;
    }

    public String getTglPengajuanIzin() {
        return tglPengajuanIzin;
    }

    public void setTglPengajuanIzin(String tglPengajuanIzin) {
        this.tglPengajuanIzin = tglPengajuanIzin;
    }

    public String getTglAwalIzin() {
        return tglAwalIzin;
    }

    public void setTglAwalIzin(String tglAwalIzin) {
        this.tglAwalIzin = tglAwalIzin;
    }

    public String getTglAkhirIzin() {
        return tglAkhirIzin;
    }

    public void setTglAkhirIzin(String tglAkhirIzin) {
        this.tglAkhirIzin = tglAkhirIzin;
    }

    public String getTglPersetujuanIzin() {
        return tglPersetujuanIzin;
    }

    public void setTglPersetujuanIzin(String tglPersetujuanIzin) {
        this.tglPersetujuanIzin = tglPersetujuanIzin;
    }

    public String getBukti() {
        return bukti;
    }

    public void setBukti(String bukti) {
        this.bukti = bukti;
    }

    public Integer getStatusPengajuan() {
        return statusPengajuan;
    }

    public void setStatusPengajuan(Integer statusPengajuan) {
        this.statusPengajuan = statusPengajuan;
    }

}