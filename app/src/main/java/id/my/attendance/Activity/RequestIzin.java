package id.my.attendance.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

//import id.my.attendance.Adapter.RequestIzinAdapter;
import id.my.attendance.Adapter.RequestIzinAdapter;
import id.my.attendance.Model.RequestIzinModel;
import id.my.attendance.R;
import id.my.attendance.Response.ResponRequestIzin;
import id.my.attendance.Rest.ApiClient;
import id.my.attendance.Rest.ApiInterface;
import id.my.attendance.Utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestIzin extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<RequestIzinModel> list = new ArrayList<>();

    SessionManager sessionManager;
    ApiInterface apiInterface;




    String pegawai_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_izin);

        sessionManager = new SessionManager(this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        recyclerView = findViewById(R.id.rv_requestizin);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        if (!sessionManager.isLogginIN()){
            Intent intent = new Intent(RequestIzin.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Requestijin);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(), HomeKaryawan.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Requestijin:
                        return true;
                    case R.id.Rekap:
                        startActivity(new Intent(getApplicationContext(), RekapPresensi.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Profile:
                        startActivity(new Intent(getApplicationContext(), ProfileKaryawan.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        String email = sessionManager.getUserDetail().get(SessionManager.EMAIL);
        pegawai_id = sessionManager.getUserDetail().get(SessionManager.ID);


        apiInterface.getDataIzin(pegawai_id).enqueue(new Callback<ResponRequestIzin>() {
            @Override
            public void onResponse(Call<ResponRequestIzin> call, Response<ResponRequestIzin> response) {
                if (response.isSuccessful()){
                    //List<RequestIzinModel> dataizin = response.body().getIzin();

                    list = response.body().getIzin();
                    adapter = new RequestIzinAdapter(RequestIzin.this, list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponRequestIzin> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}