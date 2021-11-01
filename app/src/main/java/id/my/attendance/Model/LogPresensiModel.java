package id.my.attendance.Model;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class LogPresensiModel {

    @SerializedName("logpresensi_id")
    @Expose
    private String logpresensiId;
    @SerializedName("nama_pegawai")
    @Expose
    private String namaPegawai;
    @SerializedName("waktu")
    @Expose
    private String waktu;
    @SerializedName("tanggal_log_presensi")
    @Expose
    private String tanggalLogPresensi;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public String getLogpresensiId() {
        return logpresensiId;
    }

    public void setLogpresensiId(String logpresensiId) {
        this.logpresensiId = logpresensiId;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getTanggalLogPresensi() {
        return tanggalLogPresensi;
    }

    public void setTanggalLogPresensi(String tanggalLogPresensi) {
        this.tanggalLogPresensi = tanggalLogPresensi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

}