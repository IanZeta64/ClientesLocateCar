package br.com.ada.projeto.locadoraveiculos.persistence;
import br.com.ada.projeto.locadoraveiculos.model.Cliente;
import br.com.ada.projeto.locadoraveiculos.model.Endereco;
import br.com.ada.projeto.locadoraveiculos.model.Telefone;
import br.com.ada.projeto.locadoraveiculos.model.TipoCliente;
import br.com.ada.projeto.locadoraveiculos.view.Mensagens;
import java.math.BigDecimal;
import java.util.List;

public class ClienteServices {
    private static final Mensagens mensagens = new Mensagens();
    private static final ClienteEmMemoriaRepository cr = new ClienteEmMemoriaRepository();

    public static Cliente alterarCliente(Cliente cliente) {
        alterarInfos(cliente);
        return cliente;
    }

    public static Cliente cadastrarCliente(boolean cadastroCompleto) {
        Cliente cliente = new Cliente();
        cliente.setTipoCliente(ClienteServices.cadastrarTipoCliente());
        List<String> nomeDoc = ClienteServices.cadastrarNomeEDocumento();
        cliente.setNome(nomeDoc.get(0));
        cliente.setDocumento(nomeDoc.get(1));
        if (!cadastroCompleto) {
            cliente.setEmail(cadastrarEmail());
            cliente.setTelefone(cadastrarTelefone());
            cliente.setEndenreco(cadastrarEndereco());
        }
        return cliente;
    }

    private static void alterarInfos(Cliente cliente) {
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

    private static TipoCliente cadastrarTipoCliente() {
        int resposta = mensagens.pessoaFisicaOuJuridica();
        return (resposta == 1) ?
                new TipoCliente("Pessoa Fisica", BigDecimal.valueOf(5), 5) :
                new TipoCliente("Pessoa Juridica", BigDecimal.valueOf(10), 3);
    }

    private static List<String> cadastrarNomeEDocumento() {
        String nome = mensagens.nome();
        String doc = mensagens.documento();
        return List.of(nome, doc);
    }

    private static Endereco cadastrarEndereco() {
        String logradouro = mensagens.logradouro();
        String numEnd = mensagens.numeroEndereco();
        String cep = mensagens.cep();
        String cidade = mensagens.cidade();
        return new Endereco(logradouro, numEnd, cep, cidade);
    }

    private static Telefone cadastrarTelefone() {
        String ddi = mensagens.ddi();
        String ddd = mensagens.ddd();
        String numTel = mensagens.numeroTelefone();
        return new Telefone(ddi, ddd, numTel);
    }

    private static String cadastrarEmail() {
        return mensagens.email();
    }
    public static boolean menuClienteRemover(){
            if (!cr.getEntidades().isEmpty()) {
                int listaSize = cr.getEntidades().size();
                String id = mensagens.documento();
                Cliente cl = cr.buscarPeloId(id);
                cr.remover(cl);
                if (listaSize > cr.getEntidades().size()) System.out.println(mensagens.operacaoSucesso());
                else System.out.println(mensagens.falhaOperacao());
            } else System.out.println(mensagens.listaVazia());
          return mensagens.desejaContinuar();
    }

    public static boolean menuClienteAlterar(){
        if (!cr.getEntidades().isEmpty()) {
            String id = mensagens.documento();
            Cliente cliente = cr.getEntidades().get(id);
            cr.salvar(ClienteServices.alterarCliente(cliente));
            System.out.println(mensagens.operacaoSucesso());
        } else System.out.println(mensagens.listaVazia());
        return mensagens.desejaContinuar();
    }

    public static boolean menuClienteBuscarNome(){
        if (!cr.getEntidades().isEmpty()) {
            cr.buscarPeloNome(mensagens.nome()).forEach(System.out::println);
        } else System.out.println(mensagens.listaVazia());
        return mensagens.desejaContinuar();
    }

    public static boolean menuClienteBusarId(){
        if (!cr.getEntidades().isEmpty()) {
            String id = mensagens.documento();
            String cliente = String.valueOf(cr.buscarPeloId(id));
            if (cliente.length() <= 0)System.out.println(cliente);
            else System.out.println(mensagens.falhaOperacao());
        } else System.out.println(mensagens.listaVazia());
        return mensagens.desejaContinuar();
    }
    public static boolean menuClienteCadastrar(){
        int listaSize = cr.getEntidades().size();
        cr.salvar(ClienteServices.cadastrarCliente(mensagens.tipoCadastro()));
        if (listaSize == cr.getEntidades().size()) {
            System.out.println(mensagens.falhaOperacao());
        } else System.out.println(mensagens.operacaoSucesso());
        return mensagens.desejaContinuar();
    }
    public static void listarClientes(){
        System.out.println(cr.getEntidades());
    }
}
