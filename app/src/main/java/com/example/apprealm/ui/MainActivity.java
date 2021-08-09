package com.example.apprealm.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apprealm.R;
import com.example.apprealm.crud.CrudProfesor;
import com.example.apprealm.model.Profesor;

import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre, etEmail;
    private Button btGuardar, btMostrar;
    private Profesor profesor;
    private TextView read;
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
        read = findViewById(R.id.read);

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

        //Mostrar profesores
        btMostrar = findViewById(R.id.btMostrar);
        btMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });

        etNombre.addTextChangedListener(buttonWatcher);
        etEmail.addTextChangedListener(buttonWatcher);
    }

    public void clear() {
        etNombre.setText("");
        etEmail.setText("");

    }
    //validacion Boton
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

    private void showData(){
        List<Profesor> dataModels=realm.where(Profesor.class).findAll();
        read.setText("");
        for(int i=0;i<dataModels.size();i++){
            //read.append("ID : "+dataModels.get(i).getId()+" Name : "+dataModels.get(i).getName()+" Age : "+dataModels.get(i).getAge()+" Gender : "+dataModels.get(i).getGender()+" \n");
            read.append("ID: " + profesor.getId() + "Nombre: " + profesor.getName() + " Email: " + profesor.getEmail());
            //Log.d("TAG", "id: " + profesor.getId() + " nombre: " + profesor.getName() + " email: " + profesor.getEmail());


        }
    }
}
