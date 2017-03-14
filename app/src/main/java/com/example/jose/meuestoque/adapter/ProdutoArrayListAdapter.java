package com.example.jose.meuestoque.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jose.meuestoque.R;
import com.example.jose.meuestoque.entidades.Produto;

import java.util.List;

/**
 * Created by jose on 16/02/17.
 */

public class ProdutoArrayListAdapter extends ArrayAdapter<Produto> {


    private List<Produto> mProdutos;
    Context mContext;

    public ProdutoArrayListAdapter(Context context, int resource, List<Produto> produtos) {
        super(context, resource, produtos);
        this.mContext = context;
        this.mProdutos = produtos;
    }

    public View getView(int position, View linha, ViewGroup parent) {
        if (linha == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            //representa a view de cada linha
            linha = layoutInflater.inflate(R.layout.item_produto, parent, false);
        }

        TextView txtNome = (TextView) linha.findViewById(R.id.txtNomeProduto);
        TextView txtQuantidade = (TextView) linha.findViewById(R.id.txtQuantidade);
        TextView txtValor = (TextView) linha.findViewById(R.id.txtValor);
        ImageView imageView = (ImageView) linha.findViewById(R.id.atencao);

        Produto produto = mProdutos.get(position);

        txtNome.setText(produto.getNomeProduto());
        txtQuantidade.setText("" + produto.getQuantidadeAtual());
        txtValor.setText("" + produto.getValor());


        if (Integer.parseInt(txtQuantidade.getText().toString()) < produto.getQuantidadeMinima()) {
            imageView.setVisibility(View.VISIBLE);
        }


        return linha;

    }

}
