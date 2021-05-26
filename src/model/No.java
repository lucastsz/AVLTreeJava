package model;



public class No {

    int dado;
    //usa a altura para calcular o fator de balanceamento de cada nó
    int altura;
    No esquerdo;
    No direito;

    public No() {
        this.dado = 0;
        direito = null;
        esquerdo = null;
        altura = 0;
    }

    No(int dado) {
        this.dado = dado;
        direito = null;
        esquerdo = null;
        altura = 0;
    }

    int alturaNo(No no){
        if(no == null){
            return -1;
        }
        return altura;
    }
    
    int fatorBalanceamentoNo(No no){
        return alturaNo(no.esquerdo) - alturaNo(no.direito);
    }
    
    int maior (int x, int y){
        if(x > y){
            return x;
        } 
        return y;        
    }
    
    public String noPaiMaiorNoFilho(){
           if ((esquerdo != null) && (direito != null)){
            if (esquerdo.dado > direito.dado){
                return "NÓ " + dado + ": [Esquerdo: " + esquerdo.dado+"]";
            } else {
                return "NÓ " + dado + ": [Direito: " + direito.dado+"]";
            }
        } else if ((esquerdo == null) && (direito == null)){
            return "NÓ: " + dado +" NÃO TEM FILHOS!";
        } else if (esquerdo == null){
            return "NÓ " + dado + ": [Direito: " + direito.dado + "]";
        } 
        
        return "NÓ " + dado + ": [Esquerdo: " + esquerdo.dado + "]";
         
    }

    @Override
    public String toString() {
        
        if ((esquerdo != null) && (direito != null)){
            
        return "NÓ " + dado + ": [Esquerdo: " + esquerdo.dado + "] - [Direito: " + direito.dado + "]";
        
        } else if ((esquerdo == null) && (direito == null)){
            return "NÃO TEM SUBARVORES!";
        } else if (esquerdo == null){
            return "NÓ " + dado + ": [Direito: " + direito.dado + "]";
        } 
        
        return "NÓ " + dado + ": [Esquerdo: " + esquerdo.dado + "]";
        
    }
    public String Imprimir() {
        
        if ((esquerdo != null) && (direito != null)){
            
        return "NÓ " + dado + ": [Esquerdo: " + esquerdo.dado + "] - [Direito: " + direito.dado + "]";
        
        } else if ((esquerdo == null) && (direito == null)){
            return "";
        } else if (esquerdo == null){
            return "NÓ " + dado + ": [Direito: " + direito.dado + "]";
        } 
        
        return "NÓ " + dado + ": [Esquerdo: " + esquerdo.dado + "]";
        
    }
}
