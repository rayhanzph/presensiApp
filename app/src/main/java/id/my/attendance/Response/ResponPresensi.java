package id.my.attendance.Response;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.my.attendance.Model.PresensiModel;

//@Generated("jsonschema2pojo")
public class ResponPresensi {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<PresensiModel> presensi = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PresensiModel> getPresensi() {
        return presensi;
    }

    public void setData(List<PresensiModel> presensi) {
        this.presensi = presensi;
    }

}