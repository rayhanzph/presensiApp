package id.my.attendance.Response;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.my.attendance.Model.RequestIzinModel;

//@Generated("jsonschema2pojo")
public class ResponRequestIzin {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<RequestIzinModel> izin = null;

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

    public List<RequestIzinModel> getIzin() {
        return izin;
    }

    public void setData(List<RequestIzinModel> izin) {
        this.izin = izin;
    }

}