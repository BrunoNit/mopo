package br.com.pueyo.mopo.mopo.fragmentos;

import android.support.v4.app.Fragment;

/**
 * Created by 07669751770 on 16/06/17.
 */

public class Fragmento {

    private Fragment fragmento;
    private String titulo;

    public Fragmento() {
    }

    public Fragmento(Fragment fragmento, String titulo) {
        this.fragmento = fragmento;
        this.titulo = titulo;
    }

    public Fragment getFragmento() {
        return fragmento;
    }

    public void setFragmento(Fragment fragmento) {
        this.fragmento = fragmento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
