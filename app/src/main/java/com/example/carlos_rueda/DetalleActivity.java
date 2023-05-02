package com.example.carlos_rueda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity {

    private TextView tvTitulo, tv_precioDetalle;
    private ImageView iw_imagenPrincipal;
    private Button btn_editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        setTitle(getString(R.string.txt_detalle));

        tvTitulo = findViewById(R.id.tvTitulo);
        tv_precioDetalle = findViewById(R.id.tv_precio);
        iw_imagenPrincipal = findViewById(R.id.iw_imagen);

        Producto miProductoAtrapado = (Producto) getIntent().getSerializableExtra("producto");
        tvTitulo.setText(miProductoAtrapado.getNombre());
        tv_precioDetalle.setText(miProductoAtrapado.getPrecio().toString());
        Picasso.get()
                .load(miProductoAtrapado.getUrl())
                .error(R.drawable.ic_launcher_background)
                .into(iw_imagenPrincipal);
    }
    public void clickEditarProducto(View view){
        Button btn_editar = findViewById(R.id.btn_editar);

        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        Producto product = (Producto) getIntent().getSerializableExtra("Producto");
        Intent addIntent = new Intent(this,FormularioActivity.class);
        addIntent.putExtra("editar","1");
        addIntent.putExtra("nam", product.getNombre());
        startActivity(addIntent);
        finish();
    }
}