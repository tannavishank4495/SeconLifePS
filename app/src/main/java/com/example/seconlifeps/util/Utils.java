package com.example.seconlifeps.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.seconlifeps.R;

public class Utils {
    public static void showSelectDialog(Context context, final DialogClickEvent dialogClickEvent) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setTitle("Select one option");
//        val builder = AlertDialog.Builder(context)

        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_select_wifi_cellular, null, false);

        dialog.setContentView(dialogView);

        dialog.show();

        TextView txtGallery = dialogView.findViewById(R.id.txt_gallery);
        TextView txtCamera = dialogView.findViewById(R.id.txt_camera);

        txtGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogClickEvent.negativeClick();
                dialog.dismiss();
            }
        });

        txtCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogClickEvent.positiveClick();
                dialog.dismiss();
            }
        });

    }

    public interface DialogClickEvent {

        void positiveClick();

        void negativeClick();
    }

}
