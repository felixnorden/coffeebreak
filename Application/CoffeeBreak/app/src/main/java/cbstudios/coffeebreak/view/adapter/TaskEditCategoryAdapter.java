package cbstudios.coffeebreak.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.eventbus.TaskEditedEvent;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

public class TaskEditCategoryAdapter extends ArrayAdapter<ILabelCategory> {

    private Context context;
    private List<ILabelCategory> categories;

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

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categories.remove(category);
                EventBus.getDefault().post(new TaskEditedEvent());
            }
        });

        labelName.setText(category.getName());

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
