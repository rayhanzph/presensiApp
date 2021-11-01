package id.my.attendance.Response;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.my.attendance.Model.LogPresensiModel;

//@Generated("jsonschema2pojo")
public class ResponLogPresensi {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private LogPresensiModel logPresensi;

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

    public LogPresensiModel getLogPresensi() {
        return logPresensi;
    }

    public void setLogPresensi(LogPresensiModel logPresensi) {
        this.logPresensi = logPresensi;
    }

}