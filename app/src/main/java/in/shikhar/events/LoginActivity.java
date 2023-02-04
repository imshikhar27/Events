package in.shikhar.events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText roll1;
    EditText pass1;
    Button login1;
    EditText ev;
    public static String user;
    public void newac(View v)
    {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
    public void club_entry(View v) {
        ev = findViewById(R.id.passkey);
        String f = ev.getText().toString();
        if (f.equals("Glbajaj@club")) {
            Intent i = new Intent(this, Club_select_action.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
        }
        ev.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        roll1 = (EditText) findViewById(R.id.username);
        pass1 = (EditText) findViewById(R.id.password);
        login1 = (Button) findViewById(R.id.loginbtn);
        DBHelper db = new DBHelper(this);
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 user = roll1.getText().toString();
                String pass = pass1.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = db.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), Student_action.class);
                        startActivity(intent);
                        roll1.setText("");
                        pass1.setText("");
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        pass1.setText("");
                    }
                }
            }
        });


    }
    }
