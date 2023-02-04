package in.shikhar.events;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Club_select_action extends AppCompatActivity {

    public void addevent(View v)
    {

            Intent i = new Intent(this, EnterEvent.class);
            startActivity(i);

    }
    public void viewevent(View v)
    {
        Intent i=new Intent(this,ClubEventView.class);
        startActivity(i);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_select_action);



    }
}