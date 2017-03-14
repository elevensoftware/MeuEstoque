package com.example.jose.meuestoque;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jose.meuestoque.adapter.ProdutoArrayListAdapter;
import com.example.jose.meuestoque.entidades.Produto;

import java.util.List;

public class Home extends AppCompatActivity {
    ListView listaDeProdutos;
    EditText caixaDePesquisa;
    Produto produtoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listaDeProdutos = (ListView) findViewById(R.id.listaDeProdutos);
        registerForContextMenu(listaDeProdutos);
        caixaDePesquisa = (EditText) findViewById(R.id.pesquisarNaLista);
        caixaDePesquisa.setVisibility(View.GONE);


    }


    @Override
    protected void onResume() {
        super.onResume();
        manipularLista();


    }

    private void manipularLista() {
        List<Produto> produtos = Produto.listAll(Produto.class);
        ProdutoArrayListAdapter adapter = new ProdutoArrayListAdapter(this, R.layout.item_produto, produtos);
        listaDeProdutos.setAdapter(adapter);

        listaDeProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                produtoSelecionado = (Produto) parent.getItemAtPosition(position);
                return false;
            }
        });
    }


    public void adicionarProduto(View view) {

        Intent intent = new Intent(this, CadastroProdutos.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actionFornecedores) {
            //TODO CHECK
        }
        if (item.getItemId() == R.id.actionRelatorios) {
            //TODO RELATORIOS
        }
        if (item.getItemId() == R.id.actionSearch) {
            caixaDePesquisa.setVisibility(View.VISIBLE);
            caixaDePesquisa.requestFocus();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        final MenuItem deletar = menu.add("Deletar");
        final MenuItem entrada = menu.add("Entrada Manual");
        final MenuItem saida = menu.add("Saída Manual");


        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                dialogPersonalizada("Atenção", "Deseja mesmo remover " + produtoSelecionado.getNomeProduto() + " ?", "remocao");
                return false;
            }
        });

        entrada.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                dialogPersonalizada("Atenção", "Deseja mesmo remover " + produtoSelecionado.getNomeProduto() + " ?", "entrada");
                return false;
            }
        });

        saida.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                dialogPersonalizada("Atenção", "Deseja mesmo remover " + produtoSelecionado.getNomeProduto() + " ?", "saida");
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private void dialogPersonalizada(String titulo, String texto, String tipo) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(titulo);
        dialog.setMessage(texto);
        if (tipo.equals("remocao")) {
            dialog.setPositiveButton("Sim, desejo deletar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    produtoSelecionado.delete();
                    manipularLista();
                    Toast.makeText(Home.this, "Produto Deletado!", Toast.LENGTH_SHORT).show();

                }
            });
            dialog.setNegativeButton("Não", null);
        }


        dialog.show();
    }

}

