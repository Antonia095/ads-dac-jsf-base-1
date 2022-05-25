package br.edu.ifpb.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Vendas {
        private int id;
        private List<Produto> produtos;
        private LocalDateTime criadaEm;
        private Cliente cliente;

    public Vendas(int id, List<Produto> produtos, LocalDateTime criadaEm, Cliente cliente) {
        this.id = id;
        this.produtos = produtos;
        this.criadaEm = criadaEm;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDateTime getCriadaEm() {
        return criadaEm;
    }

    public void setCriadaEm(LocalDateTime criadaEm) {
        this.criadaEm = criadaEm;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
