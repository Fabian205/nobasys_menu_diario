package com.example.menu_diario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.SearchView;
import com.example.menu_diario.adaptadores.ListaMenuAdapter;
import com.example.menu_diario.db.DbMenuDiario;
import com.example.menu_diario.entidades.Menues;
import java.util.ArrayList;

public class VerAlmuerzoActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    SearchView txtBuscar;
    RecyclerView listaMenues;
    ArrayList<Menues> listaArrayMenues;
    ListaMenuAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_almuerzo);
        txtBuscar = findViewById(R.id.txtBuscar);
        listaMenues = findViewById(R.id.listaMenues);
        listaMenues.setLayoutManager(new LinearLayoutManager(this));

        DbMenuDiario dbMenuDiario = new DbMenuDiario(VerAlmuerzoActivity.this);

        listaArrayMenues = new ArrayList<>();

        adapter = new ListaMenuAdapter(dbMenuDiario.mostrarMenuesAlm());
        listaMenues.setAdapter(adapter);

        txtBuscar.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtradoDetalle(s);
        return false;
    }
}