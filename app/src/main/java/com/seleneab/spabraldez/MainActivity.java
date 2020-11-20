package com.seleneab.spabraldez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static JSONArray jsonArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = super.getSupportActionBar();
        actionBar.setTitle(R.string.title);
        actionBar.setDisplayHomeAsUpEnabled(true);

        SharedPreferences preferences = getSharedPreferences("contactosConfig", Context.MODE_PRIVATE);
        String sPref = preferences.getString("user", null);
        TextView tv = findViewById(R.id.contactosJson);

        tv.setText(preferences.getString("contacto", "Sin Contactos!"));

        try {
            jsonArray = new JSONArray(preferences.getString("contacto", "[]"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.opSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.op1) {
            DialogAgregarContacto dialog = new DialogAgregarContacto(this, getString(R.string.editName), getString(R.string.nombre), true, true);
            dialog.show(getSupportFragmentManager(), "agregadx");
        } else if ((item.getItemId() == android.R.id.home)) {
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //Log.d("onQueryTextSubmit", query);
        String nombre = "";
        String telefono = "";
        boolean bandera = false;

        if (this.jsonArray == null) {
            DialogEsEncontrada dialog = new DialogEsEncontrada(getString(R.string.noContactos), getString(R.string.msgNoContactos), true);
            dialog.show(getSupportFragmentManager(), "nulo");
        } else {
            query = query.toLowerCase();
            query = query.substring(0, 1).toUpperCase() + query.substring(1).toLowerCase();

            for (int i = 0; i < this.jsonArray.length(); i++) {
                try {
                    nombre = this.jsonArray.getJSONObject(i).getString("Nombre");
                    telefono = this.jsonArray.getJSONObject(i).getString("Telefono");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (nombre.equals(query)) {
                    bandera = true;
                    break;
                }
            }

            if (bandera) {
                DialogEsEncontrada dialog = new DialogEsEncontrada(getString(R.string.encontrada), getString(R.string.msgEncontrada) + " " + telefono, true);
                dialog.show(getSupportFragmentManager(), "encontradx");
            } else {
                DialogEsEncontrada dialog = new DialogEsEncontrada(getString(R.string.noEncontrada), getString(R.string.msgNoEncontrada), true);
                dialog.show(getSupportFragmentManager(), "no encontradx");
            }
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //Log.d("onQueryTextChange", newText);
        return false;
    }
}