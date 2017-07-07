package br.com.pueyo.mopo.mopo.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.constraint.solver.Cache;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.pueyo.mopo.mopo.R;
import br.com.pueyo.mopo.mopo.javascript.WebAppCardConsoliadoInterface;
import br.com.pueyo.mopo.mopo.tos.CardConsolidadoDTO;
import br.com.pueyo.mopo.mopo.tos.GraficoConsolidado;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class ConsolidadoAdapter extends RecyclerView.Adapter<ConsolidadoAdapter.ViewHolder>  {
    private static final String TAG = ConsolidadoAdapter.class.toString();

    private CardConsolidadoDTO[] mDataSet;
    private AdapterView.AdapterContextMenuInfo info;

    Activity activity;


    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public ConsolidadoAdapter(CardConsolidadoDTO[] dataSet) {
        mDataSet = dataSet;
    }

    public ConsolidadoAdapter() {
    }

    public ConsolidadoAdapter(CardConsolidadoDTO[] cardConsolidadoDTOs, FragmentActivity activity) {
        this.activity = activity;
        this.mDataSet = cardConsolidadoDTOs;
    }

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private final TextView tituloCard;
        private WebView graficoContainer;
        private ProgressBar progress;
//        private TextView valorOrcado;
//        private TextView valorRealizado;



        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
//                }
//            });

            tituloCard = (TextView) v.findViewById(R.id.tituloCardConsolidado);
            graficoContainer = (WebView) v.findViewById(R.id.graficoBarras);
            progress = (ProgressBar) v.findViewById(R.id.determinateBar);
//            valorOrcado = (TextView) v.findViewById(R.id.valorTotalOrcado);
//            valorRealizado = (TextView) v.findViewById(R.id.valorTotalRealizado);
        }

        public TextView getTituloCard() {
            return tituloCard;
        }

        public WebView getGraficoContainer() {
            return graficoContainer;
        }

//        public TextView getValorOrcado() {
//            return valorOrcado;
//        }
//
//        public TextView getValorRealizado() {
//            return valorRealizado;
//        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            Log.w(TAG, "###### onCreateContextMenu #######");
        }

        public ProgressBar getBarraProgresso() {
            return progress;
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        CardView v = (CardView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_consolidado, viewGroup, false);

      return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        CardConsolidadoDTO card = mDataSet[position];
        viewHolder.getTituloCard().setText(card.getTitulo());

        GraficoConsolidado grafico = new GraficoConsolidado();
        grafico.setTitulo(card.getTitulo());
        grafico.setValorOrcado(card.getValorOrcado());
        grafico.setValorRealizado(card.getValorRealizado());


//        viewHolder.getValorOrcado().setText(String.valueOf(this.valorOrcado));
//        viewHolder.getValorRealizado().setText(String.valueOf(this.valorRealizado));

        viewHolder.getGraficoContainer().addJavascriptInterface(new WebAppInterface(grafico), "android");

        viewHolder.getGraficoContainer().getSettings().setJavaScriptEnabled(true);
        viewHolder.getGraficoContainer().getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        viewHolder.getGraficoContainer().setWebViewClient(new WebViewClient(){

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                view.loadUrl("file:///android_asset/erro.jpg");
            }
        });
        viewHolder.getGraficoContainer().setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress < 100){
                    viewHolder.getBarraProgresso().setVisibility(View.VISIBLE);
                    viewHolder.getBarraProgresso().setProgress(newProgress);
                }else{
                    viewHolder.getBarraProgresso().setVisibility(View.GONE);
                }

            }
        });

        viewHolder.getGraficoContainer().setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        viewHolder.getGraficoContainer().getSettings().setAppCachePath("/data/data/br.com.pueyo.mopo.mopo/cache");
        viewHolder.getGraficoContainer().getSettings().setAppCacheEnabled(true);
        viewHolder.getGraficoContainer().loadUrl("file:///android_asset/graficoConsolidado2.html");




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    class WebAppInterface{
        private GraficoConsolidado grafico;

        public WebAppInterface(GraficoConsolidado grafico) {
            this.grafico = grafico;
        }

        @JavascriptInterface
        public double getValorOrcado(){
            return this.grafico.getValorOrcado();
        }

        @JavascriptInterface
        public double getValorRealizado(){
            return this.grafico.getValorRealizado();
        }


        @JavascriptInterface
        public String getTitulo(){
            return this.grafico.getTitulo();
        }
    }
}


