package com.example.carlos_rueda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Producto> listaPrincipalProductos;
    private RecyclerView rvListadoProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.txt_listado));
        cargarDatos();

        rvListadoProducto = findViewById(R.id.rv_listado_productos);
        AdapatadorPersonalizado miAdaptador = new AdapatadorPersonalizado(listaPrincipalProductos);
        miAdaptador.setOnItemClickListener(new AdapatadorPersonalizado.onItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {

                Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
                intent.putExtra("producto",miProducto);
                startActivity(intent);

            }

            @Override
            public void onItemEliminarClick(Producto miProducto, int posicion) {
                listaPrincipalProductos.remove(posicion);
                miAdaptador.setListadoInformacion(listaPrincipalProductos);
            }
        });
        rvListadoProducto.setAdapter(miAdaptador);
        rvListadoProducto.setLayoutManager(new LinearLayoutManager(this));


    }
    public void cargarDatos(){
        Producto producto1 = new Producto("computador",8000000.0, "https://www.alkosto.com/medias/196337755727-001-750Wx750H?context=bWFzdGVyfGltYWdlc3wxMzkzOTd8aW1hZ2UvanBlZ3xpbWFnZXMvaGNmL2hiMC8xMTYzNzg2ODg4ODA5NC5qcGd8YmQxOTMwNzc3ZThlYjQ2ZDRjNjJhNzFlMzUxOGQ1Y2U5N2YxOTM2NTljMTNmZDdhODVlN2VmNWUwNjBhMjQ5ZA");
        Producto producto2 = new Producto("teclado",150000.0, "https://cdn.shopify.com/s/files/1/0354/1827/5972/products/Featured.png?v=1659643419");
        Producto producto3 = new Producto("mouse",100000.0,"https://panamericana.vtexassets.com/arquivos/ids/382920/mouse-inalambrico-gaming-g305-logitech-color-negro-97855137708.jpg?v=637490239719130000");
        listaPrincipalProductos = new ArrayList<>();
        listaPrincipalProductos.add(producto1);
        listaPrincipalProductos.add(producto2);
        listaPrincipalProductos.add(producto3);




    }
}