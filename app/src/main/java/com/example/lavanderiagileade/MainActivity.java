package com.example.lavanderiagileade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnCliente;
    Button btnDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCliente = findViewById(R.id.btnCliente);
        btnDelivery = findViewById(R.id.btnDelivery);

        btnCliente.setOnClickListener(v -> {
            Intent telaCliente = new Intent(MainActivity.this, CadClientes.class);
            startActivity(telaCliente);
        });
    }
}