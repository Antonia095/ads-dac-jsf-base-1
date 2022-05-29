package br.edu.ifpb.services;

import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.Vendas;

import java.util.ArrayList;

public class CarrinhoDeCompras {

    private ArrayList<Vendas> compras;

    public CarrinhoDeCompras(){
        this.compras = new ArrayList<>();
    }
    public void inserirProduto(Vendas item){
        this.compras.add(item);
    }
    public void removerProduto(Vendas item){
        this.compras.remove(item);
    }

   
}
