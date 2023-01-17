package br.com.ada.projeto.locadoraveiculos.persistence;
import br.com.ada.projeto.locadoraveiculos.model.Cliente;
import br.com.ada.projeto.locadoraveiculos.model.Endereco;
import br.com.ada.projeto.locadoraveiculos.model.Telefone;
import br.com.ada.projeto.locadoraveiculos.model.TipoCliente;
import br.com.ada.projeto.locadoraveiculos.view.Mensagens;

import java.math.BigDecimal;
import java.util.List;

public class ClienteController extends ClienteEmMemoriaRepository{
    static Mensagens mensagens = new Mensagens();

    public static Cliente alterarCliente(Cliente cliente) {
        alterarInfos(cliente);
       return cliente;
    } //ATENCAO
    public static Cliente cadastrarCliente(boolean cadastroCompleto) {
        TipoCliente tipoCliente = ClienteController.cadastrarTipoCliente();
        List<String> nomeDoc = ClienteController.cadastrarNomeEDocumento();
        Cliente cliente = new Cliente(nomeDoc.get(0), nomeDoc.get(1), tipoCliente);
        if (!cadastroCompleto) cliente = ClienteController.cadastrarInfosExtras(cliente);
        return cliente;
    }// ATENCAO
    private static void alterarInfos(Cliente cliente){
        String escolha = Integer.toString(mensagens.menuAlterar());
        switch (escolha) {
            case "1" -> cliente.setNome(mensagens.nome());
            case "2" -> cliente.setDocumento(mensagens.documento());
            case "3" -> cliente.setEmail(cadastrarEmail());
            case "4" -> cliente.setTelefone(cadastrarTelefone());
            case "5" -> cliente.setEndenreco(cadastrarEndereco());
            default -> System.out.println(mensagens.valorInvalido());
        }
    }
    private static TipoCliente cadastrarTipoCliente(){
        int resposta = mensagens.pessoaFisicaOuJuridica();
        return (resposta == 1) ?
                new TipoCliente("Pessoa Fisica", BigDecimal.valueOf(5), 5) :
                new TipoCliente("Pessoa Juridica", BigDecimal.valueOf(10), 3);
    }
    private static List<String> cadastrarNomeEDocumento(){
        String nome = mensagens.nome();
        String doc = mensagens.documento();
        return List.of(nome, doc);
    }
    private static Cliente cadastrarInfosExtras(Cliente cliente){
        cliente.setEndenreco(cadastrarEndereco());
        cliente.setTelefone(cadastrarTelefone());
        cliente.setEmail(cadastrarEmail());
        return cliente;
    }
    private static Endereco cadastrarEndereco() {
        String logradouro = mensagens.logradouro();
        String numEnd = mensagens.numeroEndereco();
        String cep = mensagens.cep();
        String cidade = mensagens.cidade();
        return new Endereco(logradouro, numEnd,cep,cidade);
    }
    private static Telefone cadastrarTelefone(){
        String ddi = mensagens.ddi();
        String ddd = mensagens.ddd();
        String numTel = mensagens.numeroTelefone();
        return  new Telefone(ddi, ddd, numTel);
    }
    private static String cadastrarEmail(){
        return mensagens.email();
    }
}
