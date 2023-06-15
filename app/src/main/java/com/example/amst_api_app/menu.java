package com.example.amst_api_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menu extends AppCompatActivity {

    String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
    }

    public void Salir(View v){
        this.finish();
        System.exit(0);
    }
    public void revisarSensores(View v){
        Intent red_sensores = new Intent(getBaseContext(), red_sensores.class);
        red_sensores.putExtra("token", token);
        startActivity(red_sensores);
    }

    public void irGuardarValores(View view){
        Intent ingreso_valores_sensores = new Intent(getBaseContext(), ingreso_valores_sensores.class);
        ingreso_valores_sensores.putExtra("token", token);
        startActivity(ingreso_valores_sensores);
    }
}