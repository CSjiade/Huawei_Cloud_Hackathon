package com.misa.misa.view;

import static com.misa.misa.utils.Constant.PRODUCTID;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.marwaeltayeb.souq.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class DocumentActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String obs = intent.getStringExtra("obs");
        Log.d("obs",obs);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        iv = findViewById(R.id.iv_your_image);
        Glide.with(this).load(obs).into(iv);



    }
}