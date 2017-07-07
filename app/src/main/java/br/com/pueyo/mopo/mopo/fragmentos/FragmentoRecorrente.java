package br.com.pueyo.mopo.mopo.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.pueyo.mopo.mopo.R;
import br.com.pueyo.mopo.mopo.adapters.ListaOrcamentoAdapter;

/**
 * Created by 07669751770 on 03/07/17.
 */

public class FragmentoRecorrente extends Fragment {


    private static final String TAG = FragmentoRecorrente.class.toString();

    protected RecyclerView orcamentoRecorrente;
    protected RecyclerView.LayoutManager orcamentoLayoutManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragmento_recorrente, container, false);


        orcamentoRecorrente = (RecyclerView) rootView.findViewById(R.id.orcamento_recorrente_view);
        orcamentoLayoutManager = new LinearLayoutManager(getActivity());

        orcamentoRecorrente.setHasFixedSize(true);


        RecyclerView.Adapter listaEntradaOrcamentoAdpater = new ListaOrcamentoAdapter();
        orcamentoRecorrente.setAdapter(listaEntradaOrcamentoAdpater);

        return inflater.inflate(R.layout.fragmento_recorrente, container, false);
    }


}
