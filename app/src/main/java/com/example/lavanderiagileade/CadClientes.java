package com.example.lavanderiagileade;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CadClientes extends AppCompatActivity {

    Button btnNewCliente;
    Button btnBuscarCli;
    Button btnVoltar;
    Button btnSalvarCli;
    Button btnCancelarCli;
    Button btnEditarCliente;
    Button btnCancelarPesq;

    LinearLayout layoutCampos;
    LinearLayout layoutBotoes;
    LinearLayout layoutSalvar;
    LinearLayout layoutVencimento;
    LinearLayout layoutPesquisa;

    EditText edtCpf;
    EditText edtNome;
    EditText edtEndereco;
    EditText edtTelefone;
    EditText edtEmail;
    EditText edtSigla;
    EditText edtValor;
    EditText edtVencimento;
    EditText edtPesquisar;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch swtMensalista;

    List<Cliente> listCliente = new ArrayList<>();

    RecyclerView rvClientes;
    AdapterClientes adapter;
    RecyclerView.LayoutManager layout;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_clientes);

        inicializarComponentes();
        funcaoBotoes();

        //==============================================================
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        //==============================================================

    }

    //============== FUNÇÕES E PROCEDIMENTOS ==========================

    //---------- Inicializar Botoes ----------
    private void funcaoBotoes() {
        swtMensalista.setOnClickListener(v -> {
            if (swtMensalista.isChecked()) {
                layoutVencimento.setVisibility(View.VISIBLE);
            } else {
                layoutVencimento.setVisibility(View.GONE);
            }
        });
        //==============================================================
        btnNewCliente.setOnClickListener(v -> {
            layoutCampos.setVisibility(View.VISIBLE);
            layoutBotoes.setVisibility(View.GONE);
            layoutSalvar.setVisibility(View.VISIBLE);
        });
        //==============================================================
        btnCancelarCli.setOnClickListener(v -> {
            limparCampos();
            layoutCampos.setVisibility(View.GONE);
            layoutBotoes.setVisibility(View.VISIBLE);
            layoutSalvar.setVisibility(View.GONE);
        });
        //==============================================================
        btnSalvarCli.setOnClickListener(v -> {
            salvarCliente();
            limparCampos();
            layoutCampos.setVisibility(View.GONE);
            layoutBotoes.setVisibility(View.VISIBLE);
            layoutSalvar.setVisibility(View.GONE);
        });
        //==============================================================
        btnBuscarCli.setOnClickListener(v -> {
            pesquisarPalavra("");
            layoutPesquisa.setVisibility(View.VISIBLE);
            layoutBotoes.setVisibility(View.GONE);
        });
        //==============================================================
        btnEditarCliente.setOnClickListener(v -> {
            layoutPesquisa.setVisibility(View.GONE);
            layoutCampos.setVisibility(View.VISIBLE);
            layoutSalvar.setVisibility(View.VISIBLE);
        });
        //==============================================================
        btnCancelarPesq.setOnClickListener(v -> {
            layoutPesquisa.setVisibility(View.GONE);
            layoutBotoes.setVisibility(View.VISIBLE);
        });
        //==============================================================
        btnVoltar.setOnClickListener(v -> {
            Intent telaPrincipal = new Intent(CadClientes.this, MainActivity.class);
            startActivity(telaPrincipal);
        });
    }

    //---------- Inicializar Componentes ----------
    private void inicializarComponentes() {
        btnNewCliente = findViewById(R.id.btnNewCliente);
        btnBuscarCli = findViewById(R.id.btnBuscarCli);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnSalvarCli = findViewById(R.id.btnSalvarCli);
        btnCancelarCli = findViewById(R.id.btnCancelarCli);
        btnCancelarPesq = findViewById(R.id.btnCancelarPesq);
        btnEditarCliente = findViewById(R.id.btnEditarCliente);

        layoutCampos = findViewById(R.id.layoutCampos);
        layoutBotoes = findViewById(R.id.layoutBotoes);
        layoutSalvar = findViewById(R.id.layoutSalvar);
        layoutVencimento = findViewById(R.id.layoutVencimento);
        layoutPesquisa = findViewById(R.id.layoutPesquisa);

        edtCpf = findViewById(R.id.edtCPF);
        edtNome = findViewById(R.id.edtNome);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtEmail = findViewById(R.id.edtEmail);
        edtSigla = findViewById(R.id.edtSigla);
        edtValor = findViewById(R.id.edtValor);
        edtVencimento = findViewById(R.id.edtVencimento);
        edtPesquisar = findViewById(R.id.edtPesquisar);

        swtMensalista = findViewById(R.id.swtMensalista);

        rvClientes = findViewById(R.id.recicleClientes);

        pesquisarCliente();
    }

    //---------- Pesquisar Cliente ----------
    private void pesquisarCliente() {
        edtPesquisar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String cliente = edtPesquisar.getText().toString().toUpperCase().trim();
                pesquisarPalavra(cliente);
            }
        });
    }

    private void pesquisarPalavra(String palavra) {
        Query query;
        if (palavra.equals("")) {
            query = myRef.child("clientes").orderByChild("nome");
        } else {
            query = myRef.child("clientes").orderByChild("nome").startAt(palavra).endAt(palavra + "\uf8ff");
        }
        listCliente.clear();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Cliente cli = objSnapshot.getValue(Cliente.class);
                    listCliente.add(cli);
                }
                rvClientes = findViewById(R.id.recicleClientes);
                layout = new LinearLayoutManager(CadClientes.this);
                rvClientes.setLayoutManager(layout);

                adapter = new AdapterClientes(listCliente, CadClientes.this);
                rvClientes.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    //---------- Salvar, Alterar Cliente ----------
    public void salvarCliente() {
        Cliente cli = new Cliente(
                edtNome.getText().toString(),
                edtEndereco.getText().toString(),
                edtTelefone.getText().toString(),
                edtEmail.getText().toString(),
                edtSigla.getText().toString()
        );

        cli.setCpf(edtCpf.getText().toString());
        cli.setNome(edtNome.getText().toString());
        cli.setEndereco(edtEndereco.getText().toString());
        cli.setTelefone(edtTelefone.getText().toString());
        cli.setEmail(edtEmail.getText().toString());
        cli.setSigla(edtSigla.getText().toString());
        cli.setMensalista(swtMensalista.isChecked());
        if (swtMensalista.isChecked()) {
            cli.setValor(Double.valueOf(edtValor.getText().toString()));
            cli.setVencimento(Integer.valueOf(edtVencimento.getText().toString()));
        }

        myRef.child("clientes").child(cli.cpf).child("nome").setValue(cli.nome.toUpperCase());
        myRef.child("clientes").child(cli.cpf).child("endereco").setValue(cli.endereco.toUpperCase());
        myRef.child("clientes").child(cli.cpf).child("telefone").setValue(cli.telefone);
        myRef.child("clientes").child(cli.cpf).child("email").setValue(cli.email.toLowerCase());
        myRef.child("clientes").child(cli.cpf).child("sigla").setValue(cli.sigla.toUpperCase());
        if (swtMensalista.isChecked()) {
            myRef.child("clientes").child(cli.cpf).child("mensalista").setValue(true);
            myRef.child("clientes").child(cli.cpf).child("valor").setValue(cli.valor);
            myRef.child("clientes").child(cli.cpf).child("vencimento").setValue(cli.vencimento);
        } else {
            myRef.child("clientes").child(cli.cpf).child("mensalista").setValue(false);
            myRef.child("clientes").child(cli.cpf).child("valor").setValue(0);
            myRef.child("clientes").child(cli.cpf).child("vencimento").setValue(0);
        }
    }

    //---------- Ler dados Cliente ----------
    public void lerCliente() {
        Cliente cli = new Cliente(
                edtNome.getText().toString(),
                edtEndereco.getText().toString(),
                edtTelefone.getText().toString(),
                edtEmail.getText().toString(),
                edtSigla.getText().toString()
        );

        cli.setCpf(edtCpf.getText().toString());
        cli.setNome(edtNome.getText().toString());
        cli.setEndereco(edtEndereco.getText().toString());
        cli.setTelefone(edtTelefone.getText().toString());
        cli.setEmail(edtEmail.getText().toString());
        cli.setSigla(edtSigla.getText().toString());
        cli.setMensalista(swtMensalista.isChecked());
        if (swtMensalista.isChecked()) {
            cli.setValor(Double.valueOf(edtValor.getText().toString()));
            cli.setVencimento(Integer.valueOf(edtVencimento.getText().toString()));
        }
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