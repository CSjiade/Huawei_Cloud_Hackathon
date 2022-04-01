package com.misa.misa.view;

import static com.misa.misa.storage.LanguageUtils.loadLocale;
import static com.misa.misa.utils.Constant.PRODUCTID;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.marwaeltayeb.souq.R;
import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.misa.misa.adapter.ReviewAdapter;
import com.misa.misa.viewmodel.ReviewViewModel;
import com.misa.misa.viewmodel.ShippingViewModel;
import com.misa.misa.viewmodel.ViewProfileViewModel;

import org.pmml4s.data.Datetime;
import org.pmml4s.model.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewProfileActivity extends AppCompatActivity {


    private TextView nameOfUser;
    private TextView emailOfUser;
    private TextView myDescription;
    private TextView myTokens;
    private  TextView myCreate;
    private ViewProfileViewModel viewProfileViewModel;
    private String productOwner;
    private Button btn_document;
    private String obsDocument;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");
        Intent intent = getIntent();
        loadLocale(this);
        productOwner = intent.getStringExtra(PRODUCTID);
        viewProfileViewModel = ViewModelProviders.of(this).get(ViewProfileViewModel.class);
        nameOfUser = findViewById(R.id.nameOfUser);
        emailOfUser = findViewById(R.id.emailOfUser);
        myDescription = findViewById(R.id.myDescription);
        myTokens = findViewById(R.id.myTokens);
        myCreate = findViewById(R.id.myCreate);
        btn_document = findViewById(R.id.btn_Document);



        getProfile();


//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date d = sdf.parse(time);
//        String formattedTime = output.format(d);
    }

    //2022-03-14T08:56:31.000Z

    private void getProfile() {
        viewProfileViewModel.getProfile(productOwner).observe(this, profileApiResponse -> {
            if (profileApiResponse != null) {
                nameOfUser.setText(profileApiResponse.getName());
                emailOfUser.setText(profileApiResponse.getEmail());
                myDescription.setText(profileApiResponse.getDetails());
                myTokens.setText("Accumulated " + String.valueOf(profileApiResponse.getTokens()) + " Misa" + " Tokens!");
                String time = profileApiResponse.getDate();
                String[] part = time.split(" ");
                String part1 = part[1];
                String part2 = part[2];
                String part3 = part[3];
                myCreate.setText("Account created on: " + part1 + " " + part2 + " " + part3);
                obsDocument = profileApiResponse.getDocument();
                btn_document.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                    public final void onClick(View it) {
                        Intent docuIntent = new Intent(ViewProfileActivity.this, DocumentActivity.class);
                        docuIntent.putExtra("obs",obsDocument);
                        startActivity(docuIntent);

                    }
                }));



//                SimpleDateFormat sdf = new SimpleDateFormat("%Y-%m-%d %H:%M:%S.%fZ");
//                SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
//                Date d = null;
//                try {
//                    d = sdf.parse(time);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                String formattedTime = output.format(d);
//                myCreate.setText("Account created on: " +formattedTime);



            }
        });
    }

}