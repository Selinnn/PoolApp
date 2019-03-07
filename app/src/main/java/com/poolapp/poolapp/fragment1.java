package com.poolapp.poolapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Calendar;

public class fragment1 extends Fragment {

    private Button userImage;
    public static ImageView imageView;
    private static final int IMAGE_PICK = 1;
    private static final int IMAGE_CAPTURE = 2;
    Button btndate;
    static TextView txtDate;
    static EditText ad_edit;
    static EditText soyad_edit;
    static EditText tc_edit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment1_layout, container, false);
        ad_edit = myView.findViewById(R.id.ad_edit);
        soyad_edit = myView.findViewById(R.id.soyad_edit);
        tc_edit = myView.findViewById(R.id.tc_edit);
        btndate = myView.findViewById(R.id.btn_datepicker);
        txtDate = myView.findViewById(R.id.date_txt);
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar simdikiZaman = Calendar.getInstance();
                int yil = simdikiZaman.get(Calendar.YEAR); // Burada güncel yılı alıyoruz.
                int ay = simdikiZaman.get(Calendar.MONTH);
                int gun = simdikiZaman.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //Dialogdan yapılan seçimin ekrana bastırılması
                       txtDate.setText(dayOfMonth+"/"+(month + 1)+"/"+year);
                    }
                },yil,ay,gun);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.setTitle("Tarih Seçiniz");
                datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE,"Tamam",datePickerDialog);
                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"İptal",datePickerDialog);
                datePickerDialog.show();
            }
        });
        return myView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userImage = getView().findViewById(R.id.fotoEkle);
        imageView = getView().findViewById(R.id.imageView);
        userImage.setOnClickListener(new FotoButon());
    }
    class FotoButon implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setNeutralButton("Fotoğraf Çek", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if(cameraIntent.resolveActivity(getActivity().getPackageManager()) != null)
                        startActivityForResult(cameraIntent,IMAGE_CAPTURE);
                }
            });
      // Must call show() prior to fetching text view
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == IMAGE_CAPTURE)
        { Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            imageView.setImageBitmap(bitmap);
        }
    }
}

