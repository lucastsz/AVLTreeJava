package arvoreAVL;

import java.util.ArrayList;
import java.util.List;


public class ArvoreBinaria {

    private No raiz;
    private int totElementos;
    private List<No> listaInOrdem;
    private List<No> listaPreOrdem;
    private List<No> listaPosOrdem;

    public ArvoreBinaria() {
        this.raiz = null;
        this.totElementos = 0;
        this.listaInOrdem = new ArrayList();
        this.listaPreOrdem = new ArrayList();
        this.listaPosOrdem = new ArrayList();
    }
    
    
        public double mediaElementos(){
        return soma() / totalElementos();
    }

                
    public int totalElementos() {
        return totElementos;
    }

    public int tamanhoLista(){
        return listaInOrdem.size();
    }

    // PROVA

    public List<No> getListaInOrdem() {
        return listaInOrdem;
    }

    public List<No> getListaPreOrdem() {
        return listaPreOrdem;
    }

    public List<No> getListaPosOrdem() {
        return listaPosOrdem;
    }

        
 
    //QUESTÃO 2 IMPRIMINDO SOMENTE O LADO ESQUERDO
        public void menor() {
        menor(raiz);
        System.out.println("");
    }

    private void menor(No p) {

        if (p != null) {

            menor(p.esquerdo);
            if(p.dado < raiz.dado){
             System.out.print(+p.dado + "  ");   
            }
            
            menor(p.direito);
        }
    }
    
    ////QUESTÃO 2 PEGANDO A MEDIA DA ARVORE
        public void menorMediaArvore() {
        menorMediaArvore(raiz);
        System.out.println("");
    }
        
    private void menorMediaArvore(No p) {

        if (p != null) {

            menorMediaArvore(p.esquerdo);
            if(p.dado < (soma() / totalElementos())){
             System.out.print(p.dado + "  ");   
            }
            
            menorMediaArvore(p.direito);
        }
    }
    
 
    //QUESTÃO 3
        public void estritamenteBinaria() {
        estrita(raiz);
    }

    //PERCORRE EM PRÉ ORDEM    
    private void estrita(No p) {

        if (p != null) {

            if (grauNo(p.dado) < 2 && p.esquerdo != null){
                System.out.println("- Não é estritamente binaria por conta do nó: " + p.dado);
              
            
            
            
            } else if (grauNo(p.dado) < 2 && p.direito != null){
                
              System.out.println("- Não é estritamente binaria por conta do nó: " + p.dado);
          
            }

            estrita(p.esquerdo);
            estrita(p.direito);
        }
        
    }
    
    //QUESTAO 4
       public void NoPaiMaiorFilho() {
        NoPaiMaiorFilho(raiz);
           System.out.println("");
    }
       
    //PERCORRE EM PRÉ ORDEM    
    private void NoPaiMaiorFilho(No p) {

        if (p != null) {

            System.out.print(p.noPaiMaiorNoFilho());
            System.out.println("");

            NoPaiMaiorFilho(p.esquerdo);
            NoPaiMaiorFilho(p.direito);

        }
    }
       public void ImprimirArvore() {
        ImprimirArvore(raiz);
    }
       
    //PERCORRE EM PRÉ ORDEM    
    private void ImprimirArvore(No p) {

        if (p != null) {

            System.out.println(p.Imprimir());

            ImprimirArvore(p.esquerdo);
            ImprimirArvore(p.direito);

        }
    }
    
    // FIM PROVA
    
    public void AlterarLadosArvore(){
        No aux = new No();
        aux.direito = raiz.esquerdo;
        aux.esquerdo = raiz.direito;
        aux.dado = raiz.dado;
        raiz = aux;
        
    }
        
    // RAIZ -------------------------------------------------
    public int valorRaiz(){
    return raiz.dado;
    }

    public No noRaiz(){
    return raiz;
    }

    // REMOVER -----------------------------------------------
    public void remover(int dado) {
        raiz = remover(raiz, dado);
    }

    private No remover(No p, int dado) {
        if (dado < p.dado) {
            p.esquerdo = remover(p.esquerdo, dado);
        } else if (dado > p.dado) {
            p.direito = remover(p.direito, dado);
        } else {
            if (p.esquerdo != null) {
                No aux = maisDireita(p.esquerdo);
                p.dado = aux.dado;
                p.esquerdo = remover(p.esquerdo, aux.dado);
            } else if (p.direito != null) {
                No aux = maisEsquerda(p.direito);
                p.dado = aux.dado;
                p.direito = remover(p.direito, aux.dado);
            } else {
                return null;
            }
        }
        return p;
    }

    // EXTREMIDADES -----------------------------------------------
    public No fimEsquerda() {
        return maisEsquerda(raiz);
    }

    private No maisEsquerda(No p) {
        while (p.esquerdo != null) {
            p = p.esquerdo;
        }
        return p;
    }

    public No fimDireita() {
        return maisDireita(raiz);
    }

    private No maisDireita(No p) {
        while (p.direito != null) {
            p = p.direito;
        }
        return p;
    }

    // INSERÇÃO ----------------------------------------------
    public void inserirI(int dado) {
        No novo = new No(dado);
        if (raiz == null) {
            raiz = novo;
            totElementos++;
        } else {
            No aux = raiz;
            No ant = null;

            while (aux != null) {
                ant = aux;
                if (dado < aux.dado) {
                    aux = aux.esquerdo;
                } else if (dado > aux.dado) {
                    aux = aux.direito;
                } else if (dado == aux.dado) {
                    return;
                }
            }

            if (dado < ant.dado) {
                ant.esquerdo = novo;
                totElementos++;
            } else {
                ant.direito = novo;
                totElementos++;
            }
        }
    }

    
    
    
    // ALTURA ------------------------------------------------
    public int altura(int valor) {
        int valorAltura = altura(buscar(valor));
        return valorAltura -1;
    }

    private int altura(No p) {

        if (p != null) {
            int he, hd;

            he = altura(p.esquerdo);
            hd = altura(p.direito);

            if (he > hd) {
                return he + 1;
            } else {
                return hd + 1;
            }
        }

        return 0;
    }

    // SOMA ----------------------------------------------
    public int soma() {
        return soma(raiz);
    }

    private int soma(No p) {

        if (p != null) {
            return soma(p.esquerdo) + soma(p.direito) + p.dado;
        }
        return 0;
    }

    // ESTÁ VAZIA -------------------------------------------------
    public boolean estaVazia() {
        return this.raiz == null;
    }




    // SUBARVORES -------------------------------------------------
    public No subArvores(int valor){
    return buscar(valor);
    }


    
// GRAU DE UMA RAIZ -------------------------------------------------
    public int grauNo(int valor){
        if (buscar(valor).direito == null && buscar(valor).esquerdo == null){
            return 0;
        } else if (buscar(valor).direito != null && buscar(valor).esquerdo != null){
            return 2;
        } else {
            return 1;
        }
    }

    
// NIVEL DE UMA RAIZ -------------------------------------------------
        
  public int nivelNo(int valor){
      return nivelNo(raiz, valor);
  }
    
    private int nivelNo(No no, int valor){
    
    if (no == null){
        return -1;
    } else if (no.dado == valor){
        return 0;
    } else {
        if (no.dado > valor){
            return nivelNo(no.esquerdo, valor) + 1;
        } 
    }
    return nivelNo(no.direito, valor) + 1;
    
}    
    


// PROFUNDIDADE DE UMA RAIZ -------------------------------------------------
public int profundidadeNo(int valor){
    return nivelNo(raiz, valor);
}  
    
  
 
// VERIFICAÇÃO -------------------------------------------------
public boolean existe(int valor){
    
    if (buscar(valor) != null){
        return true;
    }
    return false;
}
    
    
    
 
// BUSCA -------------------------------------------------
 public No buscar(int valor){
     
return buscaRecursiva(raiz, valor);

 }

private No buscaRecursiva(No no, int valor){
    
    if (no == null){
        return null;
    } else if (no.dado == valor){
        return no;
    } else {
        if (no.dado > valor){
            return buscaRecursiva(no.esquerdo, valor);
        } 
    }
    return buscaRecursiva(no.direito, valor);
}



    
    // PRE-ORDEM - NLR ----------------------------------------------
    public void preOrdem() {
        listaPreOrdem.clear();
        preOrdem(raiz);
        System.out.println("");
    }

    private void preOrdem(No p) {

        if (p != null) {

            System.out.print(p.dado + " ");
            listaPreOrdem.add(p);

            preOrdem(p.esquerdo);
            preOrdem(p.direito);

        }
    }

    
    // IN-ORDEM - LNR ----------------------------------------------
    public void inOrdem() {
        listaInOrdem.clear();
        inOrdem(raiz);
        System.out.println("");
    }

    private void inOrdem(No p) {

        if (p != null) {

            inOrdem(p.esquerdo);
            System.out.print(p.dado + " ");
            listaInOrdem.add(p);
            inOrdem(p.direito);
        }
    }
    
    // IN-ORDEM INVERSA - LNR ----------------------------------------------
    private void inOrdemInversa(No p){
        
        if (p != null){
            
            if (raiz.direito != null) {
                inOrdemInversa(p.direito);
            }
            System.out.print(p.dado + " ");

            if (raiz.esquerdo != null) {
                inOrdemInversa(p.esquerdo);
            }
        }  
    }
    
        public void inOrdemInversa() {
        inOrdemInversa(raiz);
        System.out.println("");
    }
    

    // POS-ORDEM - LRN ----------------------------------------------
    public void posOrdem() {
        listaPosOrdem.clear();
        posOrdem(raiz);
        System.out.println("");
    }

    private void posOrdem(No p) {

        if (p != null) {

            posOrdem(p.esquerdo);
            posOrdem(p.direito);
            System.out.print(p.dado + " ");
            listaPosOrdem.add(p);
        }

    }

    @Override
    public String toString() {
        return raiz.noPaiMaiorNoFilho();
    }

     public void imprimir() {

          TreePrinter<No> p = new TreePrinter<>(n -> n.dado + "", n -> n.esquerdo, n -> n.direito);
          p.setSquareBranches(false);
          p.printTree(raiz);

    }
    

}
