package id.my.attendance.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.my.attendance.Model.RequestIzinModel;
import id.my.attendance.R;

public class RequestIzinAdapter extends RecyclerView.Adapter<RequestIzinAdapter.HolderData>{
    private Context context;
    private List<RequestIzinModel> izinModelList;

    public RequestIzinAdapter(Context context, List<RequestIzinModel> izinModelList) {
        this.context = context;
        this.izinModelList = izinModelList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_requestizin , parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        RequestIzinModel requestIzinModel = izinModelList.get(position);

        holder.tanggalPengajuan.setText(requestIzinModel.getTglPengajuanIzin());
        holder.jenisIzin.setText(requestIzinModel.getJenisIzin());
        holder.keteranganIzin.setText(requestIzinModel.getKeteranganIzin());
        holder.tanggalIzin.setText(requestIzinModel.getTglAwalIzin());
        holder.statusIzin.setText(requestIzinModel.getStatusPengajuan());
    }

    @Override
    public int getItemCount() {
        return izinModelList.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tanggalPengajuan, jenisIzin, keteranganIzin, tanggalIzin, statusIzin;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tanggalPengajuan = itemView.findViewById(R.id.tgl_pengajuan);
            jenisIzin = itemView.findViewById(R.id.jenis_izin);
            keteranganIzin = itemView.findViewById(R.id.keterangan_izin);
            tanggalIzin = itemView.findViewById(R.id.tgl_izin);
            statusIzin = itemView.findViewById(R.id.status_izin);
        }
    }
}
