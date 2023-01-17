package br.com.ada.projeto.locadoraveiculos.view;
import br.com.ada.projeto.locadoraveiculos.model.Cliente;
import br.com.ada.projeto.locadoraveiculos.persistence.ClienteController;
import br.com.ada.projeto.locadoraveiculos.persistence.ClienteEmMemoriaRepository;

public class ClientesMenu {
    Mensagens mensagens = new Mensagens();
    public void menuClientes() {
        ClienteEmMemoriaRepository cr = new ClienteEmMemoriaRepository();
        boolean flag = false;
        do {
            boolean continuar;
            String id;
            int listaSize = cr.getEntidades().size();
           String menu =  Integer.toString(mensagens.menuClientes());
            switch (menu) {
                case "1":
                    do {
                        cr.salvar(ClienteController.cadastrarCliente(mensagens.tipoCadastro()));
                        if (listaSize == cr.getEntidades().size()) {
                            System.out.println(mensagens.falhaOperacao());
                        } else System.out.println(mensagens.operacaoSucesso());
                        continuar = mensagens.desejaContinuar();
                    }while (continuar);
                    break;

                case "2":
                    do{
                    if (!cr.getEntidades().isEmpty()) {
                        id = mensagens.documento();
                        try{
                            cr.buscarPeloId(id);
                        }catch(NullPointerException e){
                            System.out.println(mensagens.falhaOperacao());
                        }
                    } else System.out.println(mensagens.listaVazia());
                    continuar = mensagens.desejaContinuar();
            }while (continuar);
                    break;

                case "3":
                    do{
                    if (!cr.getEntidades().isEmpty()) {
                        cr.buscarPeloNome(mensagens.nome()).forEach(System.out::println);
                    } else System.out.println(mensagens.listaVazia());
                    continuar = mensagens.desejaContinuar();
                    }while (continuar);
                    break;

                case "4":
                    do{
                    if (!cr.getEntidades().isEmpty()) {
                        id = mensagens.documento();
                        Cliente cliente = cr.getEntidades().get(id);
                        cr.salvar(ClienteController.alterarCliente(cliente));
                        System.out.println(mensagens.operacaoSucesso());
                    } else System.out.println(mensagens.listaVazia());
                    continuar = mensagens.desejaContinuar();
                    }while (continuar);
                    break;

                case "5":
                    do {
                    if (!cr.getEntidades().isEmpty()) {
                        id = mensagens.documento();
                        Cliente cl = cr.buscarPeloId(id);
                        cr.remover(cl);
                        if (listaSize > cr.getEntidades().size()) System.out.println(mensagens.operacaoSucesso());
                        else System.out.println(mensagens.falhaOperacao());
                    } else System.out.println(mensagens.listaVazia());
                    continuar = mensagens.desejaContinuar();
                    }while (continuar);
                    break;
                case "6":
                    flag = mensagens.desejaSair();break;
                default:
                    System.out.println(mensagens.valorInvalido());break;

            }
        } while (!flag);
    }
}
