package com.isabelgutierrez.esteesbueno.gui.components;

import com.isabelgutierrez.esteesbueno.model.Receta;

public interface OnItemClickListener {
    void onItemClick(Receta receta);
    void onLongItemClick(Receta receta);
}
