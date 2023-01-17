package br.com.ada.projeto.locadoraveiculos.persistence;

import br.com.ada.projeto.locadoraveiculos.model.Agencia;

import java.util.ArrayList;
import java.util.List;

public class AgenciaEmMemoriaRepository extends RepositoryGenericoAbstract<Agencia> implements AgenciaRepository{
    @Override
    public List<Agencia> buscarPeloLogradouro(String parteLogradouro) {
        List<Agencia> agenciasComParteDesseNome = new ArrayList<>();
        for (Agencia agencia: entidades.values()) {
            if (agencia.getLogradouro().contains(parteLogradouro)) {
                agenciasComParteDesseNome.add(agencia);
            }
        }
        return agenciasComParteDesseNome;
    }
}
