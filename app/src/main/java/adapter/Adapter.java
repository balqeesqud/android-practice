package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_practice.R;

import java.util.List;

import model.Task;
import viewHolder.ViewHolder;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    List<Task> tasks;
    Context context;

    public Adapter(List<Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.taskImageView.setTex(tasks.get(position).getImage());
        holder.taskTitleView.setText(tasks.get(position).getTitle());
        holder.taskDescriptionView.setText(tasks.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
