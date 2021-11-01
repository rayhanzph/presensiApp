package id.my.attendance.Rest;

import id.my.attendance.Response.ResponLogPresensi;
import id.my.attendance.Response.ResponPresensi;
import id.my.attendance.Response.ResponRequestIzin;
import id.my.attendance.Response.ResponseJadwal;
import id.my.attendance.Response.ResponseUser;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseUser> login(
        @Field("email") String email,
        @Field("password") String password
    );

    @FormUrlEncoded
    @POST("jadwalpresensi")
    Call<ResponseJadwal> getjadwal(
            @Field("jabatan") String jabatan
    );

    @FormUrlEncoded
    @POST("logpresensiindex")
    Call<ResponLogPresensi> getLogpresensi(
        @Field("nama_pegawai") String nama_pegawai
    );

    @FormUrlEncoded
    @POST("logpresensi")
    Call<ResponLogPresensi> setLogPresensi(
            @Field("nama_pegawai") String nama_pegawai,
            @Field("kategori") String kategori,
            @Field("keterangan") String keterangan,
            @Field("lokasi") String address,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude
    );

    @FormUrlEncoded
    @POST("presensi")
    Call<ResponPresensi> getDataPresensi(
            @Field("nama_pegawai") String nama_pegawai
    );

    @FormUrlEncoded
    @POST("izin")
    Call<ResponRequestIzin> getDataIzin(
            @Field("pegawai_id") String pegawai_id
    );
}
