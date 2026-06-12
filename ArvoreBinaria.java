

public class ArvoreBinaria {

    private No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public boolean inserir(Personagem personagem) {
        if (buscar(personagem.getNivel()) != null) {
            return false;
        }
        raiz = inserirRec(raiz, personagem);
        return true;
    }

    private No inserirRec(No no, Personagem personagem) {
        if (no == null) {
            return new No(personagem);
        }

        int nivel = personagem.getNivel();

        if (nivel < no.personagem.getNivel()) {
            no.esquerdo = inserirRec(no.esquerdo, personagem);
        } else if (nivel > no.personagem.getNivel()) {
            no.direito = inserirRec(no.direito, personagem);
        }

        return no;
    }

    public Personagem buscar(int nivel) {
        No resultado = buscarRec(raiz, nivel);
        return (resultado != null) ? resultado.personagem : null;
    }

    private No buscarRec(No no, int nivel) {
        if (no == null) {
            return null;
        }

        if (nivel == no.personagem.getNivel()) {
            return no;
        } else if (nivel < no.personagem.getNivel()) {
            return buscarRec(no.esquerdo, nivel);
        } else {
            return buscarRec(no.direito, nivel);
        }
    }

    public boolean remover(int nivel) {
        if (buscar(nivel) == null) {
            return false;
        }
        raiz = removerRec(raiz, nivel);
        return true;
    }

    private No removerRec(No no, int nivel) {
        if (no == null) {
            return null;
        }

        if (nivel < no.personagem.getNivel()) {
            no.esquerdo = removerRec(no.esquerdo, nivel);

        } else if (nivel > no.personagem.getNivel()) {
            no.direito = removerRec(no.direito, nivel);

        } else {

            if (no.esquerdo == null && no.direito == null) {
                return null;
            }

            if (no.esquerdo == null) {
                return no.direito;
            }

            if (no.direito == null) {
                return no.esquerdo;
            }

            No sucessor = encontrarMinimo(no.direito);
            no.personagem = sucessor.personagem;
            no.direito = removerRec(no.direito, sucessor.personagem.getNivel());
        }

        return no;
    }

    private No encontrarMinimo(No no) {
        if (no.esquerdo == null) {
            return no;
        }
        return encontrarMinimo(no.esquerdo);
    }

    public void exibirEmOrdem() {
        if (raiz == null) {
            System.out.println("  [Árvore vazia]");
            return;
        }
        emOrdemRec(raiz);
    }

    private void emOrdemRec(No no) {
        if (no == null) return;
        emOrdemRec(no.esquerdo);
        System.out.println(no.personagem);
        emOrdemRec(no.direito);
    }

    public void exibirPreOrdem() {
        if (raiz == null) {
            System.out.println("  [Árvore vazia]");
            return;
        }
        preOrdemRec(raiz);
    }

    private void preOrdemRec(No no) {
        if (no == null) return;
        System.out.println(no.personagem);
        preOrdemRec(no.esquerdo);
        preOrdemRec(no.direito);
    }

    public void exibirPosOrdem() {
        if (raiz == null) {
            System.out.println("  [Árvore vazia]");
            return;
        }
        posOrdemRec(raiz);
    }

    private void posOrdemRec(No no) {
        if (no == null) return;
        posOrdemRec(no.esquerdo);
        posOrdemRec(no.direito);
        System.out.println(no.personagem);
    }

    public Personagem maisForte() {
        if (raiz == null) return null;
        return encontrarMaximo(raiz).personagem;
    }

    private No encontrarMaximo(No no) {
        if (no.direito == null) return no;
        return encontrarMaximo(no.direito);
    }

    public Personagem maisFraco() {
        if (raiz == null) return null;
        return encontrarMinimo(raiz).personagem;
    }

    public int quantidade() {
        return quantidadeRec(raiz);
    }

    private int quantidadeRec(No no) {
        if (no == null) return 0;
        return 1 + quantidadeRec(no.esquerdo) + quantidadeRec(no.direito);
    }

    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(No no) {
        if (no == null) return -1;
        int altEsq = alturaRec(no.esquerdo);
        int altDir = alturaRec(no.direito);
        return 1 + Math.max(altEsq, altDir);
    }

    public boolean estaVazia() {
        return raiz == null;
    }
}
