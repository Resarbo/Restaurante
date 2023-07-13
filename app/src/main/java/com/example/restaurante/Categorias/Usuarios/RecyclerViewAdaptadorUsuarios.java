package com.example.restaurante.Categorias.Usuarios;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.R;

import java.util.List;

public class RecyclerViewAdaptadorUsuarios extends RecyclerView.Adapter<RecyclerViewAdaptadorUsuarios.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView CorreoTXTAdmin, idTXTAdmin, tipoUsuarioTXTAdmin;

        public ViewHolder(View view){
            super(view);
            CorreoTXTAdmin = (TextView)view.findViewById(R.id.CorreoTXTAdmin);
            idTXTAdmin = (TextView)view.findViewById(R.id.idTXTAdmin);
            tipoUsuarioTXTAdmin = (TextView)view.findViewById(R.id.tipoUsuarioTXTAdmin);
        }
    }

    public List<Usuario> administradores;

    public RecyclerViewAdaptadorUsuarios(List<Usuario> administradores){
        this.administradores = administradores;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_usuario,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.CorreoTXTAdmin.setText(administradores.get(position).getCorreo());
        holder.idTXTAdmin.setText(String.valueOf(administradores.get(position).getId_usuario()));
        holder.tipoUsuarioTXTAdmin.setText(administradores.get(position).getNombre_usuario_tipo());
    }

    @Override
    public int getItemCount() {
        return administradores.size();
    }


}
