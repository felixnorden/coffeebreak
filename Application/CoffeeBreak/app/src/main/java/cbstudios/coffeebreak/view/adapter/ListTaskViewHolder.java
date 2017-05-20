package cbstudios.coffeebreak.view.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cbstudios.coffeebreak.R;

/**
 * Created by Felix on 2017-05-20.
 */

class ListTaskViewHolder extends TaskAdapter.TaskViewHolder {
    public TextView tvSubTask;
    public ImageView ivSubTask;

    public ListTaskViewHolder(View itemView){
        super(itemView);

        tvSubTask = (TextView) itemView.findViewById(R.id.subTextView);
        ivSubTask = (ImageView) itemView.findViewById(R.id.imageViewSubTask);

    }

    @Override
    void setSpecificFields() {

    }
}
