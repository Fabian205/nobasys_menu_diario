package com.example.menu_diario.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu_diario.EditarActivity;
import com.example.menu_diario.R;
import com.example.menu_diario.VerActivity;
import com.example.menu_diario.VerMenuesActivity;
import com.example.menu_diario.entidades.Menues;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaMenuAdapter extends RecyclerView.Adapter<ListaMenuAdapter.MenuViewHolder> {

    ArrayList<Menues> listaMenues;
    ArrayList<Menues> listaOriginal;

    public ListaMenuAdapter(ArrayList<Menues> listaMenues) {
        this.listaMenues = listaMenues;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaMenues);
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_menu, null, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.viewTipoMenu.setText(listaMenues.get(position).getTipo_menu());
        //holder.viewCodTipoMenu.setText(listaMenues.get(position).getCod_tipo_menu());
        holder.viewDetalle.setText(listaMenues.get(position).getDetalle());
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaMenues.clear();
            listaMenues.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Menues> collecion = listaMenues.stream()
                        .filter(i -> i.getTipo_menu().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaMenues.clear();
                listaMenues.addAll(collecion);
            } else {
                for (Menues c : listaOriginal) {
                    if (c.getTipo_menu().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaMenues.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public void filtradoDetalle(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaMenues.clear();
            listaMenues.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Menues> collecion = listaMenues.stream()
                        .filter(i -> i.getDetalle().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaMenues.clear();
                listaMenues.addAll(collecion);
            } else {
                for (Menues c : listaOriginal) {
                    if (c.getTipo_menu().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaMenues.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaMenues.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView viewTipoMenu, viewDetalle, viewPreparacion, viewCompras;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            viewTipoMenu = itemView.findViewById(R.id.viewTipoMenu);
            //viewCodTipoMenu = itemView.findViewById(R.id.viewCodTipoMenu);
            viewDetalle = itemView.findViewById(R.id.viewDetalle);
            viewPreparacion= itemView.findViewById(R.id.viewPreparacion);
            viewCompras= itemView.findViewById(R.id.viewCompras);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerMenuesActivity.class);
                    intent.putExtra("ID", listaMenues.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
