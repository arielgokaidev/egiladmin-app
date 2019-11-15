package com.egiladmin.egiladmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDepartamentos extends RecyclerView.Adapter<AdapterDepartamentos.DepartamentosViewHolder> {

    private ArrayList<Departamento> departamento;
    String numero, torre, estado, rut;
    //Departamento String numero, torre, estado, rut;
    public class DepartamentosViewHolder extends RecyclerView.ViewHolder {

        TextView tvNumero, tvTorre, tvEstado, tvRut;

        public DepartamentosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumero = itemView.findViewById(R.id.tvNumero);
            tvTorre = itemView.findViewById(R.id.tvTorre);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            tvRut = itemView.findViewById(R.id.tvRut);
        }

    }

    public AdapterDepartamentos(ArrayList<Departamento> departamento) {
        this.departamento = departamento;
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
        Departamento currentItem = departamento.get(position);
        numero = "Numero: " + currentItem.getNumero();
        torre = "Torre: " + currentItem.getTorre();
        estado = "Estado: " + currentItem.getEstado();
        rut = "Rut: " + currentItem.getRut();
        holder.tvNumero.setText(numero);
        holder.tvTorre.setText(torre);
        holder.tvEstado.setText(estado);
        holder.tvRut.setText(rut);
    }

    @Override
    public int getItemCount() {
        return departamento.size();
    }




}
