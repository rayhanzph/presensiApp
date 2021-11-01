package id.my.attendance.Rest;

import id.my.attendance.Config.ServerConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String baseURL = ServerConfig.API_ENDPOINT;

    public static Retrofit retrofit;

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
