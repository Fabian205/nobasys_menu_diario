package com.example.menu_diario;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.menu_diario.db.DbMenuDiario;

public class NuevoActivity extends AppCompatActivity {

    EditText txtTipoMenu, txtDetalle, txtPreparacion, txtCompras;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtTipoMenu = findViewById(R.id.txtTipoMenu);
        //txtCodTipoMenu = findViewById(R.id.txtCodTipomenu);
        txtDetalle = findViewById(R.id.txtDetalle);
        txtPreparacion=findViewById(R.id.txtPreparacion);
        txtCompras=findViewById(R.id.txtCompras);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtTipoMenu.getText().toString().equals("") && !txtDetalle.getText().toString().equals("")) {

                    DbMenuDiario dbMenues = new DbMenuDiario(NuevoActivity.this);
                    long id = dbMenues.insertarMenu(txtTipoMenu.getText().toString(), txtDetalle.getText().toString(), txtPreparacion.getText().toString(), txtCompras.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                        txtTipoMenu.requestFocus();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        txtTipoMenu.setText("");
        //txtCodTipoMenu.setText("");
        txtDetalle.setText("");
        txtPreparacion.setText("");
        txtCompras.setText("");
    }
}