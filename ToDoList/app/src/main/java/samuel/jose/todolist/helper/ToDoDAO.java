package samuel.jose.todolist.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import samuel.jose.todolist.model.ToDo;

public class ToDoDAO {
    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public ToDoDAO(Context context) {
        DBHelper db = new DBHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    public boolean insertToDo(ToDo toDo) {
        ContentValues values = new ContentValues();
        values.put("name", toDo.getToDoName());
        try {
            write.insert(DBHelper.TABLE1_NAME, null, values);
            Log.i("INFO", "Task successfully saved.");
        } catch (Exception e) {
            Log.e("INFO", "Error saving task: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateToDo(ToDo toDo) {
        ContentValues values = new ContentValues();
        values.put("name", toDo.getToDoName());
        try {
            String[] args = { toDo.getId().toString() };
            write.update(DBHelper.TABLE1_NAME, values, "id=?", args);
            Log.i("INFO", "Task successfully updated.");
        } catch (Exception e) {
            Log.e("INFO", "Error updating task: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean removeToDo(ToDo toDo) {
        return false;
    }

    public List<ToDo> getAllToDos() {
        List<ToDo> toDoList = new ArrayList<>();
        Cursor cursor = read.query(DBHelper.TABLE1_NAME, new String[]{ "id", "name" }, null, null, null, null, null);
        while (cursor.moveToNext()) {
            ToDo toDo = new ToDo();
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
            String toDoName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            toDo.setId(id);
            toDo.setToDoName(toDoName);
            toDoList.add(toDo);
        }
        return toDoList;
    }
}
