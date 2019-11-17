package com.egiladmin.egiladmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDepartamentos extends RecyclerView.Adapter<AdapterDepartamentos.DepartamentosViewHolder> {

    private ArrayList<Departamento> departamentos;
    String numero, torre, estado, rut, nombre, tipo;

    public class DepartamentosViewHolder extends RecyclerView.ViewHolder {

        TextView tvNumero, tvTorre, tvEstado, tvRut, tvNombre, tvTipo;

        public DepartamentosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumero = itemView.findViewById(R.id.tvNumero);
            tvTorre = itemView.findViewById(R.id.tvTorre);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            tvRut = itemView.findViewById(R.id.tvRut);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTipo = itemView.findViewById(R.id.tvTipo);
        }

    }

    public AdapterDepartamentos(ArrayList<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    @NonNull
    @Override
    public DepartamentosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_departamentos, parent, false);
        DepartamentosViewHolder departamentosViewHolder = new DepartamentosViewHolder(view);
        return departamentosViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DepartamentosViewHolder holder, int position) {
        Departamento currentItem = departamentos.get(position);
        numero = "Numero: " + currentItem.getNumero();
        torre = "Torre: " + currentItem.getTorre();
        estado = "Estado: " + currentItem.getEstado();
        rut = "Rut: " + currentItem.getRut();
        nombre = "Nombre: " + currentItem.getNombre() + " " + currentItem.getApellido();
        tipo = "Tipo residente: " + currentItem.getTipo();
        holder.tvNumero.setText(numero);
        holder.tvTorre.setText(torre);
        holder.tvEstado.setText(estado);
        holder.tvRut.setText(rut);
        holder.tvNombre.setText(nombre);
        holder.tvTipo.setText(tipo);
    }

    @Override
    public int getItemCount() {
        return departamentos.size();
    }

}
