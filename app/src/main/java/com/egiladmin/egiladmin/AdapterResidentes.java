package com.egiladmin.egiladmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterResidentes extends RecyclerView.Adapter<AdapterResidentes.ResidentesViewHolder> {

    private ArrayList<Residente> residente;
    String rut, nombre, usuario, tipo;

    public class ResidentesViewHolder extends RecyclerView.ViewHolder {

        TextView tvRut, tvNombre, tvUsuario, tvTipo;

        public ResidentesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRut = itemView.findViewById(R.id.tvRut);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvUsuario = itemView.findViewById(R.id.tvUsuario);
            tvTipo = itemView.findViewById(R.id.tvTipo);
        }

    }

    public AdapterResidentes(ArrayList<Residente> residente) {
        this.residente = residente;
    }

    @NonNull
    @Override
    public ResidentesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_residentes, parent, false);
        ResidentesViewHolder residentesViewHolder = new ResidentesViewHolder(view);
        return residentesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResidentesViewHolder holder, int position) {
        Residente currentItem = residente.get(position);
        rut = "Rut: " + currentItem.getRut();
        nombre = "Nombre: " + currentItem.getNombre() + " " + currentItem.getApellido();
        usuario = "Usuario: " + currentItem.getUsuario();
        tipo = "Tipo: " + currentItem.getTipo();
        holder.tvRut.setText(rut);
        holder.tvNombre.setText(nombre);
        holder.tvUsuario.setText(usuario);
        holder.tvTipo.setText(tipo);
    }

    @Override
    public int getItemCount() {
        return residente.size();
    }

}
