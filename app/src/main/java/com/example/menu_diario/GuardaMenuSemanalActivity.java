package com.example.menu_diario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class GuardaMenuSemanalActivity extends AppCompatActivity {
    Button btnDes, btnAlm, btnMer;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guarda_menu_semanal);

        //Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinnerDia);
        String[] valores = {"LUNES","MARTES","MIÉRCOLES","JUEVES","VIERNES","SÁBADO", "DOMINGO"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                //Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio
            }
        });
        //Buttons
        btnDes= findViewById(R.id.btnBuscaDes);
        btnAlm= findViewById(R.id.btnBuscaAlm);
        btnMer=findViewById(R.id.btnBuscaMer);
        
        btnDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscaMenuDes();
            }
        });
        
        btnAlm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscaMenuAlm();
            }
        });
        
        btnMer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscaMenuMer();
            }
        });

    }
    public void buscaMenuDes(){
        Intent intent = new Intent(this, VerDesayunoActivity.class);
        startActivity(intent);
    }
    public void buscaMenuAlm(){
        Intent intent = new Intent(this, VerAlmuerzoActivity.class);
        startActivity(intent);
    }
    public void buscaMenuMer(){
        Intent intent = new Intent(this, VerMeriendaActivity.class);
        startActivity(intent);
    }
}