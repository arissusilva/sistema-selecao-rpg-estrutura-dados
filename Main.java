
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArvoreBinaria arvore = new ArvoreBinaria();

    public static void main(String[] args) {
        exibirBoasVindas();
        inserirPersonagensIniciais();

        int opcao = -1;
        boolean mostrarMenu = true;

     
        while (opcao != 11) {
            
            if (mostrarMenu) {
                exibirMenu();
            }

            opcao = lerInteiro("Opção: ");
            processarOpcao(opcao);

            if (opcao != 11) {
                int voltar = -1;
                while (voltar != 0) {
                    voltar = lerInteiro("\nDigite 0 para voltar ao menu principal: ");
                }
                mostrarMenu = true;
                System.out.println("\n"); 
            }
        }
    }

    private static void exibirBoasVindas() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║           SEJA BEM VINDO AO              ║");
        System.out.println("║        SISTEMA DE CADASTRO RPG           ║");
        System.out.println("║        Árvore Binária de Busca           ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println();
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║               MENU PRINCIPAL             ║");
        System.out.println("║══════════════════════════════════════════║");
        System.out.println("║  1.  Inserir personagem                  ║");
        System.out.println("║  2.  Buscar personagem por nível         ║");
        System.out.println("║  3.  Remover personagem                  ║");
        System.out.println("║  4.  Exibir em ordem crescente de nível  ║");
        System.out.println("║  5.  Exibir personagem mais forte        ║");
        System.out.println("║  6.  Exibir personagem mais fraco        ║");
        System.out.println("║  7.  Mostrar quantidade de personagens   ║");
        System.out.println("║  8.  Mostrar altura da árvore            ║");
        System.out.println("║  9.  Exibir personagens em pré-ordem     ║");
        System.out.println("║  10. Exibir personagens em pós-ordem     ║");
        System.out.println("║  11. Encerrar sistema                    ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:  inserirPersonagem();         break;
            case 2:  buscarPersonagem();          break;
            case 3:  removerPersonagem();         break;
            case 4:  exibirEmOrdem();             break;
            case 5:  exibirMaisForte();           break;
            case 6:  exibirMaisFraco();           break;
            case 7:  mostrarQuantidade();         break;
            case 8:  mostrarAltura();             break;
            case 9:  exibirPreOrdem();            break;
            case 10: exibirPosOrdem();            break;
            case 11: encerrar();                  break;
            default:
                System.out.println("\n AVISO: Opção inválida. Digite um número de 1 a 11.");
        }
    }

    private static void inserirPersonagem() {
        System.out.println("\n── INSERIR PERSONAGEM ──────────────────────");

        String nome    = lerTexto("Nome do personagem : ");
        String classe  = lerClasse();
        int    nivel   = lerInteiroPositivo("Nível (1-100)      : ", 1, 100);
        int    vida    = lerInteiroPositivo("Pontos de vida     : ", 1, Integer.MAX_VALUE);
        String arma    = lerArma();

        Personagem p = new Personagem(nome, classe, nivel, vida, arma);

        if (arvore.inserir(p)) {
            System.out.println("\n Personagem inserido com sucesso!");
            System.out.println(p);
        } else {
            System.out.println("\n AVISO: Já existe um personagem de nível " + nivel + ".");
            System.out.println("      Cada personagem deve ter um nível único.");
        }
    }

    private static String lerClasse() {
        System.out.println("  Classe do personagem:");
        System.out.println("    1 - Guerreiro    2 - Mago       3 - Arqueiro");
        System.out.println("    4 - Paladino     5 - Assassino  6 - Druida");
        System.out.println("    7 - Outro");

        int opc = lerInteiroPositivo("  Escolha: ", 1, 7);
        switch (opc) {
            case 1: return "Guerreiro";
            case 2: return "Mago";
            case 3: return "Arqueiro";
            case 4: return "Paladino";
            case 5: return "Assassino";
            case 6: return "Druida";
            default: return lerTexto("  Nome da classe: ");
        }
    }

    private static String lerArma() {
        System.out.println("  Tipo de arma:");
        System.out.println("    1 - Espada       2 - Cajado     3 - Arco e Flecha");
        System.out.println("    4 - Lança        5 - Adagas     6 - Martelo");
        System.out.println("    7 - Outro");

        int opc = lerInteiroPositivo("  Escolha: ", 1, 7);
        switch (opc) {
            case 1: return "Espada";
            case 2: return "Cajado";
            case 3: return "Arco e Flecha";
            case 4: return "Lança";
            case 5: return "Adagas";
            case 6: return "Martelo";
            default: return lerTexto("  Nome da arma: ");
        }
    }

    private static void buscarPersonagem() {
        System.out.println("\n── BUSCAR PERSONAGEM ───────────────────────");

        if (arvore.estaVazia()) {
            System.out.println("A árvore está vazia.");
            return;
        }

        int nivel = lerInteiro("Nível a buscar: ");
        Personagem p = arvore.buscar(nivel);

        if (p != null) {
            System.out.println("\n Personagem encontrado:");
            System.out.println(p);
        } else {
            System.out.println("\n AVISO: Nenhum personagem encontrado com nível " + nivel + ".");
        }
    }

    private static void removerPersonagem() {
        System.out.println("\n── REMOVER PERSONAGEM ──────────────────────");

        if (arvore.estaVazia()) {
            System.out.println("AVISO: A árvore está vazia.");
            return;
        }

        int nivel = lerInteiro("Nível do personagem a remover: ");
        Personagem p = arvore.buscar(nivel);

        if (p == null) {
            System.out.println("\n AVISO: Nenhum personagem encontrado com nível " + nivel + ".");
            return;
        }

        System.out.println("\n  Personagem encontrado:");
        System.out.println(p);
        System.out.print("\n  Confirmar remoção? (s/n): ");
        String confirmacao = scanner.nextLine().trim().toLowerCase();

        if (confirmacao.equals("s")) {
            arvore.remover(nivel);
            System.out.println("\n Personagem removido com sucesso!");
        } else {
            System.out.println("\n AVISO: Remoção cancelada.");
        }
    }

    private static void exibirEmOrdem() {
        System.out.println("\n── PERSONAGENS (ORDEM CRESCENTE DE NÍVEL) ──");
        arvore.exibirEmOrdem();
    }

    private static void exibirMaisForte() {
        System.out.println("\n── PERSONAGEM MAIS FORTE (MAIOR NÍVEL) ─────");

        if (arvore.estaVazia()) {
            System.out.println("AVISO: A árvore está vazia.");
            return;
        }

        System.out.println(arvore.maisForte());
    }

    private static void exibirMaisFraco() {
        System.out.println("\n── PERSONAGEM MAIS FRACO (MENOR NÍVEL) ─────");

        if (arvore.estaVazia()) {
            System.out.println("AVISO: A árvore está vazia.");
            return;
        }

        System.out.println(arvore.maisFraco());
    }

    private static void mostrarQuantidade() {
        System.out.println("\n── QUANTIDADE DE PERSONAGENS ───────────────");
        System.out.println("  Total: " + arvore.quantidade() + " personagem(ns).");
    }

    private static void mostrarAltura() {
        System.out.println("\n── ALTURA DA ÁRVORE ────────────────────────");

        if (arvore.estaVazia()) {
            System.out.println("  Árvore vazia — altura: -1");
            return;
        }

        System.out.println("  Altura: " + arvore.altura() +
            "(0 = apenas raiz, cresce com os níveis da árvore)");
    }

    private static void exibirPreOrdem() {
        System.out.println("\n── PERSONAGENS EM PRÉ-ORDEM ────────────────");
        System.out.println("  (Raiz > Esquerda > Direita)");
        arvore.exibirPreOrdem();
    }

    private static void exibirPosOrdem() {
        System.out.println("\n── PERSONAGENS EM PÓS-ORDEM ────────────────");
        System.out.println("  (Esquerda > Direita > Raiz)");
        arvore.exibirPosOrdem();
    }

    private static void encerrar() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   Sistema encerrado. Até a próxima!  ⚔  ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println();
        scanner.close();
    }

    private static void inserirPersonagensIniciais() {
        arvore.inserir(new Personagem("Galileu",   "Guerreiro", 50, 520, "Espada"));
        arvore.inserir(new Personagem("Angela",   "Mago",      80, 410, "Cajado"));
        arvore.inserir(new Personagem("Jorge",   "Arqueiro",  30, 380, "Arco e Flecha"));
        arvore.inserir(new Personagem("Scorpion",    "Paladino",  65, 600, "Martelo"));
        arvore.inserir(new Personagem("Eduarda",    "Assassino", 15, 220, "Adagas"));
        arvore.inserir(new Personagem("Carlos",     "Druida",    45, 350, "Lança"));
        arvore.inserir(new Personagem("Harry",      "Mago",      90, 300, "Cajado"));
        arvore.inserir(new Personagem("José",     "Guerreiro", 10, 180, "Espada"));

        System.out.println("Foi inserido 8 personagens de exemplo na árvore.");
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("AVISO: Valor inválido. Digite um número inteiro.");
            }
        }
    }

    private static int lerInteiroPositivo(String mensagem, int min, int max) {
        while (true) {
            int valor = lerInteiro(mensagem);
            if (valor >= min && valor <= max) {
                return valor;
            }
            if (max == Integer.MAX_VALUE) {
                System.out.println("AVISO: O valor deve ser maior que " + (min - 1) + ".");
            } else {
                System.out.println("AVISO: O valor deve estar entre " + min + " e " + max + ".");
            }
        }
    }

    private static String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println("AVISO: O campo não pode ser vazio.");
        }
    }
}