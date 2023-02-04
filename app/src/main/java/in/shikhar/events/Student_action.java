package in.shikhar.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Student_action extends AppCompatActivity {
    ArrayList<EventModel> ar=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_action);

        RecyclerView recycle=findViewById(R.id.recycleevent);
        recycle.setLayoutManager(new LinearLayoutManager(this));

       EventHelper eh= new EventHelper(this);
       Cursor c=eh.getinfo();
       if(c.getCount()==0)
        {
            Toast.makeText(this, "No active events", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (c.moveToNext()) {
                if (c.getString(1).equals("Shrinik Club")) {
                    ar.add(new EventModel(R.drawable.shrink, c.getString(1), c.getString(2), c.getString(3)));
                } else {
                    ar.add(new EventModel(R.drawable.rac, c.getString(1), c.getString(2), c.getString(3)));

                }
            }

            RecyclerEventAdapter rea = new RecyclerEventAdapter(this, ar);
            recycle.setAdapter(rea);
        }
    }

    }

