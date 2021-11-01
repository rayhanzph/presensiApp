package id.my.attendance.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.my.attendance.Model.PresensiModel;
import id.my.attendance.R;

public class PresensiAdapter extends RecyclerView.Adapter<PresensiAdapter.HolderData> {
    private Context context;
    private List<PresensiModel> listPresensi;

    public PresensiAdapter(Context context, List<PresensiModel> listPresensi) {
        this.context = context;
        this.listPresensi = listPresensi;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_logpresensi, parent, false);
        HolderData holder = new HolderData(layout);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        PresensiModel presensiModel = listPresensi.get(position);

//        String locMasuk = presensiModel.getLokasiMasuk();
//        locMasuk.substring(0, 20);

        holder.idPresensi.setText(presensiModel.getPresensisId());
        holder.tglPresensi.setText(presensiModel.getTanggalPresensi());
        holder.locPresensiPulang.setText(presensiModel.getLokasiPulang());
        holder.jamPresensiPulang.setText(presensiModel.getWaktuPulang());
        holder.tglPresensiPulang.setText("");
        holder.locPresensiMasuk.setText(presensiModel.getLokasiMasuk());
        holder.jamPresensiMasuk.setText(presensiModel.getWaktuMasuk());
        holder.tglPresensiMasuk.setText(presensiModel.getStatus());
    }

    @Override
    public int getItemCount() {
        return listPresensi.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView idPresensi, tglPresensi, locPresensiPulang, jamPresensiPulang, tglPresensiPulang;
        TextView locPresensiMasuk, jamPresensiMasuk, tglPresensiMasuk;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            idPresensi = itemView.findViewById(R.id.id_presensi);
            tglPresensi = itemView.findViewById(R.id.tanggal_presensi);
            locPresensiPulang = itemView.findViewById(R.id.loc_presensi_pulang);
            jamPresensiPulang = itemView.findViewById(R.id.jam_presensi_pulang);
            tglPresensiPulang = itemView.findViewById(R.id.tgl_presensi_pulang);
            locPresensiMasuk = itemView.findViewById(R.id.loc_presensi_masuk);
            jamPresensiMasuk = itemView.findViewById(R.id.jam_presensi_masuk);
            tglPresensiMasuk = itemView.findViewById(R.id.tgl_presensi_masuk);
        }
    }
}
