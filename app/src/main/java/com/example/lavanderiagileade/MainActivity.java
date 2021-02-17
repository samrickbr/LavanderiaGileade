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

        btnCliente = (Button) findViewById(R.id.btnCliente);
        btnDelivery = (Button) findViewById(R.id.btnDelivery);

        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaCliente = new Intent(MainActivity.this, clientes.class);
                startActivity(telaCliente);
            }
        });
    }
}