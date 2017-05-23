package cbstudios.coffeebreak.view.adapter;

import android.view.View;
import android.widget.TextView;

import cbstudios.coffeebreak.R;

/**
 * Created by Felix on 2017-05-20.
 */

class AdvancedTaskViewHolder extends TaskAdapter.TaskViewHolder {
    public TextView tvSubTask;

    public AdvancedTaskViewHolder(View itemView){
        super(itemView);

        tvSubTask = (TextView) itemView.findViewById(R.id.subTextView);
    }

    @Override
    void setSpecificFields() {
        String information;
        if(task.hasNote())
            information = task.getNote();
        else if(!task.getLabels().isEmpty())
            information = task.getLabels().get(0).getName();
        else
            information = "No info, add plez";
        tvSubTask.setText(information);
    }
}
