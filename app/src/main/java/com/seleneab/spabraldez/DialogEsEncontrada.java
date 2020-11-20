package com.seleneab.spabraldez;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.List;

public class DialogEsEncontrada extends DialogFragment {
    private String titulo;
    private String mensaje;
    private Boolean ok;

    public DialogEsEncontrada(String titulo, String mensaje, Boolean positive) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.ok = positive;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(this.titulo);
        builder.setMessage(this.mensaje);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialogo_es_encontrada, null);
        ClickDialogEsEncontrada listener = new ClickDialogEsEncontrada();

        builder.setView(view);

        if (this.ok) {
            builder.setPositiveButton(R.string.ok, listener);
        }

        return builder.create();
    }


}
