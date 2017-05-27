package cbstudios.coffeebreak.view.adapter;

import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cbstudios.coffeebreak.R;

/**
 * Created by Felix on 2017-05-20.
 */

class AdvancedTaskViewHolder extends TaskAdapter.TaskViewHolder {
    public TextView tvSubTask;

    public AdvancedTaskViewHolder(View itemView) {
        super(itemView);

        tvSubTask = (TextView) itemView.findViewById(R.id.subTextView);
    }

    @Override
    void setSpecificFields() {
        String information;
        if (task.getNotification() != null) {
            Date date = task.getNotification().getTime();
            String dateTime = new SimpleDateFormat("EE", Locale.getDefault()).format(date)
                    + " " + new SimpleDateFormat("dd", Locale.getDefault()).format(date)
                    + " " + new SimpleDateFormat("MMM", Locale.getDefault()).format(date)
                    + " " + new SimpleDateFormat("HH:mm", Locale.getDefault()).format(date);
            tvSubTask.setText(dateTime);
        } else if (task.hasNote()) {
            information = task.getNote();
            tvSubTask.setText(information);
        } else if (!task.getLabels().isEmpty()) {
            information = task.getLabels().get(0).getName();
            tvSubTask.setText(information);
        } else {
            information = "No info, add plez";
            tvSubTask.setText(information);
        }
    }
}
