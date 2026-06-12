
public class Personagem {

    private String nome;
    private String classe;
    private int nivel;
    private int pontosDeVida;
    private String tipoDeArma;

    public Personagem(String nome, String classe, int nivel, int pontosDeVida, String tipoDeArma) {
        this.nome = nome;
        this.classe = classe;
        this.nivel = nivel;
        this.pontosDeVida = pontosDeVida;
        this.tipoDeArma = tipoDeArma;
    }

    public String getNome() {
        return nome;
    }

    public String getClasse() {
        return classe;
    }

    public int getNivel() {
        return nivel;
    }

    public int getPontosDeVida() {
        return pontosDeVida;
    }

    public String getTipoDeArma() {
        return tipoDeArma;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setPontosDeVida(int pontosDeVida) {
        this.pontosDeVida = pontosDeVida;
    }

    public void setTipoDeArma(String tipoDeArma) {
        this.tipoDeArma = tipoDeArma;
    }

    @Override
    public String toString() {
        return String.format(
            "┌─────────────────────────────────────┐\n" +
            "│  Nome        : %-20s │\n" +
            "│  Classe      : %-20s │\n" +
            "│  Nível       : %-20d │\n" +
            "│  Vida        : %-20d │\n" +
            "│  Arma        : %-20s │\n" +
            "└─────────────────────────────────────┘",
            nome, classe, nivel, pontosDeVida, tipoDeArma
        );
    }
}
