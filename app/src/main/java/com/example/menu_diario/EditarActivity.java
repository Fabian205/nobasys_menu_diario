package com.example.menu_diario;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.menu_diario.db.DbMenuDiario;
import com.example.menu_diario.entidades.Menues;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText txtTipoMenu, txtDetalle, txtPreparacion, txtCompras;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Menues menu;
    int id = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtTipoMenu = findViewById(R.id.txtTipoMenu);
        //txtCodTipoMenu = findViewById(R.id.txtCodTipomenu);
        txtDetalle = findViewById(R.id.txtDetalle);
        txtPreparacion = findViewById(R.id.txtPreparacion);
        txtCompras = findViewById(R.id.txtCompras);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbMenuDiario dbMenues = new DbMenuDiario(EditarActivity.this);
        menu = dbMenues.verMenu(id);

        if (menu != null) {
            txtTipoMenu.setText(menu.getTipo_menu());
            //txtCodTipoMenu.setText(menu.getCod_tipo_menu());
            txtDetalle.setText(menu.getDetalle());
            txtPreparacion.setText(menu.getPreparacion());
            txtCompras.setText(menu.getCompras());

        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtTipoMenu.getText().toString().equals("") && !txtDetalle.getText().toString().equals("")) {
                    correcto = dbMenues.editarMenues(id, txtTipoMenu.getText().toString(), txtDetalle.getText().toString(), txtPreparacion.getText().toString(), txtCompras.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}