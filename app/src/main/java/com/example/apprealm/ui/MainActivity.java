package com.example.apprealm.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        profesor = new Profesor();
        etEmail = findViewById(R.id.etEmail);
        etNombre = findViewById(R.id.etName);
        btGuardar = findViewById(R.id.btGuardar);

        configView();
    }

    public void configView() {
        //Guardamos campos
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profesor.setName(etNombre.getText().toString());
                profesor.setEmail(etEmail.getText().toString());
                CrudProfesor.addProfesor(profesor);
                clear();
                etNombre.requestFocus();
            }
        });
        btMostrar = findViewById(R.id.btMostrar);
        btMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrudProfesor.getAllProfesor();
            }
        });

        etNombre.addTextChangedListener(buttonWatcher);
        etEmail.addTextChangedListener(buttonWatcher);
    }

    public void clear() {
        etNombre.setText("");
        etEmail.setText("");

    }

    private TextWatcher buttonWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nameInput = etNombre.getText().toString().trim();
            String emailInput = etEmail.getText().toString().trim();

            btGuardar.setEnabled(!nameInput.isEmpty() && !emailInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
