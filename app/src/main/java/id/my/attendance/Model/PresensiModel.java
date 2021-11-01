package id.my.attendance.Model;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class PresensiModel {

    @SerializedName("presensis_id")
    @Expose
    private String presensisId;
    @SerializedName("nama_pegawai")
    @Expose
    private String namaPegawai;
    @SerializedName("status_presensi")
    @Expose
    private String statusPresensi;
    @SerializedName("tanggal_presensi")
    @Expose
    private String tanggalPresensi;
    @SerializedName("waktu_masuk")
    @Expose
    private String waktuMasuk;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("keterangan_masuk")
    @Expose
    private String keteranganMasuk;
    @SerializedName("waktu_pulang")
    @Expose
    private String waktuPulang;
    @SerializedName("keterangan_pulang")
    @Expose
    private String keteranganPulang;

    @SerializedName("lokasi_masuk")
    @Expose
    private String lokasiMasuk;
    @SerializedName("latitude_masuk")
    @Expose
    private String latitudeMasuk;
    @SerializedName("longitude_masuk")
    @Expose
    private String longitudeMasuk;
    @SerializedName("latitude_pulang")
    @Expose
    private String latitudePulang;
    @SerializedName("longitude_pulang")
    @Expose
    private String longitudePulang;

    @SerializedName("lokasi_pulang")
    @Expose
    private String lokasiPulang;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public String getPresensisId() {
        return presensisId;
    }

    public void setPresensisId(String presensisId) {
        this.presensisId = presensisId;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getStatusPresensi() {
        return statusPresensi;
    }

    public void setStatusPresensi(String statusPresensi) {
        this.statusPresensi = statusPresensi;
    }

    public String getTanggalPresensi() {
        return tanggalPresensi;
    }

    public void setTanggalPresensi(String tanggalPresensi) {
        this.tanggalPresensi = tanggalPresensi;
    }

    public String getWaktuMasuk() {
        return waktuMasuk;
    }

    public void setWaktuMasuk(String waktuMasuk) {
        this.waktuMasuk = waktuMasuk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeteranganMasuk() {
        return keteranganMasuk;
    }

    public void setKeteranganMasuk(String keteranganMasuk) {
        this.keteranganMasuk = keteranganMasuk;
    }

    public String getWaktuPulang() {
        return waktuPulang;
    }

    public void setWaktuPulang(String waktuPulang) {
        this.waktuPulang = waktuPulang;
    }

    public String getKeteranganPulang() {
        return keteranganPulang;
    }

    public void setKeteranganPulang(String keteranganPulang) {
        this.keteranganPulang = keteranganPulang;
    }


    public String getLokasiMasuk() {
        return lokasiMasuk;
    }

    public void setLokasiMasuk(String lokasiMasuk) {
        this.lokasiMasuk = lokasiMasuk;
    }


    public String getLatitudeMasuk() {
        return latitudeMasuk;
    }

    public void setLatitudeMasuk(String latitudeMasuk) {
        this.latitudeMasuk = latitudeMasuk;
    }

    public String getLongitudeMasuk() {
        return longitudeMasuk;
    }

    public void setLongitudeMasuk(String longitudeMasuk) {
        this.longitudeMasuk = longitudeMasuk;
    }

    public String getLatitudePulang() {
        return latitudePulang;
    }

    public void setLatitudePulang(String latitudePulang) {
        this.latitudePulang = latitudePulang;
    }

    public String getLongitudePulang() {
        return longitudePulang;
    }

    public void setLongitudePulang(String longitudePulang) {
        this.longitudePulang = longitudePulang;
    }


    public String getLokasiPulang() {
        return lokasiPulang;
    }

    public void setLokasiPulang(String lokasiPulang) {
        this.lokasiPulang = lokasiPulang;
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