package com.example.carlos_rueda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class FormularioActivity extends AppCompatActivity {
    private EditText etNombre, etPrecio, etImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNombre = findViewById(R.id.etNombre);
        etPrecio = findViewById(R.id.etPrecio);
        etImagen = findViewById(R.id.etImagen);
    }
    public void clickGuardar(View view){
        String nombre = etNombre.getText().toString();
        Double precio = Double.parseDouble(etPrecio.getText().toString());
        String url = etImagen.getText().toString();

        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(nombre);
        nuevoProducto.setPrecio(precio);
        nuevoProducto.setUrl(url);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("Producto").add(nuevoProducto);

        Toast.makeText(this, "SE CREO PRODUCTO", Toast.LENGTH_SHORT).show();
        finish();
    }
}