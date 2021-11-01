package id.my.attendance.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.my.attendance.R;
import id.my.attendance.Utils.SessionManager;

public class ProfileKaryawan extends AppCompatActivity {
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_karyawan);
        sessionManager = new SessionManager(this);

        if (!sessionManager.isLogginIN()){
            Intent intent = new Intent(ProfileKaryawan.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(), HomeKaryawan.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Requestijin:
                        startActivity(new Intent(getApplicationContext(), RequestIzin.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Rekap:
                        startActivity(new Intent(getApplicationContext(), RekapPresensi.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Profile:
                        return true;
                }
                return false;
            }
        });

        String profile = sessionManager.getUserDetail().get(SessionManager.EMAIL);
        TextView profil = findViewById(R.id.profil);
        profil.setText(profile);

        Button btnLogout = findViewById(R.id.logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logoutUser();
                Intent intent = new Intent(ProfileKaryawan.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}