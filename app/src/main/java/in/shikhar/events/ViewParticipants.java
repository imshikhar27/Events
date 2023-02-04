package in.shikhar.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewParticipants extends AppCompatActivity {
    ArrayList<ParticipantModel> pm1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_participants);
        pm1=new ArrayList<>();
        Intent i=getIntent();
        String event=i.getStringExtra("event");

        RecyclerView recycle1=findViewById(R.id.recycleit);

        ParticipationHelper ph= new ParticipationHelper(this);
        Cursor c=ph.getinfo();
        int x=0;
            while (c.moveToNext()) {

                if (c.getString(6).equals(event)) {
                             x=x+5;
                           pm1.add(new ParticipantModel(c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5)));
                }

            }
        if (x == 0) {
            Toast.makeText(this, "No participants", Toast.LENGTH_SHORT).show();
        }

            RecylerParticipantsAdapter1 re = new RecylerParticipantsAdapter1(this,pm1);
            recycle1.setAdapter(re);

        }
    }

