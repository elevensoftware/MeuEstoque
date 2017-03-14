package com.example.jose.meuestoque;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jose.meuestoque.entidades.Produto;

public class CadastroProdutos extends AppCompatActivity {
    EditText nomeProduto, codigoProduto, valorProduto, qtdMinima, qtdAtual;
    String valorNomeProduto;
    int valorCodigoProduto, valorQuantAtual, valorQuantMinima;
    double valorPrecoProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);

        nomeProduto = (EditText) findViewById(R.id.edtNomeProduto);
        codigoProduto = (EditText) findViewById(R.id.edtCodProduto);
        valorProduto = (EditText) findViewById(R.id.edtValorProduto);
        qtdMinima = (EditText) findViewById(R.id.edtQuantMinima);
        qtdAtual = (EditText) findViewById(R.id.edtQuantidade);
    }

    private void pegaValores() {
        valorNomeProduto = nomeProduto.getText().toString();
        valorCodigoProduto = Integer.parseInt(codigoProduto.getText().toString());
        valorQuantAtual = Integer.parseInt(qtdAtual.getText().toString());
        valorQuantMinima = Integer.parseInt(qtdMinima.getText().toString());
        valorPrecoProduto = Double.parseDouble(valorProduto.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edicao_clientes, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actionSalvar) {

            if (nomeProduto.getText().toString().equals("")) {
                Toast.makeText(this, "Insira o nome deste produto", Toast.LENGTH_SHORT).show();
            } else if (codigoProduto.getText().toString().equals("")) {
                Toast.makeText(this, "Insira o codigo deste produto", Toast.LENGTH_SHORT).show();
            } else if (valorProduto.getText().toString().equals("")) {
                Toast.makeText(this, "Insira o valor deste produto", Toast.LENGTH_SHORT).show();
            } else if (qtdMinima.getText().toString().equals("")) {
                Toast.makeText(this, "Insira a quantidade minima deste produto", Toast.LENGTH_SHORT).show();
            } else if (qtdAtual.getText().toString().equals("")) {
                Toast.makeText(this, "Insira a quantidade atual em estoque deste produto", Toast.LENGTH_SHORT).show();
            } else {
                pegaValores();
                Produto produto = new Produto(valorCodigoProduto, valorNomeProduto, valorQuantAtual, valorQuantMinima, valorPrecoProduto);
                    produto.save();
                caixaDeDialogo("Produto salvo", "Deseja cadastrar mais algum outro produto ?");
                limpaCampos();
            }
        }
        if (item.getItemId() == R.id.actionCancel) {
                caixaDeDialogo("Atenção", "Deseja mesmo sair ?");
        }
        return super.onOptionsItemSelected(item);
    }

    private void limpaCampos() {
        nomeProduto.setText("");
        codigoProduto.setText("");
        valorProduto.setText("");
        qtdMinima.setText("");
        qtdAtual.setText("");
    }

    private void caixaDeDialogo(String titulo, String mensagem) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(titulo);
        dialog.setMessage(mensagem);
            if (titulo.equals("Produto salvo")) {
                dialog.setPositiveButton("Sim", null);
                dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
            }
            if (titulo.equals("Atenção")){
                dialog.setNegativeButton("Não", null);
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
            }
        dialog.show();

    }
}
