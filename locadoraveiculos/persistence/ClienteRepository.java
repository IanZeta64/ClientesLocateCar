package br.com.ada.projeto.locadoraveiculos.persistence;

import br.com.ada.projeto.locadoraveiculos.model.Cliente;

import java.util.List;

public interface ClienteRepository {
    List<Cliente> buscarPeloNome(String parteNome);
}
