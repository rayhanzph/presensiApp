package id.my.attendance.Model;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class JadwalModel {

    @SerializedName("jadwal_presensis_id")
    @Expose
    private String jadwalPresensisId;
    @SerializedName("jabatan")
    @Expose
    private String jabatan;
    @SerializedName("tanggal_jadwal")
    @Expose
    private String tanggalJadwal;
    @SerializedName("jadwal_masuk")
    @Expose
    private String jadwalMasuk;
    @SerializedName("jadwal_pulang")
    @Expose
    private String jadwalPulang;
    @SerializedName("batas_awal_masuk")
    @Expose
    private String batasAwalMasuk;
    @SerializedName("batas_akhir_masuk")
    @Expose
    private String batasAkhirMasuk;
    @SerializedName("batas_awal_pulang")
    @Expose
    private String batasAwalPulang;
    @SerializedName("batas_akhir_pulang")
    @Expose
    private String batasAkhirPulang;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public String getJadwalPresensisId() {
        return jadwalPresensisId;
    }

    public void setJadwalPresensisId(String jadwalPresensisId) {
        this.jadwalPresensisId = jadwalPresensisId;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getTanggalJadwal() {
        return tanggalJadwal;
    }

    public void setTanggalJadwal(String tanggalJadwal) {
        this.tanggalJadwal = tanggalJadwal;
    }

    public String getJadwalMasuk() {
        return jadwalMasuk;
    }

    public void setJadwalMasuk(String jadwalMasuk) {
        this.jadwalMasuk = jadwalMasuk;
    }

    public String getJadwalPulang() {
        return jadwalPulang;
    }

    public void setJadwalPulang(String jadwalPulang) {
        this.jadwalPulang = jadwalPulang;
    }

    public String getBatasAwalMasuk() {
        return batasAwalMasuk;
    }

    public void setBatasAwalMasuk(String batasAwalMasuk) {
        this.batasAwalMasuk = batasAwalMasuk;
    }

    public String getBatasAkhirMasuk() {
        return batasAkhirMasuk;
    }

    public void setBatasAkhirMasuk(String batasAkhirMasuk) {
        this.batasAkhirMasuk = batasAkhirMasuk;
    }

    public String getBatasAwalPulang() {
        return batasAwalPulang;
    }

    public void setBatasAwalPulang(String batasAwalPulang) {
        this.batasAwalPulang = batasAwalPulang;
    }

    public String getBatasAkhirPulang() {
        return batasAkhirPulang;
    }

    public void setBatasAkhirPulang(String batasAkhirPulang) {
        this.batasAkhirPulang = batasAkhirPulang;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
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