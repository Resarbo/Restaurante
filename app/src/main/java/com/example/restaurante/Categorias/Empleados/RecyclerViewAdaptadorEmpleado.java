package com.example.restaurante.Categorias.Empleados;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.R;

import java.util.List;

public class RecyclerViewAdaptadorEmpleado extends RecyclerView.Adapter<RecyclerViewAdaptadorEmpleado.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView NombreTXTEmpleado, ApellidoTXTEmpleado,DniTXTEmpleado,DescripcionTXTEmpleado;

        public ViewHolder(View view){
            super(view);
            NombreTXTEmpleado = (TextView) view.findViewById(R.id.NombreTXTEmpleado);
            ApellidoTXTEmpleado = (TextView)view.findViewById(R.id.ApellidoTXTEmpleado);
            DniTXTEmpleado = (TextView)view.findViewById(R.id.DniTXTEmpleado);
            DescripcionTXTEmpleado = (TextView)view.findViewById(R.id.DescripcionTXTEmpleado);
        }
    }

    public List<Empleado> empleadoLista;

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
        holder.NombreTXTEmpleado.setText(empleadoLista.get(position).getNombre());
        holder.ApellidoTXTEmpleado.setText(empleadoLista.get(position).getApellido());
        holder.DniTXTEmpleado.setText(empleadoLista.get(position).getDni());
        holder.DescripcionTXTEmpleado.setText(String.valueOf(empleadoLista.get(position).getDescripcion()));
    }

    @Override
    public int getItemCount() {
        return empleadoLista.size();
    }


}
