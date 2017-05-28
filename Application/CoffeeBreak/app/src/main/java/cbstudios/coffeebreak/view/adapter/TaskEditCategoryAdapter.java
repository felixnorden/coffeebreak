package cbstudios.coffeebreak.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.eventbus.TaskEditedEvent;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

/**
 * @author Zack
 * @version 1.0
 *          Responsibility: Handles how the listview in TaskEditActivity should display a tasks
 *          categories to the user.
 *          Uses: ILabelCategory, Eventbus.
 *          Used by: TaskEditActivity, TaskEditPresenter, ITaskEditView.
 */
public class TaskEditCategoryAdapter extends ArrayAdapter<ILabelCategory> {

    private Context context;
    private List<ILabelCategory> categories;

    /**
     * @param context    Context in which the adapter is used.
     * @param resource   Dunno
     * @param categories The list of categories to show.
     */
    public TaskEditCategoryAdapter(Context context, int resource, List<ILabelCategory> categories) {
        super(context, resource, categories);

        this.context = context;
        this.categories = categories;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ILabelCategory category = categories.get(position);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.task_edit_category_layout, null);

        ImageButton removeButton = (ImageButton) view.findViewById(R.id.task_edit_category_remove_button);
        TextView labelName = (TextView) view.findViewById(R.id.task_edit_category_name);
        ImageView indicator = (ImageView) view.findViewById(R.id.task_edit_categories_item_indicator);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categories.remove(category);
                EventBus.getDefault().post(new TaskEditedEvent());
            }
        });

        labelName.setText(category.getName());

        indicator.setColorFilter(Color.parseColor(category.getColor()), PorterDuff.Mode.MULTIPLY);

        notifyDataSetChanged();

        return view;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public ILabelCategory getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
