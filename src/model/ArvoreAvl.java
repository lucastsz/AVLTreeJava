package model;

import util.Impressao;


public class ArvoreAvl {

    private No raiz;

    public ArvoreAvl() {
        this.raiz = null;
    }


    // INSERÇÃO -------------------------------------------------

    public void inserir(int element) {
        raiz = inserir(element, raiz);
    }

    private int altura(No no) {
        return no == null ? -1 : no.altura;
    }

    private int alturaMax(int alturaNoEsquerdo, int alturaNoDireito) {
        return alturaNoEsquerdo > alturaNoDireito ? alturaNoEsquerdo : alturaNoDireito;
    }

    private No inserir(int elemento, No no) {
        if (no == null) {
            no = new No(elemento);
        }
        else if (elemento < no.dado) {
            no.esquerdo = inserir(elemento, no.esquerdo);
            if (altura(no.esquerdo) - altura(no.direito) == 2) {

                System.out.println("A arvore está desbalanceada!");
                if (elemento < no.esquerdo.dado) {
                    System.out.println("Realizando uma rotação simples a direita (esquerda do " + no.dado + ")");
                    no = rotacaoSimplesFilhoEsquerdo(no);
                } else {
                    System.out.println("Realizando uma rotação dupla a direita (esquerda do " + no.dado + ")");
                    no = rotacaoDuplaFilhoEsquerdo(no);
                }
            }
        } else if (elemento > no.dado) {
            no.direito = inserir(elemento, no.direito);
            if (altura(no.direito) - altura(no.esquerdo) == 2) {
                System.out.println("A arvore está desbalanceada!");
                if (elemento > no.direito.dado) {
                    System.out.println("Realizando uma rotação simples a esquerda (direita do " + no.dado + ")");
                    no = rotacaoSimplesFilhoDireito(no);
                } else {
                    System.out.println("Realizando uma rotação dupla a esquerda (direita do " + no.dado + ")");
                    no = rotacaoDuplaFilhoDireito(no);
                }
            }
        } else  
            ; 
        no.altura = alturaMax(altura(no.esquerdo), altura(no.direito)) + 1;

        return no;

    }

        // ROTAÇÕES -------------------------------------------------
    
    private No rotacaoSimplesFilhoEsquerdo(No node2) {
        No node1 = node2.esquerdo;
        node2.esquerdo = node1.direito;
        node1.direito = node2;
        node2.altura = alturaMax(altura(node2.esquerdo), altura(node2.direito)) + 1;
        node1.altura = alturaMax(altura(node1.esquerdo), node2.altura) + 1;
        return node1;
    }

    private No rotacaoSimplesFilhoDireito(No node1) {
        No node2 = node1.direito;
        node1.direito = node2.esquerdo;
        node2.esquerdo = node1;
        node1.altura = alturaMax(altura(node1.esquerdo), altura(node1.direito)) + 1;
        node2.altura = alturaMax(altura(node2.direito), node1.altura) + 1;
        return node2;
    }

    private No rotacaoDuplaFilhoEsquerdo(No node3) {
        node3.esquerdo = rotacaoSimplesFilhoDireito(node3.esquerdo);
        return rotacaoSimplesFilhoEsquerdo(node3);
    }

    private No rotacaoDuplaFilhoDireito(No node1) {
        node1.direito = rotacaoSimplesFilhoEsquerdo(node1.direito);
        return rotacaoSimplesFilhoDireito(node1);
    }

    
    // QTD NÓS -------------------------------------------------
    public int qtdNos() {
        return TotNos(raiz);
    }

    private int TotNos(No head) {
        if (head == null) {
            return 0;
        } else {
            int length = 1;
            length = length + TotNos(head.esquerdo);
            length = length + TotNos(head.direito);
            return length;
        }
    }

    public boolean existe(int elemento) {
        return buscaElemento(raiz, elemento);
    }

    private boolean buscaElemento(No raiz, int elemento) {
        boolean check = false;
        while ((raiz != null) && !check) {
            int raizElemento = raiz.dado;
            if (elemento < raizElemento) {
                raiz = raiz.esquerdo;
            } else if (elemento > raizElemento) {
                raiz = raiz.direito;
            } else {
                check = true;
                break;
            }
            check = buscaElemento(raiz, elemento);
        }
        return check;
    }

   
    // BUSCA -------------------------------------------------
    public No buscar(int valor) {

        return buscaRecursiva(raiz, valor);

    }

    private No buscaRecursiva(No no, int valor) {

        if (no == null) {
            return null;
        } else if (no.dado == valor) {
            return no;
        } else {
            if (no.dado > valor) {
                return buscaRecursiva(no.esquerdo, valor);
            }
        }
        return buscaRecursiva(no.direito, valor);
    }
    
    // EXCLUSÃO -------------------------------------------------
        public void exclusao(int chave) {
        raiz = exclusao(raiz, chave);
    }
    
    private No exclusao(No node, int chave) {
        if (node == null) {
            return node;
        } else if (node.dado > chave) {
            node.esquerdo = exclusao(node.esquerdo, chave);
        } else if (node.dado < chave) {
            node.direito = exclusao(node.direito, chave);
        } else {
            if (node.esquerdo == null || node.direito == null) {
                node = (node.esquerdo == null) ? node.direito : node.esquerdo;
            } else {
                No noMaisEsq = nodeMaisEsquerda(node.direito);
                node.dado = noMaisEsq.dado;
                node.direito = exclusao(node.direito, node.dado);
            }
        }
        if (node != null) {
            node = rebalancear(node);
        }
        return node;
    }

    private No nodeMaisEsquerda(No node) {
        No atual = node;
        while (atual.esquerdo != null) {
            atual = atual.esquerdo;
        }
        return atual;
    }

    private No rebalancear(No z) {
        atualizarAltura(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (altura(z.direito.direito) > altura(z.direito.esquerdo)) {
                z = rotacaoEsquerda(z);
            } else {
                z.direito = rotacaoDireita(z.direito);
                z = rotacaoEsquerda(z);
            }
        } else if (balance < -1) {
            if (altura(z.esquerdo.esquerdo) > altura(z.esquerdo.direito)) {
                z = rotacaoDireita(z);
            } else {
                z.esquerdo = rotacaoEsquerda(z.esquerdo);
                z = rotacaoDireita(z);
            }
        }
        return z;
    }

    private No rotacaoDireita(No y) {
        No x = y.esquerdo;
        No z = x.direito;
        x.direito = y;
        y.esquerdo = z;
        atualizarAltura(y);
        atualizarAltura(x);
        return x;
    }

    private No rotacaoEsquerda(No y) {
        No x = y.direito;
        No z = x.esquerdo;
        x.esquerdo = y;
        y.direito = z;
        atualizarAltura(y);
        atualizarAltura(x);
        return x;
    }

    private void atualizarAltura(No n) {
        n.altura = 1 + Math.max(altura(n.esquerdo), altura(n.direito));
    }


    private int getBalance(No n) {
        return (n == null) ? 0 : altura(n.direito) - altura(n.esquerdo);
    }

        public void limparArvore() {
        raiz = null;
    }

    public boolean vazia() {
        if (raiz == null) {
            return true;
        } else {
            return false;
        }
    }

 public void imprimir() {

          Impressao<No> p = new Impressao<>(n -> n.dado + "", n -> n.esquerdo, n -> n.direito);
          p.setSquareBranches(false);
          p.printTree(raiz);

    }

}
