package viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_practice.R;

public class ViewHolder extends RecyclerView.ViewHolder{

    public ImageView taskImageView;
    public TextView taskTitleView;
    public TextView taskDescriptionView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        taskImageView= itemView.findViewById(R.id.taskImageView);
        taskTitleView= itemView.findViewById(R.id.taskTitleTextView);
        taskDescriptionView= itemView.findViewById(R.id.taskDescriptionTextView);


    }


}
