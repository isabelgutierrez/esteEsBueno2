package com.isabelgutierrez.esteesbueno.gui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isabelgutierrez.esteesbueno.R;
import com.isabelgutierrez.esteesbueno.core.MiscController;
import com.isabelgutierrez.esteesbueno.core.RecetaController;
import com.isabelgutierrez.esteesbueno.gui.components.OnItemClickListener;
import com.isabelgutierrez.esteesbueno.gui.components.RecetasAdapter;
import com.isabelgutierrez.esteesbueno.model.Receta;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecetaFragment extends Fragment implements OnItemClickListener {


    @BindView(R.id.recetasRecycler)
    RecyclerView recetasRecycler;
    Unbinder unbinder;

    private View view;
    private Context context;
    private MiscController miscController = MiscController.Instance();
    private RecetaController recetaController = RecetaController.Instance();

    public RecetaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        configGlobals();
        configView(inflater, container);
        configRecycler();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    private void actualizar() {
        miscController.ShowWait(context, "Consultando clientes...");
        recetaController.GetAll();
    }

    public void actualizar(List<Receta> recetas){
        if(!recetas.isEmpty()){
            recetasRecycler.setAdapter(new RecetasAdapter(recetas, this));
            miscController.CloseWait();
        }
    }

    private void configRecycler() {
        recetasRecycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(context,1);
        recetasRecycler.setLayoutManager(linearLayoutManager);
    }

    private void configGlobals() {
        MainActivity.GLOBALS.put("recetasRecycler", this);
    }

    private void configView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_receta, container, false);
        ButterKnife.bind(this,view);
        context = container.getContext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(Receta receta) {

    }

    @Override
    public void onLongItemClick(Receta receta) {

    }
}
