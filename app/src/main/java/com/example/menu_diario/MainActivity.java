package com.example.menu_diario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.menu_diario.adaptadores.ListaMenuAdapter;
import com.example.menu_diario.db.DbMenuDiario;
import com.example.menu_diario.db.DbHelper;
import com.example.menu_diario.entidades.Menues;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    //Button btnCrear;
    SearchView txtBuscar;
    RecyclerView listaMenues;
    ArrayList<Menues> listaArrayMenues;
    FloatingActionButton fabNuevo;
    ListaMenuAdapter adapter;
    ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //********** se utiliza el boton para crear la base de datos*********//
        /*btnCrear = findViewById(R.id.btnCrearDb);
        btnCrear.setOnClickListener(new  View.OnClickListener(){

            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db != null){
                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA", Toast. LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "ERROR AL CREAR LA BASE DE DATOS", Toast. LENGTH_LONG).show();
                }
            }
        });*/
        //*****************************************************************//

        txtBuscar = findViewById(R.id.txtBuscar);
        listaMenues = findViewById(R.id.listaMenues);
        fabNuevo = findViewById(R.id.favNuevo);
        imagen = findViewById(R.id.imageView);

        listaMenues.setLayoutManager(new LinearLayoutManager(this));

        DbMenuDiario dbMenuDiario = new DbMenuDiario(MainActivity.this);

        listaArrayMenues = new ArrayList<>();

        adapter = new ListaMenuAdapter(dbMenuDiario.mostrarMenues());
        listaMenues.setAdapter(adapter);

        fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoRegistro();
            }
        });

        txtBuscar.setOnQueryTextListener(this);
    }
    public void onclick(View view){
        cargarImagen();
    }

    private void cargarImagen() {
        Toast.makeText(this, "ojo bien", Toast.LENGTH_SHORT).show();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;
            case R.id.semanaNueva:
                nuevaSemana();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }

    private void nuevaSemana(){
        Intent intent = new Intent(this, GuardaMenuSemanalActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}
