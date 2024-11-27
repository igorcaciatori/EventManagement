import Exceptions.EventoInexistenteException;
import Exceptions.EventoJaCadastradoException;

import java.sql.Date;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final BancoInscricao bancoInscricao = new BancoInscricao();
    private static final BancoParticipante bancoParticipante = new BancoParticipante();
    private static final BancoEvento bancoEvento = new BancoEvento();

    public static void main(String[] args) {
        do {
            mostrarOpcoesMenu();
            int opcao = sc.nextInt();
            try {
                executarOpcaoMenu(opcao);
            } catch (EventoInexistenteException |
                     EventoJaCadastradoException e) {

                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private static void mostrarOpcoesMenu() {
        System.out.print("""
                MENU:
                
                1 - Cadastro de evento
                2 - Cadastro de participante
                3 - Cadastro de inscrição
                4 - Selecionar evento
                5 - Selecionar participante
                6 - Deletar evento
                7 - Deletar participante
                8 - Remover inscrição
                9 - Sair
                
                >""");
    }

    private static void executarOpcaoMenu(int opcao) {
        switch (opcao) {
            case 1:
                cadastroEvento();
                break;
            case 2:
                cadastroParticipante();
                break;
            case 3:
                cadastroInscricao();
                break;
            case 4:
                buscarEvento();
                break;
            case 5:
                buscarParticipante();
                break;
            case 6:
                removerEvento();
                break;
            case 7:
                removerParticipante();
                break;
            case 8:
                removerInscricao();
                break;
            case 9:
                System.out.println("Até mais!");
                System.exit(0);
            default:
                System.out.println("Opção inválida!");
        }
    }

    // CRUD DO EVENTO

    // C
    private static void cadastroEvento() {
        sc.nextLine();
        System.out.print("Nome do evento: ");
        String nome = sc.nextLine();
        try {
            Evento evento = bancoEvento.buscarEventoPorNome(nome);
        } catch (EventoInexistenteException e) {
            System.out.println("Lugar do evento: ");
            String lugar = sc.nextLine();
            System.out.println("Data do evento: ");
            String data = sc.nextLine();
            System.out.println("Descrição do evento: ");
            String descricao = sc.nextLine();
            bancoEvento.adicionarEvento(new Evento(nome, lugar, data, descricao));
            return;
        }
        throw new EventoJaCadastradoException();
    }

    // R
    private static Evento buscarEvento() {
        sc.nextLine();
        System.out.print("Nome do evento: ");
        return bancoEvento.buscarEventoPorNome(sc.nextLine());
    }

    // D
    private static void removerEvento() {
        Evento evento = buscarEvento();
        bancoEvento.removerEvento(evento.getId());
    }

    // CRUD DO PARTICIPANTE

    // C
    private static void cadastroParticipante() {
        sc.nextLine(); // LB
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("E-mail: ");
        String email = sc.nextLine();
        bancoParticipante.adicionarParticipante(new Participante(nome, email));
    }

    // R
    private static Participante buscarParticipante() {
        sc.nextLine();
        System.out.print("E-mail do cliente: ");
        String email = sc.nextLine();
        return bancoParticipante.buscarParticipantePorEmail(email);
    }

    // D
    private static void removerParticipante() {
        sc.nextLine();
        System.out.print("ID do cliente: ");
        int id = sc.nextInt();
        bancoParticipante.removerParticipante(id);
    }

    // CRUD DA INSCRIÇÃO

    // C
    private static void cadastroInscricao() {
        System.out.println("ID do cliente: ");
        int idCliente = sc.nextInt();
        System.out.println("ID do evento: ");
        int idEvento = sc.nextInt();
        bancoInscricao.inscreverParticipante(idEvento, idCliente);
    }

    // D
    private static void removerInscricao() {
        System.out.println("ID da inscrição: ");
        int id = sc.nextInt();
        bancoInscricao.removerInscricao(id);
    }
}