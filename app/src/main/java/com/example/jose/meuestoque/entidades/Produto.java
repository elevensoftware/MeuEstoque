package com.example.jose.meuestoque.entidades;

/**
 * Created by jose on 09/03/17.
 */

public class Produto {

    int codigo;
    String nomeProduto;
    int quantidadeAtual;
    int quantidadeMinima;
    double valor;

    public Produto(){
        
    }

    public Produto(int codigo, String nomeProduto, int quantidadeAtual, int quantidadeMinima, double valor) {
        this.codigo = codigo;
        this.nomeProduto = nomeProduto;
        this.quantidadeAtual = quantidadeAtual;
        this.quantidadeMinima = quantidadeMinima;
        this.valor = valor;
    }
}
