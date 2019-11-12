package model.bean;

/**
 *
 * @author fredaum
 */
public class Pneus {
    private int codPneu;
    private String descricao;
    private String medidas;
    private double preco;
    private String ativo;

    /**
     * @return the codPneu
     */
    public int getCodPneu() {
        return codPneu;
    }

    /**
     * @param codPneu the codPneu to set
     */
    public void setCodPneu(int codPneu) {
        this.codPneu = codPneu;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the medidas
     */
    public String getMedidas() {
        return medidas;
    }

    /**
     * @param medidas the medidas to set
     */
    public void setMedidas(String medidas) {
        this.medidas = medidas;
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
     * @return the ativo
     */
    public String getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
    
}
