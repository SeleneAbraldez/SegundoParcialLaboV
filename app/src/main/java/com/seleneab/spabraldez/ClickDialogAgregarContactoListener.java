package com.seleneab.spabraldez;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class ClickDialogAgregarContactoListener extends AppCompatActivity implements DialogInterface.OnClickListener {
    // private int index;
    private View view;
    public MainActivity mainActivity;

    public ClickDialogAgregarContactoListener(MainActivity mainActivity, View view) {
        // this.index = indexPasado;
        this.view = view;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(DialogInterface dialog, int boton) {
        if (boton == -1) { //si es la interaccion positiva
            EditText etNombre = view.findViewById(R.id.inputNombre);
            EditText etNumero = view.findViewById(R.id.inputTelefono);

            if (etNombre.getText().toString().length() > 0 && etNumero.getText().toString().length() > 0) {
                SharedPreferences preferences = this.mainActivity.getSharedPreferences("contactosConfig", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = preferences.edit();
                JSONObject json = new JSONObject();
                try {
                    json.put("Nombre", etNombre.getText().toString());
                    json.put("Telefono", etNumero.getText().toString());
                    this.mainActivity.jsonArray.put(json);
                    editor.putString("contacto", this.mainActivity.jsonArray.toString());
                    editor.commit();
                    TextView tv = this.mainActivity.findViewById(R.id.contactosJson);
                    tv.setText(this.mainActivity.jsonArray.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }


    }
}
