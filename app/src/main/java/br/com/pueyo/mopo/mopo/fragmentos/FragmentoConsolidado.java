package br.com.pueyo.mopo.mopo.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;


import br.com.pueyo.mopo.mopo.R;
import br.com.pueyo.mopo.mopo.adapters.ConsolidadoAdapter;
import br.com.pueyo.mopo.mopo.listeners.RecyclerItemClickListener;
import br.com.pueyo.mopo.mopo.tos.CardConsolidadoDTO;

/**
 * Created by 07669751770 on 16/06/17.
 */

public class FragmentoConsolidado extends Fragment {

    private static final String TAG = "FragmentoConsolidado";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 1;
    private static final int DATASET_COUNT = 60;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RadioButton mLinearLayoutRadioButton;
    protected RadioButton mGridLayoutRadioButton;

    protected RecyclerView carteiraRecycleView;
    protected ConsolidadoAdapter consolidadoAdapter;
    protected RecyclerView.LayoutManager carteiraLayoutManager;
    protected String[] dados;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragmento_consolidado, container, false);

        rootView.setTag(TAG);

        carteiraRecycleView = (RecyclerView) rootView.findViewById(R.id.carteira_recycler_view);

        carteiraLayoutManager = new GridLayoutManager(getActivity(),SPAN_COUNT);

        mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;

        if(savedInstanceState != null){
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER);
        }

        carteiraRecycleView.setHasFixedSize(true);

        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        consolidadoAdapter = new ConsolidadoAdapter(criarBase());

        carteiraRecycleView.setAdapter(consolidadoAdapter);



//        Drawable d = ContextCompat.getDrawable(getActivity(),R.drawable.line_divider);
//
//        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(d);
//
//        carteiraRecycleView.addItemDecoration(decoration);

        carteiraRecycleView.addOnItemTouchListener(new RecyclerItemClickListener(this.getContext(), carteiraRecycleView, new RecyclerItemClickListener
                .OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.w(TAG,"Item Click da posição [" + position +"]");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.w(TAG,"Item Click Longo da posição [" + position +"]");

            }
        }));

       return rootView;
    }

      public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (carteiraRecycleView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) carteiraRecycleView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                carteiraLayoutManager= new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                carteiraLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                carteiraLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        carteiraRecycleView.setLayoutManager(carteiraLayoutManager);
        carteiraRecycleView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    private CardConsolidadoDTO[] criarBase(){

        CardConsolidadoDTO[] cards = new CardConsolidadoDTO[4];
        cards[0] = new CardConsolidadoDTO(getResources().getString(R.string.label_curto_card_recorrente));
        cards[0].setValorOrcado(10000d);
        cards[0].setValorRealizado(20000d);

        cards[1] = new CardConsolidadoDTO(getResources().getString(R.string.label_curto_card_nao_essenciais));
        cards[1].setValorOrcado(10000d);
        cards[1].setValorRealizado(5000d);

        cards[2] = new CardConsolidadoDTO(getResources().getString(R.string.label_curto_card_rendimentos));
        cards[2].setValorOrcado(10000d);
        cards[2].setValorRealizado(30000d);

        cards[3] = new CardConsolidadoDTO(getResources().getString(R.string.label_curto_card_totalizacao));
        cards[3].setValorOrcado(100000d);
        cards[3].setValorRealizado(150000d);

        return cards;
    }



}
