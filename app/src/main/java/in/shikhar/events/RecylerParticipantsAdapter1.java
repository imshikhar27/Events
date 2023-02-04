package in.shikhar.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecylerParticipantsAdapter1 extends RecyclerView.Adapter<RecylerParticipantsAdapter1.ViewHolder> {
    Context context;
    ArrayList<ParticipantModel> pm;
    RecylerParticipantsAdapter1(Context context, ArrayList<ParticipantModel> pm)
    {
        this.context=context;
        this.pm=pm;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewparticipantsrow,parent,false);
       ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name1.setText(pm.get(position).name);
        holder.year1.setText(pm.get(position).year);
        holder.roll1.setText(pm.get(position).roll);
        holder.email1.setText(pm.get(position).email);
        holder.phone1.setText(pm.get(position).phone);


    }

    @Override
    public int getItemCount() {
        return pm.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name1, year1, roll1,phone1,email1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name1 = itemView.findViewById(R.id.studentname);
            year1= itemView.findViewById(R.id.yearview);
            phone1 = itemView.findViewById(R.id.phoneview);
            email1 = itemView.findViewById(R.id.emailview);
            roll1 = itemView.findViewById(R.id.rollview);
        }
    }
}
