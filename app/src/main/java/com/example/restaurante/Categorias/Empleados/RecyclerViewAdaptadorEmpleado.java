package com.example.restaurante.Categorias.Empleados;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.Categorias.Plato.Plato;
import com.example.restaurante.Categorias.Plato.PlatoDetalle;
import com.example.restaurante.Categorias.Plato.RecyclerViewAdaptadorPlatos;
import com.example.restaurante.R;

import java.util.List;

public class RecyclerViewAdaptadorEmpleado extends RecyclerView.Adapter<RecyclerViewAdaptadorEmpleado.ViewHolder> {
    private OnClickListener onClickListener;
    public List<Empleado> empleadoLista;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        private TextView NombreTXTEmpleado, ApellidoTXTEmpleado, IdTXTEmpleado,DniTXTEmpleado,DescripcionTXTEmpleado;

        public ViewHolder(View view){
            super(view);
            context = view.getContext();
            NombreTXTEmpleado = (TextView) view.findViewById(R.id.NombreTXTEmpleado);
            ApellidoTXTEmpleado = (TextView)view.findViewById(R.id.ApellidoTXTEmpleado);
            IdTXTEmpleado = (TextView)view.findViewById(R.id.IdTXTEmpleado);
            DniTXTEmpleado = (TextView)view.findViewById(R.id.DniTXTEmpleado);
            DescripcionTXTEmpleado = (TextView)view.findViewById(R.id.DescripcionTXTEmpleado);
        }
    }


    public RecyclerViewAdaptadorEmpleado(List<Empleado> empleadoLista){
        this.empleadoLista = empleadoLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_empleado,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Empleado item = empleadoLista.get(position);
        holder.NombreTXTEmpleado.setText(empleadoLista.get(position).getNombre());
        holder.ApellidoTXTEmpleado.setText(empleadoLista.get(position).getApellido());
        holder.IdTXTEmpleado.setText(String.valueOf(empleadoLista.get(position).getId_empleado()));
        holder.DniTXTEmpleado.setText(empleadoLista.get(position).getDni());
        holder.DescripcionTXTEmpleado.setText(String.valueOf(empleadoLista.get(position).getDescripcion()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(holder.getAdapterPosition(), item);
                    Intent intent = new Intent(holder.context, EmpleadoDetalle.class);
                    intent.putExtra("idEmpleado",holder.IdTXTEmpleado.getText());
                    holder.context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return empleadoLista.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Empleado model);
    }
}
