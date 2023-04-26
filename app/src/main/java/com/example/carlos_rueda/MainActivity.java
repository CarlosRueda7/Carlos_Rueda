package com.example.carlos_rueda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Producto> listaPrincipalProductos = new ArrayList<>();
    private RecyclerView rvListadoProducto;

    private AdapatadorPersonalizado miAdaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.txt_listado));
        cargarDatos();

        rvListadoProducto = findViewById(R.id.rv_listado_productos);
         miAdaptador = new AdapatadorPersonalizado(listaPrincipalProductos);
        miAdaptador.setOnItemClickListener(new AdapatadorPersonalizado.onItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {

                Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
                intent.putExtra("producto",miProducto );
                startActivity(intent);

            }

            @Override
            public void onItemEliminarClick(Producto miProducto, int posicion) {
                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                firestore.collection("Producto").document(miProducto.getId()).delete();
                listaPrincipalProductos.remove(posicion);
                miAdaptador.setListadoInformacion(listaPrincipalProductos);
            }
        });
        rvListadoProducto.setAdapter(miAdaptador);
        rvListadoProducto.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onResume() {
        super.onResume();
        listaPrincipalProductos.clear();
        cargarDatos();
    }

    public void cargarDatos(){

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("Producto").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()){

                    for(DocumentSnapshot document : task.getResult()){
                        Producto productoAtrapado = document.toObject(Producto.class);
                        productoAtrapado.setId(document.getId());
                        listaPrincipalProductos.add(productoAtrapado);
                    }
                    miAdaptador.setListadoInformacion(listaPrincipalProductos);

                }else{
                    Toast.makeText(MainActivity.this,"no se pudo conectar al servidor", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void clickCerrarSesion(View view){

        SharedPreferences misreferencias = getSharedPreferences("tienda_app", MODE_PRIVATE);
        SharedPreferences.Editor myEditor = misreferencias.edit();
        myEditor.clear();
        myEditor.apply();
        startActivity(new Intent(this, LoginActivity.class));
        finish();

    }

    public void clickAgregarProducto(View view){
        startActivity(new Intent(this, FormularioActivity.class));
    }


}