package com.example.restaurante.Categorias.Caja;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.R;

import java.util.List;

public class RecyclerViewAdaptadorCajero extends RecyclerView.Adapter<RecyclerViewAdaptadorCajero.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView NombreTXTCajero, ApellidoTXTCajero,DniTXTCajero,EdadTXTCajero,DescripcionTXTCajero;

        public ViewHolder(View view){
            super(view);
            ApellidoTXTCajero = (TextView) view.findViewById(R.id.ApellidoTXTCajero);
            NombreTXTCajero = (TextView)view.findViewById(R.id.NombreTXTCajero);
            DniTXTCajero = (TextView)view.findViewById(R.id.DniTXTCajero);
            EdadTXTCajero = (TextView)view.findViewById(R.id.EdadTXTCajero);
            DescripcionTXTCajero = (TextView)view.findViewById(R.id.DescripcionTXTCajero);
        }
    }

    public List<Cajero> cajeroLista;

    public RecyclerViewAdaptadorCajero(List<Cajero> cajeroLista){
        this.cajeroLista = cajeroLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_cocinero,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ApellidoTXTCajero.setText(cajeroLista.get(position).getApellido());
        holder.NombreTXTCajero.setText(cajeroLista.get(position).getNombre());
        holder.EdadTXTCajero.setText(cajeroLista.get(position).getEdad());
        holder.DescripcionTXTCajero.setText(cajeroLista.get(position).getDescripcion());
        holder.DniTXTCajero.setText(String.valueOf(cajeroLista.get(position).getDni()));
    }

    @Override
    public int getItemCount() {
        return cajeroLista.size();
    }


}
