package com.example.restaurante.Categorias.Pedidos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.Categorias.Plato.Plato;
import com.example.restaurante.R;

import java.util.List;

public class RecyclerViewAdaptadorPedidos extends RecyclerView.Adapter<RecyclerViewAdaptadorPedidos.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView NumeroTXTPedido, PlatoTXTPedido, CantidadTXTPedido,EstadoTXTPedido;

        public ViewHolder(View view){
            super(view);
            NumeroTXTPedido = (TextView)view.findViewById(R.id.NumeroTXTPedido);
            PlatoTXTPedido = (TextView)view.findViewById(R.id.PlatoTXTPedido);
            CantidadTXTPedido = (TextView)view.findViewById(R.id.CantidadTXTPedido);
            EstadoTXTPedido = (TextView)view.findViewById(R.id.EstadoTXTPedido);
        }
    }

    public List<Pedido> listaPedidos;

    public RecyclerViewAdaptadorPedidos(List<Pedido> listaPedidos){
        this.listaPedidos = listaPedidos;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_pedidos,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.NumeroTXTPedido.setText(String.valueOf(listaPedidos.get(position).getId_pedido()));
        holder.PlatoTXTPedido.setText(String.valueOf(listaPedidos.get(position).getNombrePlato()));
        holder.CantidadTXTPedido.setText(String.valueOf(listaPedidos.get(position).getCantidad()));
        if (listaPedidos.get(position).getEstado()==0){
            holder.EstadoTXTPedido.setText("EN ESPERA");
        } else if (listaPedidos.get(position).getEstado()==1) {
            holder.EstadoTXTPedido.setText("EN ESPERA");
        }
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }


}
