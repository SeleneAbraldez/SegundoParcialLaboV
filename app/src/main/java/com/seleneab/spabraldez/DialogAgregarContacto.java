package com.seleneab.spabraldez;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogAgregarContacto extends DialogFragment {
    private String mensaje;
    private String titulo;
    private Boolean negative;
    private Boolean positive;
    private int index;
    public MainActivity mainActivity;



    public DialogAgregarContacto(MainActivity mainActivity, String titulo, String mensaje, Boolean positive, Boolean negative) {
        this.mainActivity = mainActivity;
        this.titulo = titulo;
//        this.mensaje = mensaje;
        this.positive = positive;
        this.negative = negative;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(this.titulo);
//        builder.setMessage(this.mensaje);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialogo_agregar_contacto, null);
        ClickDialogAgregarContactoListener listener = new ClickDialogAgregarContactoListener(this.mainActivity, view);

        builder.setView(view);

        if (this.positive) {
            builder.setPositiveButton(R.string.save, listener);
        }
        if (this.negative) {
            builder.setNegativeButton(R.string.cancel, listener);
        }

        return builder.create();
    }

}
