package com.example.android.pets.data;

public class User {

    int id;
    String nombre;
    String correo;
    String fecha_nac;
    String password;

    //Constructor
    public User(int _id,String _nombre,String _correo,String _fecha_nac, String _password){
        id=_id;
        nombre=_nombre;
        correo=_correo;
        fecha_nac=_fecha_nac;
        password=_password;

    }

    //Represetacion del objeto como cadena de texto
    @Override
    public String toString() {
        return nombre;
    }

    //Metodos de acceso a cada atribito de la clase
    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getCorreo(){
        return correo;
    }

    public String getFecha_nac(){
        return fecha_nac;
    }

    public String getPassword(){
        return password;
    }



}
