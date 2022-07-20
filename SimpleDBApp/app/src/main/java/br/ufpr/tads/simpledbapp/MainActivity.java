package br.ufpr.tads.simpledbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private StudentOperations studentOperations;
    ListView studentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try {
//            SQLiteDatabase db = openOrCreateDatabase("contacts.db", MODE_PRIVATE, null);
//
//            db.execSQL("CREATE TABLE IF NOT EXISTS contacts (name VARCHAR, age INT(3))");
//            db.execSQL("INSERT INTO contacts VALUES ('Marco', 40)");
//            db.execSQL("INSERT INTO contacts VALUES ('Veronica', 25)");
//            db.execSQL("UPDATE contacts SET age = 19 WHERE name = 'Veronica'");
//            db.execSQL("DELETE FROM contacts WHERE name = 'Marco'");
//
//            Cursor cursor = db.rawQuery("SELECT name, age FROM contacts", null);
//
//            int nameIndex = cursor.getColumnIndex("name");
//            int ageIndex = cursor.getColumnIndex("age");
//
//            cursor.moveToFirst();
//            while (cursor != null) {
//                Log.i("Resultado - name: ", cursor.getString(nameIndex));
//                Log.i("Resultado - age: ", cursor.getString(ageIndex));
//                cursor.moveToNext();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        studentOperations = new StudentOperations(this);
        studentOperations.open();

        studentsList = findViewById(R.id.studentList);
        List values = studentOperations.getAllStudents();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        studentsList.setAdapter(arrayAdapter);
    }

    public void addStudent(View view) {
        ArrayAdapter arrayAdapter = (ArrayAdapter) studentsList.getAdapter();
        EditText editTextStudentName = findViewById(R.id.editTextTextStudentName);
        Student student = studentOperations.addStudent(editTextStudentName.getText().toString());
        arrayAdapter.add(student);
        editTextStudentName.setText("");
    }

    public void removeFirstStudent(View view) {
        ArrayAdapter arrayAdapter = (ArrayAdapter) studentsList.getAdapter();

        if (studentsList.getAdapter().getCount() > 0) {
            Student student = (Student) studentsList.getAdapter().getItem(0);
            studentOperations.deleteStudent(student);
            arrayAdapter.remove(student);
        } else {
            Toast.makeText(this, "Lista vazia!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentOperations.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        studentOperations.close();
    }
}