package trees;

import java.io.BufferedReader;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    // Vector auxiliar para armazenamento dos nos antes de os inserir na arvore
    private Vector<Node> nodeList = new Vector<Node>();

    // referencia para a raiz da arvore
    private Node raiz = null;

    // metodos para efectuar a visita da arvore
    public void visitaEmOrdem(Node a) {
        if (a != null) {
            visitaEmOrdem(a.getEsquerda());
            System.out.println(a);
            visitaEmOrdem(a.getDireita());
        }
    }

    // Insere nos na arvore garantindo a ordenacao
    // mas nao garantindo que a arvore fique balanceada 
    public Node insereNo(Node r, Node novo) {
        if (r == null) {
            r = novo;
        } else {
            if (novo.getPalavra().compareTo(r.getPalavra()) < 0) {
                r.setEsquerda(insereNo(r.getEsquerda(), novo));
            } else {
                r.setDireita(insereNo(r.getDireita(), novo));
            }
        }
        return r;
    }

    // metodo para contar o numero de nos da arvore
    public int contaNos(Node r) {
        if (r == null) {
            return 0;
        } else {
            return 1 + contaNos(r.getEsquerda()) + contaNos(r.getDireita());
        }
    }

    public void go() throws IOException {
        int v = 0, sinais = 0, contador_p = 0, espacos = 0, carateres = 0, all_carateres = 0;
        File ficheiro = new File("ficheiro.txt");
        BufferedReader leitura = new BufferedReader(new FileReader(ficheiro));
        boolean palavra_existe = false;
        String frase_ficheiro = null;
        
        while ((frase_ficheiro = leitura.readLine()) != null) { 
            if (frase_ficheiro == null) {
                break; 
            }
            
            frase_ficheiro = frase_ficheiro.toLowerCase();
            Scanner ler = new Scanner(frase_ficheiro);
            for (char i : frase_ficheiro.toCharArray()) {  
                if (i == ' ') { //percorre a frase carater a carater que caso seja um espaco irá contabilizar
                    espacos++;
                }
            }
            
            while (ler.hasNext()) {
                
                palavra_existe = false;
                Node n = new Node();
                String palavra = ler.next();
                
                if (palavra.matches("(.)[.,!?](.)")) {
                    sinais++; //contabiliza os sinais encontrados na frase
                }
                palavra = palavra.replace(".,", ""); //substituir os sinais para não aparecerem no nodeList
                
                for (int i = 0; i < nodeList.size(); i++) {
                    if (palavra.equals(nodeList.get(i).getPalavra())) {
                        palavra_existe = true; //se a palavra existir vai acrescentar os valores e adicionando ao node
                        v = nodeList.get(i).getVezes() + 1;
                        nodeList.get(i).setVezes(v);
                        break;
                    }
                }
                
                if (palavra_existe == false) { // caso não haja essa palavra no node irá adicionar ao node
                    v = 1;
                    n.setPalavra(palavra);
                    n.setVezes(v);
                    nodeList.add(n);
                }
            }
        }

        for (int i = 0; i < nodeList.size(); i++) { //vai verificar palavra a palavra
            String tamanho = nodeList.get(i).getPalavra();
            int ocorrencias = nodeList.get(i).getVezes(); // conta a ocorrência de cada palavra
            carateres = carateres + (tamanho.length() * ocorrencias); //conta os carateres das palavras e multiplica o tamanho da palavra pela quantidade de vezes que aparece a palavra
            contador_p = contador_p + ocorrencias;
        }
        all_carateres = carateres + sinais + espacos; // adição de todos os carateres
        
        // Descarrega os nos existentes em nodeList e insere-os na arvore
        for (Node n : nodeList) {
            raiz = insereNo(raiz, n);
        }
        
        System.out.println("PALAVRAS NO FICHEIRO \n");
        visitaEmOrdem(raiz);
        System.out.println("O ficheiro tem de um total de " + contador_p + " palavras, " + nodeList.size() + " palavras diferentes e de " + all_carateres + " caracteres.");
    }

    public static void main(String[] args) throws IOException {
        new Main().go();

    }
}
