package com.example.jose.meuestoque.entidades;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by jose on 09/03/17.
 */
@Table

public class Produto extends SugarRecord {
    private long id;

    int codigo;
    String nomeProduto;
    int quantidadeAtual;
    int quantidadeMinima;
    double valor;

    public Produto() {

    }

    public Produto(int codigo, String nomeProduto, int quantidadeAtual, int quantidadeMinima, double valor) {
        this.codigo = codigo;
        this.nomeProduto = nomeProduto;
        this.quantidadeAtual = quantidadeAtual;
        this.quantidadeMinima = quantidadeMinima;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "COD: "+codigo+" - " + nomeProduto ;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(int quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
