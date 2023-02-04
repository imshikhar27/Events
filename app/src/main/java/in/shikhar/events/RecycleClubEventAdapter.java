package in.shikhar.events;

import android.content.Context;
import android.content.Intent;
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

public class RecycleClubEventAdapter extends RecyclerView.Adapter<RecycleClubEventAdapter.ViewHolder> {

    Context context;
    ArrayList<EventModel> em1;
    RecycleClubEventAdapter(Context context, ArrayList<EventModel> em1)
    {   this.em1=em1;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.clubview_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ci.setImageResource(em1.get(position).img);
        holder.cn.setText(em1.get(position).club_name);
        holder.en.setText(em1.get(position).event_name);
        holder.ed.setText(em1.get(position).event_description);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventHelper eh=new EventHelper(context);
                ParticipationHelper ph=new ParticipationHelper(context);
                boolean x= eh.deleteEventByName(em1.get(holder.getAdapterPosition()).event_name);
                boolean y= ph.deleteparticipantsByEventName(em1.get(holder.getAdapterPosition()).event_name);
                if(x==true&&y==true)
                {
                    em1.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                }
            }
        });
        holder.vp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String event=em1.get(holder.getBindingAdapterPosition()).event_name;
                Intent i= new Intent(context,ViewParticipants.class);
                i.putExtra("event",event);
                context.startActivity(i);


            }
        });
    }

    @Override
    public int getItemCount() {
        return em1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cn,en,ed;
        ImageView ci;

        Button delete,vp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cn=itemView.findViewById(R.id.clubnameview1);
            en=itemView.findViewById(R.id.eventnameview1);
            ed=itemView.findViewById(R.id.eventdescriptionview1);
            ci=itemView.findViewById(R.id.clubimageview1);
            delete=itemView.findViewById(R.id.delete1);
            vp=itemView.findViewById(R.id.view1);

        }

    }
}
