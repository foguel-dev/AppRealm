package com.example.apprealm.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.apprealm.R;
import com.example.apprealm.crud.CrudProfesor;
import com.example.apprealm.model.Profesor;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etEmail;
    private Button btGuardar, btMostrar;
    private Profesor profesor;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();
        configView();
    }
    public void configView(){
        profesor = new  Profesor();
        etEmail = findViewById(R.id.etEmail);
        etNombre = findViewById(R.id.etName);
        btGuardar = findViewById(R.id.btGuardar);

        //Guardamos campos
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profesor.setName(etNombre.getText().toString());
                profesor.setEmail(etEmail.getText().toString());
                CrudProfesor.addProfesor(profesor);
            }
        });
        btMostrar = findViewById(R.id.btMostrar);
        btMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrudProfesor.getAllProfesor();
            }
        });
    }

}