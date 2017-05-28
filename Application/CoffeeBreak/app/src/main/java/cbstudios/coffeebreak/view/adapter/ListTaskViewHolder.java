package cbstudios.coffeebreak.view.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cbstudios.coffeebreak.R;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Template-method implementing class for handling {@link cbstudios.coffeebreak.model.tododatamodule.todolist.IListTask}
 *          graphical representation in {@link TaskAdapter}</br >
 *          Used by: {@link TaskAdapter}
 *          </p>
 *
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
