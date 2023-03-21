package com.example.carlos_rueda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity {

    private TextView tvTitulo, tv_precioDetalle;
    private ImageView iw_imagenPrincipal;

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
}