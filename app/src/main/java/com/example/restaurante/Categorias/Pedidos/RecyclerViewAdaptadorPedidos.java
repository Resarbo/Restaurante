package com.example.restaurante.Categorias.Pedidos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.R;

import java.util.List;

public class RecyclerViewAdaptadorPedidos extends RecyclerView.Adapter<RecyclerViewAdaptadorPedidos.ViewHolder> {
    private OnClickListener onClickListener;
    public List<Pedido> listaPedidos;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        private TextView NumeroTXTPedido, PlatoTXTPedido, CantidadTXTPedido,EstadoTXTPedido;

        public ViewHolder(View view){
            super(view);
            context = view.getContext();
            NumeroTXTPedido = (TextView)view.findViewById(R.id.NumeroTXTPedido);
            PlatoTXTPedido = (TextView)view.findViewById(R.id.PlatoTXTPedido);
            CantidadTXTPedido = (TextView)view.findViewById(R.id.CantidadTXTPedido);
            EstadoTXTPedido = (TextView)view.findViewById(R.id.EstadoTXTPedido);
        }
    }

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
        Pedido item = listaPedidos.get(position);
        holder.NumeroTXTPedido.setText(String.valueOf(listaPedidos.get(position).getId_pedido()));
        holder.PlatoTXTPedido.setText(String.valueOf(listaPedidos.get(position).getNombrePlato()));
        holder.CantidadTXTPedido.setText(String.valueOf(listaPedidos.get(position).getCantidad()));
        if (listaPedidos.get(position).getEstado()==0){
            holder.EstadoTXTPedido.setText("EN ESPERA");
        } else if (listaPedidos.get(position).getEstado()==1) {
            holder.EstadoTXTPedido.setText("ENTREGADO");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(holder.getAdapterPosition(), item);
                    Intent intent = new Intent(holder.context, PedidoDetalle.class);
                    intent.putExtra("idPedido",holder.NumeroTXTPedido.getText());
                    holder.context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Pedido model);
    }
}
