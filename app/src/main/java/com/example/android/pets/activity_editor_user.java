package com.example.android.pets;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.pets.data.MyOpenHelper;
import com.example.android.pets.data.User;

import java.util.ArrayList;

public class activity_editor_user extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    private EditText id;
    private EditText nombre;
    private EditText correo;
    private EditText fecha_nac;
    private EditText password;

    private Button btnCrear;
    private Button btnVer;
    private Button btnNuevo;
    ///private Button btnEliminar;

    //Declaración del spinner y su Adapter
    private Spinner spinUser;
    private ArrayAdapter spinnerAdapter;

    //Lista de comentarios y comentario actual
    private ArrayList<User> lista;
    private User u;

    //Controlador de bases de datos

    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_user);

        id=(EditText) findViewById(R.id.edit_user_id);
        nombre=(EditText) findViewById(R.id.edit_user_nombre);
        correo=(EditText)findViewById(R.id.edit_user_correo);
        fecha_nac=(EditText) findViewById(R.id.edit_user_fecha_nac);
        password=(EditText)findViewById(R.id.edit_user_password);

        btnCrear=(Button)findViewById(R.id.btnCrear);
        btnVer=(Button)findViewById(R.id.btnVer);
        btnNuevo=(Button)findViewById(R.id.btnNuevo);


        btnCrear.setOnClickListener(this);
        btnVer.setOnClickListener(this);
        btnNuevo.setOnClickListener(this);

        db=new MyOpenHelper(this);

        spinUser = (Spinner)findViewById(R.id.spinUser);
        lista=db.getUsuarios();


        spinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista);
        spinUser.setAdapter(spinnerAdapter);
        spinUser.setOnItemSelectedListener(this);

        id.setVisibility(View.INVISIBLE);
        id.setText("");
        nombre.setText("");
        correo.setText("");
        fecha_nac.setText("");
        password.setText("");


    }


    @Override
    public void onClick(View v) {
        //Acciones de cada boton
        switch(v.getId()){
            case R.id.btnCrear:


                if (id.getText().toString() == "" ) {


                    db.insertar(nombre.getText().toString(),correo.getText().toString(),fecha_nac.getText().toString(),password.getText().toString());


                }
                else
                {

                    db.update(id.getText().toString(),nombre.getText().toString(),correo.getText().toString(),fecha_nac.getText().toString(),password.getText().toString());
                }


                lista=db.getUsuarios();

                spinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista);
                spinUser.setAdapter(spinnerAdapter);

                id.setText("");
                nombre.setText("");
                correo.setText("");
                fecha_nac.setText("");
                password.setText("");


                break;

            case R.id.btnNuevo:

                id.setText("");
                nombre.setText("");
                correo.setText("");
                fecha_nac.setText("");
                password.setText("");

                break;

            case R.id.btnVer:
                //Si hay algun comentario seleccionado mostramos sus valores en la parte inferior
                if(u!=null) {
                    id.setText(String.valueOf(u.getId()));
                    nombre.setText(u.getNombre());
                    correo.setText(u.getCorreo());
                    fecha_nac.setText(u.getFecha_nac());
                    password.setText(u.getPassword());
                }
                break;

    }
}


    //Controlador de elemento seleccionado en el spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinUser) {
            //Si hay elementos en la base de datos, establecemos el comentario actual a partir del
            //indice del elemento seleccionado en el spinner
            if(lista.size()>0) {
                u = lista.get(position);

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_editor_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_usu:
                db.borrar(Integer.parseInt(id.getText().toString()));

                lista=db.getUsuarios();

                spinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista);
                spinUser.setAdapter(spinnerAdapter);

                id.setText("");
                nombre.setText("");
                correo.setText("");
                fecha_nac.setText("");
                password.setText("");

                return true;


           }
        return super.onOptionsItemSelected(item);
    }
}
