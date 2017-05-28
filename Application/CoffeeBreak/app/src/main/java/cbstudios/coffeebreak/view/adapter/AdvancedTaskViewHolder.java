package cbstudios.coffeebreak.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cbstudios.coffeebreak.R;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Template-method implementing class for handling {@link cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask}
 *          graphical representation in {@link TaskAdapter}</br >
 *          Used by: {@link TaskAdapter}
 *          </p>
 */

class AdvancedTaskViewHolder extends TaskAdapter.TaskViewHolder {
    public TextView tvSubTask;

    /**
     * @param itemView The view in which the task is to be shown in.
     * @param context The context of the managing activity
     */

    public AdvancedTaskViewHolder(View itemView, Context context) {
        super(itemView, context);

        tvSubTask = (TextView) itemView.findViewById(R.id.subTextView);
    }

    @Override
    void setSpecificFields() {
        String information;
        if (task.getNotification() != null) {
            Date date = task.getNotification().getTime();
           information = new SimpleDateFormat("EE", Locale.getDefault()).format(date)
                    + " " + new SimpleDateFormat("dd", Locale.getDefault()).format(date)
                    + " " + new SimpleDateFormat("MMM", Locale.getDefault()).format(date)
                    + " " + new SimpleDateFormat("HH:mm", Locale.getDefault()).format(date);
        } else if (task.hasNote()) {
            information = task.getNote();
        } else if (!task.getLabels().isEmpty()) {
            information = task.getLabels().get(0).getName();
        } else {
            return;
        }
        tvSubTask.setText(information);

    }
}
