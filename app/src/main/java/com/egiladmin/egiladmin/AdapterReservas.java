package com.egiladmin.egiladmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterReservas extends RecyclerView.Adapter<AdapterReservas.ReservasViewHolder> {

    private ArrayList<Reserva> reservas;
    String fecha, hora, turno, departamento, residente;

    public class ReservasViewHolder extends RecyclerView.ViewHolder {

        TextView tvFecha, tvHora, tvTurno, tvDepartamento, tvResidente;

        public ReservasViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvHora = itemView.findViewById(R.id.tvHora);
            tvTurno = itemView.findViewById(R.id.tvTurno);
            tvDepartamento = itemView.findViewById(R.id.tvDepartamento);
            tvResidente = itemView.findViewById(R.id.tvResidente);
        }

    }

    public AdapterReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    @NonNull
    @Override
    public ReservasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_reservas, parent, false);
        ReservasViewHolder reservasViewHolder = new ReservasViewHolder(view);
        return reservasViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReservasViewHolder holder, int position) {
        Reserva currentItem = reservas.get(position);
        fecha = "Fecha: " + currentItem.getFecha();
        hora = "Hora: " + currentItem.getHora();
        turno = "Turno: " + currentItem.getTurno();
        departamento = "Departamento: " + currentItem.getDepartamento() + " - " + currentItem.getTorre();
        residente = "Nombre: " + currentItem.getNombre() + " " + currentItem.getApellido();
        holder.tvFecha.setText(fecha);
        holder.tvHora.setText(hora);
        holder.tvTurno.setText(turno);
        holder.tvDepartamento.setText(departamento);
        holder.tvResidente.setText(residente);
    }

    @Override
    public int getItemCount() {
        return reservas.size();
    }

}