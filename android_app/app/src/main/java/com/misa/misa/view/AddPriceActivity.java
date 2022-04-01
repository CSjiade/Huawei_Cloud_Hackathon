package com.misa.misa.view;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.pmml4s.model.Model;
//import com.misa.misa.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.marwaeltayeb.souq.R;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class AddPriceActivity extends AppCompatActivity {


    private static final String TAG = "AddPriceActivity";

    private Spinner brandSpinner;
    private EditText txtAge;
    private Spinner colorSpinner;
    private EditText txtHeight;
    private EditText txtWidth;
    private EditText txtDepth;
    private Button predictBtn;
    private Model model;
    private Spinner subcategorySpinner;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_add_price);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Misa's Product Valuation Estimator");
        subcategorySpinner = findViewById(R.id.category_spinner);
        brandSpinner = findViewById(R.id.brand_spinner);
        colorSpinner = findViewById(R.id.color_spinner);
        predictBtn = findViewById(R.id.btnPredict);
        txtAge = findViewById(R.id.txtage);
        txtHeight = findViewById(R.id.txtHeight);
        txtWidth = findViewById(R.id.txtWidth);
        txtDepth = findViewById(R.id.txtDepth);
        result = findViewById(R.id.txtresult);
        populateSpinner();
        predictBtn.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                startPrediction();
            }
        }));
    }


    public void startPrediction() {

//        Integer age = Integer.parseInt(txtAge.getText().toString().trim());
//        Integer height = Integer.parseInt(txtHeight.getText().toString().trim());
//        Integer width = Integer.parseInt(txtWidth.getText().toString().trim());
//        Integer depth = Integer.parseInt(txtDepth.getText().toString().trim());
//        String brand = brandSpinner.getSelectedItem().toString();
//        String color = colorSpinner.getSelectedItem().toString();

        // Check if there are no empty values
        if (TextUtils.isEmpty(txtAge.getText().toString().trim()) || TextUtils.isEmpty(txtHeight.getText().toString().trim()) ||
                TextUtils.isEmpty(txtWidth.getText().toString().trim()) || TextUtils.isEmpty(txtDepth.getText().toString().trim())
                ) {
            Toast.makeText(this, getString(R.string.required_data), Toast.LENGTH_SHORT).show();
            return;
        }

        //String url = "http://127.0.0.1:5000/predict";
       // String url ="http://192.168.1.119:5000/predict";
       // String url = "http://10.0.2.2:5000/predict";
        String url = "";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String data = null;
                        try {
                            data = jsonObject.getString("result");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        result.setText("Your product is estimated to be worth: " + data + " SGD");
                    }

                },
                error -> Toast.makeText(AddPriceActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show()){
            @Override
            protected Map getParams(){
                Map params = new HashMap();
                params.put("age",txtAge.getText().toString().trim());
                params.put("height",txtHeight.getText().toString().trim());
                params.put("width",txtWidth.getText().toString().trim());
                params.put("depth",txtDepth.getText().toString().trim());
                params.put("brand",brandSpinner.getSelectedItem().toString());
                params.put("color",colorSpinner.getSelectedItem().toString());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(AddPriceActivity.this);
        queue.add(stringRequest);

    }

    private void populateSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.brand_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brandSpinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.color_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.subcategories_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subcategorySpinner.setAdapter(adapter3);
    }


}




