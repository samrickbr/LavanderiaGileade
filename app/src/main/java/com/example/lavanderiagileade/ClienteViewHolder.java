package com.example.lavanderiagileade;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClienteViewHolder extends RecyclerView.ViewHolder {

    final TextView nome;
    final TextView sigla;
    final TextView separador;

    public ClienteViewHolder(@NonNull View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.txtNome);
        sigla = itemView.findViewById(R.id.txtSigla);
        separador = itemView.findViewById(R.id.txtSeparador);
    }
}
