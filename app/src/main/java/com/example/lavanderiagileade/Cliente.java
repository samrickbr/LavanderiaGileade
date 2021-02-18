package com.example.lavanderiagileade;

import androidx.annotation.NonNull;

public class Cliente {
    String cpf;
    String nome;
    String endereco;
    String telefone;
    String email;
    String sigla;
    Boolean mensalista;
    Integer vencimento;
    Double valor;

    public Cliente() {
    }

    public Cliente(String nome, String endereco, String telefone, String email, String sigla) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.sigla = sigla;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Boolean getMensalista() {
        return mensalista;
    }

    public void setMensalista(Boolean mensalista) {
        this.mensalista = mensalista;
    }

    public Integer getVencimento() {
        return vencimento;
    }

    public void setVencimento(Integer vencimento) {
        this.vencimento = vencimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @NonNull
    @Override
    public String toString() {
        return "Cliente{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", sigla='" + sigla + '\'' +
                ", mensalista=" + mensalista +
                ", vencimento=" + vencimento +
                ", valor=" + valor +
                '}';
    }
}
