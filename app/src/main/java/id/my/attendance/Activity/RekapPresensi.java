package id.my.attendance.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.my.attendance.R;
import id.my.attendance.Utils.SessionManager;

public class RekapPresensi extends AppCompatActivity {
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap_presensi);
        sessionManager = new SessionManager(this);

        if (!sessionManager.isLogginIN()){
            Intent intent = new Intent(RekapPresensi.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Rekap);
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
                        return true;
                    case R.id.Profile:
                        startActivity(new Intent(getApplicationContext(), ProfileKaryawan.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        String jabatan = sessionManager.getUserDetail().get(SessionManager.JABATAN);
        TextView jibitin = findViewById(R.id.jibitin);
        jibitin.setText(jabatan);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}