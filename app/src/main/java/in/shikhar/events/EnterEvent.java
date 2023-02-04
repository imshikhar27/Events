package in.shikhar.events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EnterEvent extends AppCompatActivity {

    RadioGroup rg1;
    RadioButton rb;
    String club_name="";
    EditText event1;
    EditText decription1;
    Button enter1;
    public void getclubname(View view)
    {
        rg1=findViewById(R.id.clubname);
        int clubnameid=rg1.getCheckedRadioButtonId();
        rb=findViewById(clubnameid);
        club_name=rb.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_event);
        event1=findViewById(R.id.eventname);
        rg1=findViewById(R.id.clubname);
        decription1=findViewById(R.id.description);
        enter1=findViewById(R.id.enterevent);
        EventHelper eh=new EventHelper(this);
        enter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String event_name =event1.getText().toString();
                String event_description =decription1.getText().toString();

                if(club_name.equals("")||event_name.equals("")||event_description.equals(""))
                    Toast.makeText(EnterEvent.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else
                {
                    if(!eh.eventexist(event_name)) {
                        Boolean insert = eh.insertevent(club_name, event_name, event_description);
                        if (insert == true) {
                            Toast.makeText(EnterEvent.this, "Event entered successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EnterEvent.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(EnterEvent.this, "Event already posted", Toast.LENGTH_SHORT).show();

                    }
                        rg1.clearCheck();
                        event1.setText("");
                        decription1.setText("");


                }
            }

        });
    }
}