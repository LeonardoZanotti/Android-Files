package samuel.jose.todolist.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import samuel.jose.todolist.R;
import samuel.jose.todolist.helper.RecyclerItemClickListener;
import samuel.jose.todolist.adapter.ToDoAdapter;
import samuel.jose.todolist.helper.ToDoDAO;
import samuel.jose.todolist.model.ToDo;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ToDoAdapter toDoAdapter;
    private List<ToDo> toDoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewToDoList);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToDo selectedToDo = toDoList.get(position);
                Intent intent = new Intent(MainActivity.this, AddToDoActivity.class);
                intent.putExtra("selectedToDo", selectedToDo);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                ToDo selectedToDo = toDoList.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Remove task");
                dialog.setMessage("The task " + selectedToDo.getToDoName() + " will be removed. Are you sure?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToDoDAO toDoDAO = new ToDoDAO(getApplicationContext());
                        if (toDoDAO.removeToDo(selectedToDo)) {
                            instantiateRecyclerToDo();
                            Toast.makeText(MainActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error deleting task", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.setNegativeButton("No", null);
                dialog.create();
                dialog.show();
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));
    }

    @Override
    protected void onStart() {
        super.onStart();
        instantiateRecyclerToDo();
    }

    private void instantiateRecyclerToDo() {
        ToDoDAO toDoDAO = new ToDoDAO(getApplicationContext());
        toDoList = toDoDAO.getAllToDos();

        toDoAdapter = new ToDoAdapter(toDoList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(toDoAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            Intent intent = new Intent(getApplicationContext(), AddToDoActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}