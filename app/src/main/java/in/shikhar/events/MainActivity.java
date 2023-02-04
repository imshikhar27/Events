package in.shikhar.events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name1;
    RadioGroup rg;
    RadioButton year1;
    EditText email1;
    EditText phone1;
    EditText roll1;
    EditText pass1;
    Button create1;
    TextView tt;
    String year;
     int yearid;
    public void getyear(View v)
    {
        rg=findViewById(R.id.rad);
       yearid= rg.getCheckedRadioButtonId();
       year1=findViewById(yearid);
       year=year1.getText().toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name1=findViewById(R.id.name);
        rg=findViewById(R.id.rad);
        email1=findViewById(R.id.email);
        phone1=findViewById(R.id.phone);
        roll1=findViewById(R.id.roll);
        pass1=findViewById(R.id.pass);
        create1=findViewById(R.id.create);
        create1.findViewById(R.id.create);
        DBHelper db=new DBHelper(this);
        create1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String name=name1.getText().toString();
                String email=email1.getText().toString();
                String phone=phone1.getText().toString();
                String roll=roll1.getText().toString();
                String password=pass1.getText().toString();
                if(name.equals("")||year.equals("")||email.equals("")||roll.equals("")||phone.equals("")||password.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else
                {

                        Boolean checkuser = db.checkroll(roll);
                        if(checkuser==false)
                        {
                            Boolean insert = db.insertData(roll,name,year,email,phone, password);
                            if(insert==true)
                            {
                                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                                name1.setText("");
                                email1.setText("");
                                phone1.setText("");
                                pass1.setText("");
                                roll1.setText("");
                                rg.clearCheck();
                            }else
                            {
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            name1.setText("");
                            email1.setText("");
                            phone1.setText("");
                            pass1.setText("");
                            roll1.setText("");
                            rg.clearCheck();
                            Toast.makeText(MainActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });
    }
}
