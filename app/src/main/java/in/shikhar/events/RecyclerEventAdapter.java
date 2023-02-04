package in.shikhar.events;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerEventAdapter  extends RecyclerView.Adapter<RecyclerEventAdapter.ViewHolder> {

    Context context;
    ArrayList<EventModel> em;
    RecyclerEventAdapter(Context context, ArrayList<EventModel> em)
    {   this.em=em;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View v= LayoutInflater.from(context).inflate(R.layout.event_row,parent,false);
       ViewHolder viewHolder=new ViewHolder(v);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     holder.ci.setImageResource(em.get(position).img);
     holder.cn.setText(em.get(position).club_name);
     holder.en.setText(em.get(position).event_name);
     holder.ed.setText(em.get(position).event_description);

     holder.bt.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             String event=em.get(holder.getAdapterPosition()).event_name;
             DBHelper db=new DBHelper(context);
             ParticipationHelper ph=new ParticipationHelper(context);
             LoginActivity la=new LoginActivity();
             Cursor c=db.getinfo();
             while(c.moveToNext())
             {

                 if(c.getString(0).equals(la.user))
                 {
                    if(!ph.alreadyparticipated(c.getString(1),event))
                    {
                        boolean f = ph.insert_participants(c.getString(1), c.getString(2), c.getString(0), c.getString(3), c.getString(4), event);
                        if (f) {
                            Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "not", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(context, "Already participated", Toast.LENGTH_SHORT).show();
                    }
                 }

             }

         }
     });
    }

    @Override
    public int getItemCount() {
        return em.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cn,en,ed;
        ImageView ci;
        Button bt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cn=itemView.findViewById(R.id.clubnameview);
            en=itemView.findViewById(R.id.eventnameview);
            ed=itemView.findViewById(R.id.eventdescriptionview);
            ci=itemView.findViewById(R.id.clubimageview);
            bt=itemView.findViewById(R.id.participate);
        }
    }
}
