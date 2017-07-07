package br.com.pueyo.mopo.mopo.tos;

import java.io.Serializable;

/**
 * Created by 07669751770 on 04/07/17.
 */

public class GraficoConsolidado implements Serializable {

    private Double valorOrcado;
    private Double valorRealizado;
    private String titulo;


    public GraficoConsolidado() {
    }

    public GraficoConsolidado(Double valorOrcado, Double valorRealizado) {
        this.valorOrcado = valorOrcado;
        this.valorRealizado = valorRealizado;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
