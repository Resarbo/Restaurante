package com.example.restaurante.Categorias.Mesero;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.R;

import java.util.List;

public class RecyclerViewAdaptadorMesero extends RecyclerView.Adapter<RecyclerViewAdaptadorMesero.ViewHolder> {
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

    public List<Mesero> meserosLista;

    public RecyclerViewAdaptadorMesero(List<Mesero> meserosLista){
        this.meserosLista = meserosLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_mesero,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ApellidoTXTCocinero.setText(meserosLista.get(position).getApellido());
        holder.NombreTXTCocinero.setText(meserosLista.get(position).getNombre());
        holder.EdadTXTCocinero.setText(meserosLista.get(position).getEdad());
        holder.DescripcionTXTCocinero.setText(meserosLista.get(position).getDescripcion());
        holder.DniTXTCocinero.setText(String.valueOf(meserosLista.get(position).getDni()));
    }

    @Override
    public int getItemCount() {
        return meserosLista.size();
    }


}
