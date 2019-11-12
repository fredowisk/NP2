package model.bean;



/**
 *
 * @author fredaum
 */
public class Itens {
    
    private NotaFiscal notaFiscal;
    private Pneus pneu;
    private int item;
    private int qtde;
    private double preco;

    
    /**
     * @return the item
     */
    public int getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(int item) {
        this.item = item;
    }

    /**
     * @return the qtde
     */
    public int getQtde() {
        return qtde;
    }

    /**
     * @param qtde the qtde to set
     */
    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * @return the notaFiscal
     */
    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    /**
     * @param notaFiscal the notaFiscal to set
     */
    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    /**
     * @return the pneu
     */
    public Pneus getPneu() {
        return pneu;
    }

    /**
     * @param pneu the pneu to set
     */
    public void setPneu(Pneus pneu) {
        this.pneu = pneu;
    }
    
    
    
}
