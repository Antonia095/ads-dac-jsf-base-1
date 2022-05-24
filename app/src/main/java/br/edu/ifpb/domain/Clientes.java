package br.edu.ifpb.domain;

import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 19/04/2022, 10:50:41
 */
public interface Clientes {

    public List<Cliente> todas();

    public List<Cliente> porLocalDeOrigem(String localDeOrigem);

    public void nova(Cliente cliente);

}
