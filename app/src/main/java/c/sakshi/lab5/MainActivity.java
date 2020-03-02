package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public void clickFunction(View view) {

        EditText myTextField = (EditText) findViewById(R.id.editText);
        String str = myTextField.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username",str).apply();
        String usernameKey = "username";
        Log.d(TAG,"username    : "+sharedPreferences.getString(usernameKey,""));
        goToActivity2(str);
    }
    public void goToActivity2 (String s) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("message",s);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5",Context.MODE_PRIVATE);
        if(!sharedPreferences.getString(usernameKey,"").equals("")){
            goToActivity2(sharedPreferences.getString(usernameKey,""));
        } else {
            setContentView(R.layout.activity_main);
        }
    }
}
