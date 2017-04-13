package cbstudios.coffeebreak.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

import static android.support.constraint.R.id.none;

/**
 * Created by Felix on 2017-04-13.
 */

public class TaskAdapter extends ArrayAdapter<IAdvancedTask> {
    private final Context context;
    private final List<IAdvancedTask> tasks;

    public TaskAdapter(Context context, List<IAdvancedTask> tasks){
        super(context, R.layout.advanced_task_layout, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowItem = inflater.inflate(R.layout.advanced_task_layout, parent, false);

        IAdvancedTask task = getItem(position);

        // Fetch references to layout items
        View vPriority = (View) rowItem.findViewById(R.id.viewPriority);
        CheckBox cbCheckBox = (CheckBox) rowItem.findViewById(R.id.checkBox);
        EditText etTaskName = (EditText) rowItem.findViewById(R.id.editTextField);
        ImageButton ibMore = (ImageButton) rowItem.findViewById(R.id.imageButtonMore);
        ImageView ivCategory = (ImageView) rowItem.findViewById(R.id.imageViewCategory);

        // Set up elements for layout
        //TODO Check Colors of Priority and Category

        if(task.getName() != null){
            etTaskName.setText(task.getName());
            etTaskName.setInputType(none);
            etTaskName.setFocusable(false);
            vPriority.setBackgroundColor(task.getPriority().getColor());
        }
        else{
            // Task creation, hide all unset elements except for text field
            cbCheckBox.setVisibility(View.INVISIBLE);
            ivCategory.setVisibility(View.INVISIBLE);
            ibMore.setVisibility(View.INVISIBLE);
            vPriority.setVisibility(View.INVISIBLE);

            // Request input from user
            etTaskName.requestFocus();
        }

        if(task.getLabels().size() == 1){
            ivCategory.setColorFilter(task.getLabels().get(0).getColor());
        }

        return rowItem;
    }
}
