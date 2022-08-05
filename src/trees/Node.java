package trees;

class Node {

    private int vezes;
    private String palavra;
    private Node esquerda;
    private Node direita;

    public int getVezes() {
        return vezes;
    }

    public void setVezes(int v) {
        vezes = v;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public Node getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Node e) {
        esquerda = e;
    }

    public Node getDireita() {
        return direita;
    }

    public void setDireita(Node d) {
        direita = d;
    }

    public String toString() {
        return "A palavra " + palavra + " ocorre " + vezes + " vezes.";
    }
}
