package com.example.lavanderiagileade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class clientes extends MainActivity {

    Button btnNewCliente;
    Button btnBuscarCli;
    Button btnVoltar;
    Button btnSalvarCli;
    Button btnCancelarCli;
    LinearLayout layoutCampos;
    LinearLayout layoutBotoes;
    LinearLayout layoutSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes);

        btnNewCliente = (Button) findViewById(R.id.btnNewCliente);
        btnBuscarCli = (Button) findViewById(R.id.btnBuscarCli);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnSalvarCli = (Button) findViewById(R.id.btnSalvarCli);
        btnCancelarCli = (Button) findViewById(R.id.btnCancelarCli);
        layoutCampos = (LinearLayout) findViewById(R.id.layoutCampos);
        layoutBotoes = (LinearLayout) findViewById(R.id.layoutBotoes);
        layoutSalvar = (LinearLayout) findViewById(R.id.layoutSalvar);

        btnNewCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutCampos.setVisibility(View.VISIBLE);
                layoutBotoes.setVisibility(View.GONE);
                layoutSalvar.setVisibility(View.VISIBLE);

            }
        });

        btnCancelarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutCampos.setVisibility(View.GONE);
                layoutBotoes.setVisibility(View.VISIBLE);
                layoutSalvar.setVisibility(View.GONE);
            }
        });

        btnSalvarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaPrincipal = new Intent(clientes.this, MainActivity.class);
                startActivity(telaPrincipal);
            }
        });
    }
}
