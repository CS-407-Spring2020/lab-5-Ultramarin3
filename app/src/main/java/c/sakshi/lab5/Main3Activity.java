package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Main3Activity extends AppCompatActivity {

    int noteid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditText editText;
        editText = (EditText) findViewById(R.id.editText3);
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid",noteid);
        if (noteid != -1) {
            Note note = Main2Activity.notes.get(noteid);
            String noteContent = note.getContent();
            editText.setText(noteContent);
        }
    }
    public void saveMethod(View view) {
        EditText editText;
        editText = (EditText) findViewById(R.id.editText3);
        String content = editText.getText().toString();
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes",Context.MODE_PRIVATE,null);
        DBHelper a = new DBHelper(sqLiteDatabase);
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1){
            title = "NOTE_" + (Main2Activity.notes.size() +1);
            a.saveNotes(username, title, content,date);
        } else {
            title = "NOTE_" + (noteid +1);
            a.updateNote(title, date, content, username);
        }
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("message",username);
        startActivity(intent);


    }
}
