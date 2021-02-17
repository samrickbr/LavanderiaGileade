package com.example.lavanderiagileade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class clientes extends MainActivity {

    Button btnNewCliente;
    Button btnBuscarCli;
    Button btnVoltar;
    Button btnSalvarCli;
    Button btnCancelarCli;

    LinearLayout layoutCampos;
    LinearLayout layoutBotoes;
    LinearLayout layoutSalvar;
    LinearLayout layoutVencimento;

    EditText edtCpf;
    EditText edtNome;
    EditText edtEndereco;
    EditText edtTelefone;
    EditText edtEmail;
    EditText edtSigla;
    EditText edtValor;
    EditText edtVencimento;

    Switch swtMensalista;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes);

        btnNewCliente = findViewById(R.id.btnNewCliente);
        btnBuscarCli = findViewById(R.id.btnBuscarCli);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnSalvarCli = findViewById(R.id.btnSalvarCli);
        btnCancelarCli = findViewById(R.id.btnCancelarCli);

        layoutCampos = findViewById(R.id.layoutCampos);
        layoutBotoes = findViewById(R.id.layoutBotoes);
        layoutSalvar = findViewById(R.id.layoutSalvar);
        layoutVencimento = findViewById(R.id.layoutVencimento);

        edtCpf = findViewById(R.id.edtCPF);
        edtNome = findViewById(R.id.edtNome);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtEmail = findViewById(R.id.edtEmail);
        edtSigla = findViewById(R.id.edtSigla);
        edtValor = findViewById(R.id.edtValor);
        edtVencimento = findViewById(R.id.edtVencimento);

        swtMensalista = findViewById(R.id.swtMensalista);

        //==============================================================
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("clientes");

        //==============================================================
        swtMensalista.setOnClickListener(v -> {
            if (swtMensalista.isChecked()) {
                layoutVencimento.setVisibility(View.VISIBLE);
            } else {
                layoutVencimento.setVisibility(View.GONE);
            }
        });
        btnNewCliente.setOnClickListener(v -> {
            layoutCampos.setVisibility(View.VISIBLE);
            layoutBotoes.setVisibility(View.GONE);
            layoutSalvar.setVisibility(View.VISIBLE);

        });

        btnCancelarCli.setOnClickListener(v -> {
            layoutCampos.setVisibility(View.GONE);
            layoutBotoes.setVisibility(View.VISIBLE);
            layoutSalvar.setVisibility(View.GONE);
        });

        btnSalvarCli.setOnClickListener(v -> {
            salvarCliente();
            //limparCampos();
            layoutCampos.setVisibility(View.GONE);
            layoutBotoes.setVisibility(View.VISIBLE);
            layoutSalvar.setVisibility(View.GONE);
        });

        btnVoltar.setOnClickListener(v -> {
            Intent telaPrincipal = new Intent(clientes.this, MainActivity.class);
            startActivity(telaPrincipal);
        });
    }

    //============== FUNÇÕES E PROCEDIMENTOS ==========================

    //---------- Salvar Cliente ----------
    public void salvarCliente() {
        Cliente cli = new Cliente(
                edtNome.getText().toString(),
                edtEndereco.getText().toString(),
                edtTelefone.getText().toString(),
                edtEmail.getText().toString(),
                edtSigla.getText().toString(),
                swtMensalista.isChecked()
        );

        cli.setCpf(edtCpf.getText().toString());
        cli.setNome(edtNome.getText().toString());
        cli.setEndereco(edtEndereco.getText().toString());
        cli.setTelefone(edtTelefone.getText().toString());
        cli.setEmail(edtEmail.getText().toString());
        cli.setSigla(edtSigla.getText().toString());
        cli.setMensalista(swtMensalista.isChecked());
        cli.setValor(Double.valueOf(edtValor.getText().toString()));
        cli.setVencimento(Integer.valueOf(edtVencimento.getText().toString()));

        myRef.child(cli.cpf).child("nome").setValue(cli.nome);
        myRef.child(cli.cpf).child("endereco").setValue(cli.endereco);
        myRef.child(cli.cpf).child("telefone").setValue(cli.telefone);
        myRef.child(cli.cpf).child("email").setValue(cli.email);
        myRef.child(cli.cpf).child("sigla").setValue(cli.sigla);
        myRef.child(cli.cpf).child("mensalista").setValue(cli.swtMensalista);
        myRef.child(cli.cpf).child("valor").setValue(cli.valor);
        myRef.child(cli.cpf).child("vencimento").setValue(cli.vencimento);
    }

    //---------- Limpar Campos ----------
    public void limparCampos() {
        edtCpf.setText("");
        edtNome.setText("");
        edtEndereco.setText("");
        edtTelefone.setText("");
        edtEmail.setText("");
        edtSigla.setText("");
        swtMensalista.setChecked(false);
        edtValor.setText("");
        edtVencimento.setText("");
    }

}
