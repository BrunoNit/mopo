package br.com.pueyo.mopo.mopo.javascript;

import android.webkit.JavascriptInterface;

import br.com.pueyo.mopo.mopo.tos.GraficoConsolidado;

/**
 * Created by 07669751770 on 04/07/17.
 */

public class WebAppCardConsoliadoInterface {





    private String tituloVertical;
    private String tituloHorizontal;
    private GraficoConsolidado grafico;


    public WebAppCardConsoliadoInterface(GraficoConsolidado graficoConsolidado) {
        this.grafico = graficoConsolidado;
    }



    @JavascriptInterface
    public String getTituloVertical() {
        return tituloVertical;
    }

    public void setTituloVertical(String tituloVertical) {
        this.tituloVertical = tituloVertical;
    }

    @JavascriptInterface
    public String getTituloHorizontal() {
        return tituloHorizontal;
    }

    public void setTituloHorizontal(String tituloHorizontal) {
        this.tituloHorizontal = tituloHorizontal;
    }

    @JavascriptInterface
    public Double getValorOrcado() {
        return grafico.getValorOrcado();
    }

    @JavascriptInterface
    public Double getValorRealizado() {

        return grafico.getValorRealizado();
    }

    @JavascriptInterface
    public double getTitulo() {
        //return grafico.getTitulo();
        return 10d;
    }


}
