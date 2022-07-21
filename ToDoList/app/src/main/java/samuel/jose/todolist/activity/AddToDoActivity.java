package samuel.jose.todolist.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import samuel.jose.todolist.R;
import samuel.jose.todolist.helper.ToDoDAO;
import samuel.jose.todolist.model.ToDo;

public class AddToDoActivity extends AppCompatActivity {
    private TextInputEditText newToDo;
    private ToDo currentToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        newToDo = findViewById(R.id.editTextNewTask);
        currentToDo = (ToDo) getIntent().getSerializableExtra("selectedToDo");
        if (currentToDo != null) {
            newToDo.setText(currentToDo.getToDoName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_to_do, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemSalvar) {
            ToDoDAO toDoDAO = new ToDoDAO(getApplicationContext());
            if (currentToDo != null) {
                String strNewToDo = newToDo.getText().toString();
                if (!strNewToDo.isEmpty()) {
                    ToDo toDo = new ToDo();
                    toDo.setId(currentToDo.getId());
                    toDo.setToDoName(strNewToDo);
                    if (toDoDAO.updateToDo(toDo)) {
                        Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Error updating task", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Insert the task", Toast.LENGTH_SHORT).show();
                }
            } else {
                String strNewToDo = newToDo.getText().toString();
                if (!strNewToDo.isEmpty()) {
                    ToDo toDo = new ToDo();
                    toDo.setToDoName(strNewToDo);
                    if (toDoDAO.insertToDo(toDo)) {
                        Toast.makeText(this, "Task created", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Error creating task", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Insert the task", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}