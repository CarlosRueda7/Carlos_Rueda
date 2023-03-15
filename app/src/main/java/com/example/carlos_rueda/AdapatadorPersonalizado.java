package com.example.carlos_rueda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapatadorPersonalizado extends RecyclerView.Adapter<AdapatadorPersonalizado.ViewHolder> {

    private ArrayList<Producto> listadoInformacion;
    private onItemClickListener onItemClickListener;

    public void setListadoInformacion(ArrayList<Producto> listadoInformacion) {
        this.listadoInformacion = listadoInformacion;
        notifyDataSetChanged();
    }

    public AdapatadorPersonalizado(ArrayList<Producto> listadoInformacion) {
        this.listadoInformacion = listadoInformacion;
        this.onItemClickListener = null;
    }

    public void setOnItemClickListener(AdapatadorPersonalizado.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AdapatadorPersonalizado.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miVew = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_producto,parent,false);

        return new ViewHolder(miVew);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapatadorPersonalizado.ViewHolder holder, int position) {
        Producto miProducto = listadoInformacion.get(position);
        holder.enlazar(miProducto);


    }

    @Override
    public int getItemCount() {
        return listadoInformacion.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNombre, tvPrecio;
        private ImageView ivProducto;
        private Button btnEliminar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tv_item_nombre);
            tvPrecio = itemView.findViewById(R.id.tv_item_precio);
            ivProducto = itemView.findViewById(R.id.iv_item_imagen2);
            btnEliminar = itemView.findViewById(R.id.btn_item_eliminar);
        }

        public void enlazar(Producto miProducto){
            tvNombre.setText(miProducto.getNombre());
            tvPrecio.setText(miProducto.getPrecio().toString());
            Picasso.get()
                    .load(miProducto.getUrl())
                    .error(R.drawable.ic_launcher_background)
                    .into(ivProducto);
            if(onItemClickListener != null){

            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(miProducto,getAdapterPosition());
                }
            });
            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemEliminarClick(miProducto,getAdapterPosition());
                }
            });
        }
    }

    public interface onItemClickListener{
        void onItemClick(Producto miProducto, int posicion);
        void onItemEliminarClick(Producto miProducto, int posicion);
    }

}
