package com.example.restaurante.Categorias.Usuarios;

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

public class RecyclerViewAdaptadorUsuarios extends RecyclerView.Adapter<RecyclerViewAdaptadorUsuarios.ViewHolder> {
    public List<Usuario> usuarios;
    private OnClickListener onClickListener;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        Context context;
        private TextView CorreoTXTAdmin, idTXTAdmin, tipoUsuarioTXTAdmin;

        public ViewHolder(View view){
            super(view);
            context = view.getContext();
            CorreoTXTAdmin = (TextView)view.findViewById(R.id.CorreoTXTAdmin);
            idTXTAdmin = (TextView)view.findViewById(R.id.idTXTAdmin);
            tipoUsuarioTXTAdmin = (TextView)view.findViewById(R.id.tipoUsuarioTXTAdmin);
        }
    }

    public RecyclerViewAdaptadorUsuarios(List<Usuario> usuarios){
        this.usuarios = usuarios;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_usuario,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Usuario item = usuarios.get(position);
        holder.CorreoTXTAdmin.setText(usuarios.get(position).getCorreo());
        holder.idTXTAdmin.setText(String.valueOf(usuarios.get(position).getId_usuario()));
        holder.tipoUsuarioTXTAdmin.setText(usuarios.get(position).getNombre_usuario_tipo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener != null){
                    onClickListener.onClick(holder.getAdapterPosition(), item);
                    Intent intent = new Intent(holder.context, UsuarioDetalle.class);
                    intent.putExtra("idUsuario",holder.idTXTAdmin.getText());
                    holder.context.startActivity(intent);
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Usuario model);
    }
}
