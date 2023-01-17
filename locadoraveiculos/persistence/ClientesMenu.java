package br.com.ada.projeto.locadoraveiculos.persistence;
import br.com.ada.projeto.locadoraveiculos.view.Mensagens;

public class ClientesMenu {
    Mensagens mensagens = new Mensagens();
    public void menuClientes() {
        boolean flag = false;
        do {
            boolean continuar;
            mensagens.headerMenuClientes();
            ClienteServices.listarClientes();
            switch (Integer.toString(mensagens.menuClientes())) {

                case "1":
                    do {
                        continuar = ClienteServices.menuClienteCadastrar();
                    }while (continuar);break;

                case "2":
                    do{
                    continuar =ClienteServices.menuClienteBusarId();
            }while (continuar);break;

                case "3":
                    do{
                    continuar = ClienteServices.menuClienteBuscarNome();
                    }while (continuar);break;

                case "4":
                    do{
                    continuar = ClienteServices.menuClienteAlterar();
                    }while (continuar);break;

                case "5":
                    do {
                    continuar = ClienteServices.menuClienteRemover();
                    }while (continuar);break;

                case "6":
                    flag = mensagens.desejaSair();break;

                default:
                    System.out.println(mensagens.valorInvalido());break;

            }
        } while (!flag);
    }
}
