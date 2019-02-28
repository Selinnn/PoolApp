package com.poolapp.poolapp;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UyeBilgisi extends AppCompatActivity {
    TabLayout tabLayout_bilgi;
    ViewPager viewPager_bilgi;
    Context context = this;
    List<uyeYoklama> ListSil = new ArrayList<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uye_bilgisi);
        final String tc = getIntent().getExtras().getString("tc");
        tabLayout_bilgi = findViewById(R.id.tablayout_bilgi);
        viewPager_bilgi = findViewById(R.id.View_pager_bilgi);
        viewPager_bilgi.setAdapter(new Fragments_Bilgi(getSupportFragmentManager(),context));
        tabLayout_bilgi.setupWithViewPager(viewPager_bilgi);
        Toast.makeText(context,tc,Toast.LENGTH_LONG).show();

        fragment5.database = this.openOrCreateDatabase("PoolApp",MODE_PRIVATE,null);
        Cursor cursor = fragment5.database.rawQuery("Select ad, soyad, tc,cinsiyet,ameslek,bmeslek, okul, sinif, tel,\" +\n" +
                "\"mail, adres, yakintel, evtel, isadresi, kangrb, saglik, ameliyat, ilac, boy, kilo, koluzunlugu,\" +\n" +
                "\"bacak, omuz, gun, saat, yuzme, antrenor, lisansno, yarismalar from users where tc = ?",new String[]{tc});
        int ixad = cursor.getColumnIndex("ad");
        int ixsoyad = cursor.getColumnIndex("soyad");
        int ixtc = cursor.getColumnIndex("tc");
        int ixcins = cursor.getColumnIndex("cinsiyet");
        int ixameslek = cursor.getColumnIndex("ameslek");
        int bmeslek = cursor.getColumnIndex("bmeslek");
        int ixokul = cursor.getColumnIndex("okul");
        int ixsinif = cursor.getColumnIndex("sinif");
        int ixtel = cursor.getColumnIndex("tel");
        int ixmail = cursor.getColumnIndex("mail");
        int ixadres = cursor.getColumnIndex("adres");
        int ixyakintel = cursor.getColumnIndex("yakintel");
        int ixevtel = cursor.getColumnIndex("evtel");
        int ixisadresi = cursor.getColumnIndex("isadresi");
        int ixkangrb = cursor.getColumnIndex("kangrb");
        int ixsaglik = cursor.getColumnIndex("saglik");
        int ixameliyat = cursor.getColumnIndex("ameliyat");
        int ixilac = cursor.getColumnIndex("ilac");
        int ixboy = cursor.getColumnIndex("boy");
        int ixkilo = cursor.getColumnIndex("kilo");
        int ixkoluzunlugu = cursor.getColumnIndex("koluzunlugu");
        int izbacak = cursor.getColumnIndex("bacak");
        int ixomuz = cursor.getColumnIndex("omuz");
        int ixgun = cursor.getColumnIndex("gun");
        int ixsaat = cursor.getColumnIndex("saat");
        int ixyuzme = cursor.getColumnIndex("yuzme");
        int ixantrenor = cursor.getColumnIndex("antrenor");
        int ixlisansno = cursor.getColumnIndex("lisansno");
        int ixyarismalar = cursor.getColumnIndex("yarismalar");

        if (cursor.moveToFirst()) {

            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                uyeYoklama yeni = new uyeYoklama(cursor.getString(ixad), cursor.getString(ixsoyad), cursor.getString(ixtc));
                ListSil.add(yeni);
                cursor.moveToNext();
            }
    }
    }
}
