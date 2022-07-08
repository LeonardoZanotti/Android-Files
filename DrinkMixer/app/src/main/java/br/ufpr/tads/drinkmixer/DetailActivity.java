package br.ufpr.tads.drinkmixer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent it = getIntent();

        String[] drinks = new String[] {"Batida de Sonho de Valsa", "Drink dos Deuses", "Quentão"};
        String[] ingredients = new String[] {
                "3 bombons sonho de valsa\n1 lata de leite condensado\n1/2 garrafa de pinga\n1 latinha de guaraná",
                "1 vidro (100 ml) de leite de coco\n1 copo (100 ml) de suco de maracujá\n1 copo (100 ml) de groselha\nCerca de 1 lata de leite condensado\n1 copo (100 ml) de cachaça\nGelo picado",
                "1 garrafa de cachaça (600 ml)\n 600 ml de água\n1/2 kg de açúcar\nCasca de 2 laranjas\nCasca de 1 limão\n50 g de gengibre em pedacinhos\nCravo-da-índia a gosto\nCanela de pau a gosto\n1 maçã cortada em pedacinhos"
        };
        String[] preparation = new String[] {
                "1. Bater tudo no liquidificador, ficando os chocolates crocantes por cima\n2. Servir gelado",
                "1. Bater os 5 primeiros ingredientes no liquidificador e acrescentar gelo na hora de servir",
                "1. Colocar em uma panela grande o açúcar, as cascas de laranja, as cascas de limão, o gengibre, o cravo e a canela\n2. Quando o açúcar estiver derretendo, colocar a cachaça e a água, deixando cozinhar por 20 a 25 minutos em fogo médio\n3. Filtre e adicione a maçã picadinha\n4. Manter aquecido após o preparo (fogo baixo)"
        };

        TextView drinkNameView = findViewById(R.id.drinkName);
        TextView ingredientsView = findViewById(R.id.ingredients);
        TextView preparationView = findViewById(R.id.preparation);

        int drinkId = it != null ? it.getIntExtra("drinkId", 3) : 3;

        if (drinkId == 3) {
            drinkNameView.setText("");
            ingredientsView.setText("");
            preparationView.setText("");
            Toast.makeText(this, "Drink não encontrado!", Toast.LENGTH_SHORT).show();
        } else {
            drinkNameView.setText(drinks[drinkId]);
            ingredientsView.setText(ingredients[drinkId]);
            preparationView.setText(preparation[drinkId]);
        }
    }
}