package id.my.attendance.Response;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.my.attendance.Model.JadwalModel;

//@Generated("jsonschema2pojo")
public class ResponseJadwal {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private JadwalModel jadwal;

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

    public JadwalModel getJadwal() {
        return jadwal;
    }

    public void setData(JadwalModel jadwal) {
        this.jadwal = jadwal;
    }

}