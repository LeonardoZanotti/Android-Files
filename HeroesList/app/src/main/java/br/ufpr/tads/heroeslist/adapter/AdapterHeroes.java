package br.ufpr.tads.heroeslist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ufpr.tads.heroeslist.R;
import br.ufpr.tads.heroeslist.model.HeroModel;

public class AdapterHeroes extends RecyclerView.Adapter<AdapterHeroes.MyViewHolder> {

    private List<HeroModel> listHeroes;
    public AdapterHeroes(List<HeroModel> list) {
        this.listHeroes = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, owner;
        ImageView img;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.textViewName);
            owner = view.findViewById(R.id.textViewOwner);
            img = view.findViewById(R.id.imageViewHero);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_heroes, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HeroModel obj = listHeroes.get(position);
        holder.name.setText(obj.getName());
        holder.owner.setText(obj.getOwner());
        holder.img.setImageResource(obj.getImg());
    }

    @Override
    public int getItemCount() {
        return listHeroes.size();
    }
}
