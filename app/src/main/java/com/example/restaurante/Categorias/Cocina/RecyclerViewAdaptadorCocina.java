package com.example.restaurante.Categorias.Cocina;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.Categorias.Caja.Cajero;
import com.example.restaurante.R;

import java.util.List;

public class RecyclerViewAdaptadorCocina extends RecyclerView.Adapter<RecyclerViewAdaptadorCocina.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView NombreTXTCocinero, ApellidoTXTCocinero,DniTXTCocinero,EdadTXTCocinero,DescripcionTXTCocinero;

        public ViewHolder(View view){
            super(view);
            ApellidoTXTCocinero = (TextView) view.findViewById(R.id.ApellidoTXTCocinero);
            NombreTXTCocinero = (TextView)view.findViewById(R.id.NombreTXTCocinero);
            DniTXTCocinero = (TextView)view.findViewById(R.id.DniTXTCocinero);
            EdadTXTCocinero = (TextView)view.findViewById(R.id.EdadTXTCocinero);
            DescripcionTXTCocinero = (TextView)view.findViewById(R.id.DescripcionTXTCocinero);
        }
    }

    public List<Cocinero> cocineroLista;

    public RecyclerViewAdaptadorCocina(List<Cocinero> cocineroLista){
        this.cocineroLista = cocineroLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_cocinero,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ApellidoTXTCocinero.setText(cocineroLista.get(position).getApellido());
        holder.NombreTXTCocinero.setText(cocineroLista.get(position).getNombre());
        holder.EdadTXTCocinero.setText(cocineroLista.get(position).getEdad());
        holder.DescripcionTXTCocinero.setText(cocineroLista.get(position).getDescripcion());
        holder.DniTXTCocinero.setText(String.valueOf(cocineroLista.get(position).getDni()));
    }

    @Override
    public int getItemCount() {
        return cocineroLista.size();
    }


}
