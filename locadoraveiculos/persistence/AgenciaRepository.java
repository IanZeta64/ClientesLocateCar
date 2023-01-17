package br.com.ada.projeto.locadoraveiculos.persistence;

import br.com.ada.projeto.locadoraveiculos.model.Agencia;
import br.com.ada.projeto.locadoraveiculos.model.Cliente;

import java.util.List;

public interface AgenciaRepository {

    List<Agencia> buscarPeloLogradouro(String parteLogradouro);

}