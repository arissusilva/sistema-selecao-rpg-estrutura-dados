
public class No {

    Personagem personagem;
    No esquerdo;
    No direito;

    public No(Personagem personagem) {
        this.personagem = personagem;
        this.esquerdo   = null;
        this.direito    = null;
    }
}
