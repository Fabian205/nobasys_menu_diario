package com.example.menu_diario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menu_diario.db.DbMenuDiario;
import com.example.menu_diario.entidades.Menues;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerMenuesActivity extends AppCompatActivity {

    TextView txtvTipoMenu, txtvDetalle, txtvPreparacion, txtvCompras;
    FloatingActionButton fabEditar, fabEliminar,fabGuardaMenu;
    Menues menu;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_menues);

        txtvTipoMenu = findViewById(R.id.txtv_TipoMenu);
        //txtvCodTipoMenu = findViewById(R.id.txtv_CodTipoMenu);
        txtvDetalle = findViewById(R.id.txtv_Detalle);
        txtvPreparacion = findViewById(R.id.txtv_Preparacion);
        txtvCompras = findViewById(R.id.txtv_Compras);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabGuardaMenu = findViewById(R.id.fabIncluirMenu);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbMenuDiario dbMenues = new DbMenuDiario(VerMenuesActivity.this);
        menu = dbMenues.verMenu(id);

        if(menu != null){
            txtvTipoMenu.setText(menu.getTipo_menu());
            //txtvCodTipoMenu.setText(menu.getCod_tipo_menu());
            txtvDetalle.setText(menu.getDetalle());
            txtvPreparacion.setText(menu.getPreparacion());
            txtvCompras.setText(menu.getCompras());

        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerMenuesActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerMenuesActivity.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbMenues.eliminarMenues(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

        fabGuardaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardaMenu();

            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void guardaMenu(){
        txtvTipoMenu.getText().toString();
        //txtvCodTipoMenu.getText().toString();
        txtvDetalle.getText().toString();
        txtvPreparacion.getText().toString();
        txtvCompras.getText().toString();
        Toast.makeText(VerMenuesActivity.this, txtvTipoMenu.getText().toString() +txtvDetalle.getText().toString() + "Prueba ok con metodo", Toast.LENGTH_LONG).show();
    }
}