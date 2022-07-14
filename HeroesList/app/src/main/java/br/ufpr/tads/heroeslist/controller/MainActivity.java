package br.ufpr.tads.heroeslist.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.heroeslist.R;
import br.ufpr.tads.heroeslist.RecyclerItemClickListener;
import br.ufpr.tads.heroeslist.adapter.AdapterHeroes;
import br.ufpr.tads.heroeslist.model.HeroModel;

public class MainActivity extends AppCompatActivity {
   private RecyclerView recyclerViewHeroes;
   private List<HeroModel> listHeroes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewHeroes = findViewById(R.id.recyclerViewHeroes);

        this.createHero();
        AdapterHeroes adapter = new AdapterHeroes(listHeroes);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewHeroes.setLayoutManager(layoutManager);
        recyclerViewHeroes.setHasFixedSize(true);
        recyclerViewHeroes.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewHeroes.setAdapter(adapter);
        recyclerViewHeroes.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerViewHeroes,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                HeroModel obj = listHeroes.get(position);
                                Toast.makeText(getApplicationContext(), "Selected " + obj.getName(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                HeroModel obj = listHeroes.get(position);
                                Toast.makeText(getApplicationContext(), "Long selected " + obj.getName(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }

    public void createHero() {
        HeroModel obj = new HeroModel(R.drawable.flash, "Flash", "DC");
        listHeroes.add(obj);
        obj = new HeroModel(R.drawable.ironman, "Ironman", "Marvel");
        listHeroes.add(obj);
        obj = new HeroModel(R.drawable.flash, "Flash", "DC");
        listHeroes.add(obj);
        obj = new HeroModel(R.drawable.ironman, "Ironman", "Marvel");
        listHeroes.add(obj);
    }
}