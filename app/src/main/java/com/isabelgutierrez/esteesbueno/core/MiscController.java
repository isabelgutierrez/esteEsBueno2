package com.isabelgutierrez.esteesbueno.core;

import android.app.AlertDialog;
import android.content.Context;

import com.isabelgutierrez.esteesbueno.R;
import com.isabelgutierrez.esteesbueno.model.Receta;

import dmax.dialog.SpotsDialog;

public class MiscController {
    private static MiscController instance = null;
    private AlertDialog alertDialog;
    public static Receta receta;
    public static int indexReceta;


    public MiscController() {
    }

    public static MiscController Instance(){
        if(instance == null)
            instance = new MiscController();
        return instance;
    }

    public void ShowWait(Context context, String message){
        alertDialog = new SpotsDialog(context, message, R.style.CustomProgressBar);
        alertDialog.show();
    }

    public void CloseWait(){
        if(alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
    }
}
