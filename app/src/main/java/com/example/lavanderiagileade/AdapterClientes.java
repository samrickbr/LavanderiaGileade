package com.example.lavanderiagileade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

public class AdapterClientes extends RecyclerView.Adapter {

    private final List<Cliente> clientes;
    private final Context context;

    public AdapterClientes(List<Cliente> clientes, Context context) {
        this.clientes = clientes;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_lista_clientes, parent, false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ClienteViewHolder holder = (ClienteViewHolder) viewHolder;
        Cliente cliente = clientes.get(position);

        holder.nome.setText(cliente.nome);
        holder.sigla.setText(cliente.getSigla());
        holder.separador.setText(" - ");
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

}
