package com.example.apprealm.crud;

import android.util.Log;

import com.example.apprealm.model.Profesor;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class CrudProfesor {
    private final static int calculateIndex(){
        Realm realm = Realm.getDefaultInstance();
        // De todos los objetos de profesor devuelva el valor maximo de id
        Number currentId = realm.where(Profesor.class).max("id");

        //Si la bd esta vacia
        int nextId;
        if(currentId == null){
            nextId = 0;
            //Para autoincrement
        } else {
            nextId = currentId.intValue() + 1;
        }
        return nextId;
    }

    public final static void addProfesor(final Profesor profesor){
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(realm1 -> {
                int index = CrudProfesor.calculateIndex();
                Profesor realmProfesor = realm1.createObject(Profesor.class, index);
                realmProfesor.setName(profesor.getName());
                realmProfesor.setEmail(profesor.getEmail());
            });
    }

    public final static List<Profesor> getAllProfesor(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Profesor> profesors =  realm.where(Profesor.class).findAll();
        for (Profesor profesor: profesors){
            Log.d("TAG", "id: " + profesor.getId()  + " nombre: " + profesor.getName() + " email: " + profesor.getEmail());
        }
            return profesors;
    }
}

