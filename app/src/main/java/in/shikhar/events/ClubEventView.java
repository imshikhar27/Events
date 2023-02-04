package in.shikhar.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;


public class ClubEventView extends AppCompatActivity {
    ArrayList<EventModel> clubar=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_event_view);
        RecyclerView recycle=findViewById(R.id.recycleclubeventview);
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
                    clubar.add(new EventModel(R.drawable.shrink, c.getString(1), c.getString(2), c.getString(3)));
                } else {
                    clubar.add(new EventModel(R.drawable.rac, c.getString(1), c.getString(2), c.getString(3)));

                }
            }

            RecycleClubEventAdapter rea1 = new RecycleClubEventAdapter(this, clubar);
            recycle.setAdapter(rea1);
        }
    }

}