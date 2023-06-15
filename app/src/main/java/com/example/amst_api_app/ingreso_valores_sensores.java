package com.example.amst_api_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ingreso_valores_sensores extends AppCompatActivity {

    EditText txtTemp, txtHumd, txtPeso;

    private RequestQueue mQueue = null;
    private String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_valores_sensores);

        mQueue = Volley.newRequestQueue(this);

        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");

    }

    public void guardarValores(View view){
        txtTemp = findViewById(R.id.txtTemp);
        txtHumd = findViewById(R.id.txtHumd);
        txtPeso = findViewById(R.id.txtPeso);


        String strTemp = txtTemp.getText().toString();
        String strHumd = txtHumd.getText().toString();
        String strPeso = txtPeso.getText().toString();

        Map<String, String> params = new HashMap();
        params.put("temperatura", strTemp);
        params.put("humedad", strHumd);
        params.put("peso", strPeso);
        JSONObject parametros = new JSONObject(params);

        String url = "https://amst-api-bk-lab3.onrender.com/api/sensores";

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, url, parametros,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("hola");
                            System.out.println(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "JWT " + token);
                System.out.println(token);
                return params;
            }
        };;
        mQueue.add(request);
    }
}