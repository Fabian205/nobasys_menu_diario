package com.example.menu_diario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.menu_diario.db.DbMenuDiario;
import com.example.menu_diario.entidades.Menues;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtTipoMenu, txtDetalle,  txtPreparacion, txtCompras;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;

    Menues menu;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtTipoMenu = findViewById(R.id.txtTipoMenu);
        //txtCodTipoMenu = findViewById(R.id.txtCodTipomenu);
        txtDetalle = findViewById(R.id.txtDetalle);
        txtPreparacion = findViewById(R.id.txtPreparacion);
        txtCompras = findViewById(R.id.txtCompras);

        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnGuarda.setVisibility(View.INVISIBLE);

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

        final DbMenuDiario dbMenues = new DbMenuDiario(VerActivity.this);
        menu = dbMenues.verMenu(id);

        if(menu != null){
            txtTipoMenu.setText(menu.getTipo_menu());
            //txtCodTipoMenu.setText(menu.getCod_tipo_menu());
            txtDetalle.setText(menu.getDetalle());
            txtPreparacion.setText(menu.getPreparacion());
            txtCompras.setText(menu.getCompras());

            /*txtTipoMenu.setInputType(InputType.TYPE_NULL);
            txtCodTipoMenu.setInputType(InputType.TYPE_NULL);
            txtDetalle.setInputType(InputType.TYPE_NULL);
            txtPreparacion.setInputType(InputType.TYPE_NULL);
            txtCompras.setInputType(InputType.TYPE_NULL);*/
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
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
    }

    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}