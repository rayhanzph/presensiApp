package id.my.attendance.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.my.attendance.Model.UserModel;
import id.my.attendance.R;
import id.my.attendance.Response.ResponseUser;
import id.my.attendance.Rest.ApiClient;
import id.my.attendance.Rest.ApiInterface;
import id.my.attendance.Utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    SessionManager sessionManager;

    EditText etEmail, etPassword;
    Button btnLogin;
    String email,password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =  etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (email.trim().equals("")){
                    etEmail.setError("Email wajib diisi");
                }else if (password.trim().equals("")){
                    etPassword.setError("Password wajib diisi");
                }else{
                    loginUser();
                }
            }
        });
    }

    private void loginUser() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        apiInterface.login(email,password).enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if (response.isSuccessful()){
                    UserModel sessionData = response.body().getUser();
                    //mengambil data dari ResponseModel
                    String status = response.body().getStatus();
                    //mengambil status dari ResponseModel
    
                    if (status.equals("berhasil")){
                        sessionManager.createLoginSession(sessionData);
                        String jabatan = sessionData.getJabatan();

//                        if (jabatan.equals("Karyawan")){
                            Intent intent = new Intent(MainActivity.this, HomeKaryawan.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(intent);
                            finish();
//                        }else if (jabatan.equals("Direktur")){
//                            Toast.makeText(MainActivity.this, "Selamat datang pak direktur", Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(MainActivity.this, "Selamat datang "+jabatan, Toast.LENGTH_SHORT).show();
//                        }
                    }else if (status.equals("salah")){
                        Toast.makeText(MainActivity.this, "Password Salah", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this, "User tidak terdaftar", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}