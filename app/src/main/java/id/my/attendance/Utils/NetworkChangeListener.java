package id.my.attendance.Utils;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import id.my.attendance.R;

public class NetworkChangeListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Common.isConnectedToInternet(context)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layout_dialog = LayoutInflater.from(context).inflate(R.layout.no_internet_layout, null);
            builder.setView(layout_dialog);

            Button btnReload = layout_dialog.findViewById(R.id.reload);

            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);

            dialog.getWindow().setGravity(Gravity.CENTER);

            btnReload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    onReceive(context, intent);
                }
            });
        }
    }
}
