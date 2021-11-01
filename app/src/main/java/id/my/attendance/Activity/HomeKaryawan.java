package id.my.attendance.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.my.attendance.Adapter.PresensiAdapter;
import id.my.attendance.Model.JadwalModel;
import id.my.attendance.Model.PresensiModel;
import id.my.attendance.R;
import id.my.attendance.Response.ResponLogPresensi;
import id.my.attendance.Response.ResponPresensi;
import id.my.attendance.Response.ResponseJadwal;
import id.my.attendance.Rest.ApiClient;
import id.my.attendance.Rest.ApiInterface;
import id.my.attendance.Utils.NetworkChangeListener;
import id.my.attendance.Utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeKaryawan extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private final static int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<PresensiModel> listPresensiModel = new ArrayList<>();


    SessionManager sessionManager;
    ApiInterface apiInterface;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


//    SessionJadwal sessionJadwal;

    String jabatan, nama;
    //    String jadwal_masuk, jadwal_pulang, batasAwalMasuk, batasAkhirMasuk, batasAwalPulang, batasAkhirPulang, location;
    String jadwal_masuk, jadwal_pulang;
    String batasAwalMasuk = "08:00:00", batasAkhirMasuk = "09:15:00", batasAwalPulang = "16:00:00", batasAkhirPulang = "17:30:00", location;

    double latitude_maps, longitude_maps;
    String latitude, longitude;
    String address, lokasi, keteranganMasuk, keteranganPulang;
    String realtime, realdate;
    String statusLogMasuk, statusLogPulang;
    Date realJam, btsAwalMasuk, btsAkhirMasuk, btsAwalPulang, btsAkhirPulang;
    int presensiCode = 0;

    TextView jadwalMasuk, jadwalPulang, Pesan, etKeterangan, etKeteranganPulang, Username;
    Button btnMasuk, btnPulang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_karyawan);

        sessionManager = new SessionManager(this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        sessionJadwal = new SessionJadwal(this);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        progressBar = findViewById(R.id.pb_bar);
        recyclerView = findViewById(R.id.rv_logpresensi);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        Pesan = findViewById(R.id.pesan);
        Username = findViewById(R.id.username);

        //get data from sessionManager
        jabatan = sessionManager.getUserDetail().get(SessionManager.JABATAN);
        nama = sessionManager.getUserDetail().get(SessionManager.NAME);
        Username.setText(nama);


        lokasi = address;

        //cek apakah user sudah pernah login atau belum
        //jika belum maka alihkan ke halaman login
        if (!sessionManager.isLogginIN()) {
            Intent intent = new Intent(HomeKaryawan.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home:
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
                        startActivity(new Intent(getApplicationContext(), ProfileKaryawan.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        TextView jam = findViewById(R.id.jam);
        TextView tanggal = findViewById(R.id.tanggal);

        //menginisialisasi button variable
        btnMasuk = findViewById(R.id.btnMasuk);
        btnPulang = findViewById(R.id.btnPulang);
        jadwalMasuk = findViewById(R.id.tvjadwalMasuk);
        jadwalPulang = findViewById(R.id.tvjadwalPulang);
        Pesan = findViewById(R.id.pesan);


        jadwalMasuk.setText(jadwal_masuk);
        jadwalPulang.setText(jadwal_pulang);

        if (sessionManager.getUserDetail() == null) {
//            Log.i("session", String.valueOf(sessionManager.getUserDetail()));
            Intent intent = new Intent(HomeKaryawan.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        } else {
            //method untuk mengambil data jadwal
            getJadwal();
            getPresensi();
            getLogPresensi();
            getCurrentLocation();
        }


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
//                sessionJadwal.clearJadwal();
                getJadwal();
                getLogPresensi();
                getPresensi();
                getCurrentLocation();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        //get realtime jam dan tanggal
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

//                                getJadwal();
//                                Toast.makeText(HomeKaryawan.this, "btsAwal : "+jadwal_masuk, Toast.LENGTH_SHORT).show();

                                Date calendar = Calendar.getInstance().getTime();
                                SimpleDateFormat simpleFormatTime = new SimpleDateFormat("HH:mm:ss");
                                SimpleDateFormat simpleFormaTanggal = new SimpleDateFormat("EEEE, dd-MM-yyyy");
                                realtime = simpleFormatTime.format(calendar);
                                realdate = simpleFormaTanggal.format(calendar);
//
                                jam.setText(realtime);
                                tanggal.setText(realdate);

                                try {
                                    SimpleDateFormat sdF = new SimpleDateFormat("HH:mm:ss");
//                                    Date realjam = sdF.parse(sdF.format(new Date()));
                                    Date realjam = sdF.parse(realtime);
                                    String wal = "07:00:00";
                                    String khir = "07:10:00";
                                    Date awal = sdF.parse(wal);
                                    Date akhir = sdF.parse(khir);
                                    btsAwalMasuk = sdF.parse(batasAwalMasuk);
                                    btsAkhirMasuk = sdF.parse(batasAkhirMasuk);
                                    btsAwalPulang = sdF.parse(batasAwalPulang);
                                    btsAkhirPulang = sdF.parse(batasAkhirPulang);

//                                    Toast.makeText(HomeKaryawan.this, "awal masuk : "+statusLogPulang, Toast.LENGTH_SHORT).show();


                                    if (realjam.after(awal) && realjam.before(btsAwalMasuk)) {
                                        presensiCode = 0;
                                        btnMasuk.setEnabled(false);
                                        Pesan.setText("Ups.. belum waktunya presensi");
                                    }


                                    if (realjam.after(btsAwalMasuk) && !statusLogMasuk.equals("ada")) {
                                        btnMasuk.setEnabled(true);
                                        Pesan.setText("Waktunya presensi...");
                                    }else if(statusLogMasuk.equals("ada")){
                                        btnMasuk.setEnabled(false);
                                        Pesan.setText("Waktunya kerja...");
                                    }else if (realjam.after(btsAkhirMasuk)) {
                                        btnMasuk.setEnabled(false);
                                        Pesan.setText("Waktunya kerja...");
                                    } else {
                                        btnMasuk.setEnabled(false);
                                        Pesan.setText("Waktunya kerja...");
                                    }


                                    if (realjam.after(btsAwalPulang) && statusLogMasuk.equals("ada")) {
                                        if (statusLogPulang == null) {
                                            btnPulang.setEnabled(true);
                                            Pesan.setText("Waktunya pulang...");
                                        } else if (statusLogPulang.equals("updated")) {
                                            btnPulang.setEnabled(false);
                                            Pesan.setText("Sampai jumpa..");
                                        }

                                    } else if (realjam.after(btsAkhirPulang)) {
                                        btnPulang.setEnabled(false);
                                        Pesan.setText("Sampai jumpa..");
                                    }

//                                    Toast.makeText(HomeKaryawan.this, "presc : "+statusLogPulang, Toast.LENGTH_SHORT).show();

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                } catch (Exception e) {
                    jam.setText("00.00");
                }
            }
        };
        thread.start();


        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check GPS Permission
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            HomeKaryawan.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_LOCATION_PERMISSION
                    );
                }

                getCurrentLocation();


                //menampilkan bottom sheetview Masuk
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(HomeKaryawan.this, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.bs_presensi_masuk,
                                (LinearLayout) findViewById(R.id.bottomSheetContainer)
                        );


                TextView lokasi = (TextView) bottomSheetView.findViewById(R.id.lokasi_presensi_masuk);
                lokasi.setText(address);

                bottomSheetView.findViewById(R.id.btn_presensi).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        etKeterangan = (EditText) bottomSheetView.findViewById(R.id.etKeterangan);
                        keteranganMasuk = etKeterangan.getText().toString();

                        presensiMasuk();
                        getPresensi();

//                        Toast.makeText(HomeKaryawan.this, "Presensi berhasil : "+keteranganMasuk, Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                        //btnMasuk.setEnabled(false);
                    }
                });


                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();

            }
        });


        btnPulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check GPS Permission
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            HomeKaryawan.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_LOCATION_PERMISSION
                    );
                }

                getCurrentLocation();
//                Toast.makeText(HomeKaryawan.this, "Location : "+address, Toast.LENGTH_SHORT).show();

                //menampilkan bottom sheet view
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(HomeKaryawan.this, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.bs_presensi_pulang,
                                (LinearLayout) findViewById(R.id.bottomSheetContainerPulang)
                        );

                TextView lokasipulang = (TextView) bottomSheetView.findViewById(R.id.lokasi_presensi_pulang);
                lokasipulang.setText(address);

                bottomSheetView.findViewById(R.id.btn_presensiPulang).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        etKeteranganPulang = (EditText) bottomSheetView.findViewById(R.id.etKeteranganPulang);
                        keteranganPulang = etKeteranganPulang.getText().toString();

                        presensiPulang();

                        bottomSheetDialog.dismiss();
                        btnPulang.setEnabled(false);
                    }
                });

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });


    }

    private void getLogPresensi() {
        apiInterface.getLogpresensi(nama).enqueue(new Callback<ResponLogPresensi>() {
            @Override
            public void onResponse(Call<ResponLogPresensi> call, Response<ResponLogPresensi> response) {
                if (response.isSuccessful()) {
                    statusLogMasuk = response.body().getStatus();
//                    Toast.makeText(HomeKaryawan.this, "Status : "+status, Toast.LENGTH_LONG).show();
//                    Log.i("Status : ",status);
                    if (statusLogMasuk.equals("ada")) {
//                        btnMasuk.setEnabled(false);
//                        Pesan.setText("Waktunya kerja..");

                    } else {
                        btnMasuk.setEnabled(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponLogPresensi> call, Throwable t) {
                Toast.makeText(HomeKaryawan.this, "Error getLogPresensi" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPresensi() {

        apiInterface.getDataPresensi(nama).enqueue(new Callback<ResponPresensi>() {
            @Override
            public void onResponse(Call<ResponPresensi> call, Response<ResponPresensi> response) {
                if (response.isSuccessful()) {
                    listPresensiModel = response.body().getPresensi();

                    adapter = new PresensiAdapter(HomeKaryawan.this, listPresensiModel);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponPresensi> call, Throwable t) {

            }
        });

    }

    private void presensiMasuk() {
        String kategori = "masuk";
        String keterangan = keteranganMasuk;

        apiInterface.setLogPresensi(nama, kategori, keterangan, address, latitude, longitude).enqueue(new Callback<ResponLogPresensi>() {
            @Override
            public void onResponse(Call<ResponLogPresensi> call, Response<ResponLogPresensi> response) {
                if (response.isSuccessful()) {
                    String status = response.body().getStatus();
                    if (status.equals("duplicate")) {
                        Toast.makeText(HomeKaryawan.this, "Upss kamu sudah presensi masuk hari ini", Toast.LENGTH_SHORT).show();
                        btnMasuk.setEnabled(false);
                    } else {
                        if (status.equals("berhasil")) {
                            Toast.makeText(HomeKaryawan.this, "Presensi masuk berhasil", Toast.LENGTH_SHORT).show();
//                            presensiCode = 1;
                            Pesan.setText("Waktunya kerja..");
                            btnMasuk.setEnabled(false);
                            getLogPresensi();
                            getPresensi();
                        } else {
                            Toast.makeText(HomeKaryawan.this, "Gagal presensi", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponLogPresensi> call, Throwable t) {
                Toast.makeText(HomeKaryawan.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void presensiPulang() {
        String kategori = "pulang";
        String keterangan = keteranganPulang;

        apiInterface.setLogPresensi(nama, kategori, keterangan, address, latitude, longitude).enqueue(new Callback<ResponLogPresensi>() {
            @Override
            public void onResponse(Call<ResponLogPresensi> call, Response<ResponLogPresensi> response) {
                if (response.isSuccessful()) {
                    statusLogPulang = response.body().getStatus();
                    if (statusLogPulang.equals("updated")) {
                        Toast.makeText(HomeKaryawan.this, "Presensi pulang berhasil", Toast.LENGTH_SHORT).show();
                        btnPulang.setEnabled(false);
                        Pesan.setText("Sampai jumpa..");
                        getPresensi();

                    } else {
                        Toast.makeText(HomeKaryawan.this, "Gagal presensi ", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponLogPresensi> call, Throwable t) {
                Toast.makeText(HomeKaryawan.this, "Error prePul : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        getJadwal();
        getLogPresensi();
        getCurrentLocation();
    }

    private void getJadwal() {
        progressBar.setVisibility(View.VISIBLE);

        if (jabatan == null) {
//            Toast.makeText(this, "Please login..", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomeKaryawan.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        //ambil data jadwal dari database
        apiInterface.getjadwal(jabatan).enqueue(new Callback<ResponseJadwal>() {
            @Override
            public void onResponse(Call<ResponseJadwal> call, Response<ResponseJadwal> response) {
                if (response.isSuccessful()) {
                    String status = response.body().getStatus();
                    if (status.equals("berhasil")) {
                        JadwalModel dataJadwal = response.body().getJadwal();

                        String stus = response.body().getStatus();

                        if (stus.equals("berhasil")) {
                            //mengisi variable string dengan data dari model
                            jadwal_masuk = dataJadwal.getJadwalMasuk();

                            jadwal_pulang = dataJadwal.getJadwalPulang();

                            batasAwalMasuk = dataJadwal.getBatasAwalMasuk();

                            batasAkhirMasuk = dataJadwal.getBatasAkhirMasuk();

                            batasAwalPulang = dataJadwal.getBatasAwalPulang();

                            batasAkhirPulang = dataJadwal.getBatasAkhirPulang();

                            //mengisi textview dengan data variable string
                            jadwalMasuk.setText(jadwal_masuk);
                            jadwalPulang.setText(jadwal_pulang);
                        } else {
                            Toast.makeText(HomeKaryawan.this, "Error : " + stus, Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        //mengisi textview dengan data variable string
                        jadwalMasuk.setText("00:00");
                        jadwalPulang.setText("00:00");

                        Pesan.setText("Jadwalnya belum dibikin");
                    }


                    //tampilkan progress bar saat melakukan refresh
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseJadwal> call, Throwable t) {
                Toast.makeText(HomeKaryawan.this, "Error schedule : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(HomeKaryawan.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(HomeKaryawan.this).removeLocationUpdates(this);
                        if (locationRequest != null && locationResult.getLocations().size() > 0) {
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            latitude_maps = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            longitude_maps = locationResult.getLocations().get(latestLocationIndex).getLongitude();
                            latitude = String.valueOf(latitude_maps);
                            longitude = String.valueOf(longitude_maps);

                            try {
                                Geocoder geocoder = new Geocoder(HomeKaryawan.this, Locale.getDefault());
                                List<Address> addresses = geocoder.getFromLocation(latitude_maps, longitude_maps, 1);
                                address = addresses.get(0).getAddressLine(0);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, Looper.getMainLooper());
    }


    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}