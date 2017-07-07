package br.com.pueyo.mopo.mopo.tos;

/**
 * Created by 07669751770 on 03/07/17.
 */

public class CardConsolidadoDTO {

    private String titulo;

    private Double valorOrcado;
    private Double valorRealizado;

    public CardConsolidadoDTO(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getValorOrcado() {
        return valorOrcado;
    }

    public void setValorOrcado(Double valorOrcado) {
        this.valorOrcado = valorOrcado;
    }

    public Double getValorRealizado() {
        return valorRealizado;
    }

    public void setValorRealizado(Double valorRealizado) {
        this.valorRealizado = valorRealizado;
    }
}
