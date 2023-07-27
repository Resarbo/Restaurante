package com.example.restaurante.Categorias.Plato;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.R;

import java.util.List;

public class RecyclerViewAdaptadorPlatos extends RecyclerView.Adapter<RecyclerViewAdaptadorPlatos.ViewHolder> {
    private OnClickListener onClickListener;
    public List<Plato> cocineroPlato;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        private TextView NombreTXTPlato, PrecioTXTPlato, IdTXTPlato,CantidadTXTPlato,DescripcionTXTPlato;
        private ImageView image;

        public ViewHolder(View view){
            super(view);
            context = view.getContext();
            NombreTXTPlato = (TextView)view.findViewById(R.id.NombreTXTPlato);
            IdTXTPlato = (TextView)view.findViewById(R.id.IdTXTPlato);
            PrecioTXTPlato = (TextView)view.findViewById(R.id.PrecioTXTPlato);
            CantidadTXTPlato = (TextView)view.findViewById(R.id.CantidadTXTPlato);
            DescripcionTXTPlato = (TextView)view.findViewById(R.id.DescripcionTXTPlato);
            image = view.findViewById(R.id.imagenVerPlato);
        }
    }

    public RecyclerViewAdaptadorPlatos(List<Plato> cocineroPlato){
        this.cocineroPlato = cocineroPlato;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_platos,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Plato item = cocineroPlato.get(position);
        holder.NombreTXTPlato.setText(cocineroPlato.get(position).getNombre());
        holder.DescripcionTXTPlato.setText(cocineroPlato.get(position).getDescripcion());
        holder.PrecioTXTPlato.setText(String.valueOf(cocineroPlato.get(position).getPrecio()));
        holder.IdTXTPlato.setText(String.valueOf(cocineroPlato.get(position).getId()));
        holder.CantidadTXTPlato.setText(String.valueOf(cocineroPlato.get(position).getCantidad()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(holder.getAdapterPosition(), item);
                    Intent intent = new Intent(holder.context, PlatoDetalle.class);
                    intent.putExtra("idPlato",holder.IdTXTPlato.getText());

                    holder.context.startActivity(intent);
                }
            }
        });
        byte[] imagenBytes = cocineroPlato.get(position).getImagen();
        Bitmap bitmapImagen = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
        Bitmap result = Bitmap.createScaledBitmap(bitmapImagen, 200, 200, false);
        holder.image.setImageBitmap(bitmapImagen);
    }

    @Override
    public int getItemCount() {
        return cocineroPlato.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Plato model);
    }



}