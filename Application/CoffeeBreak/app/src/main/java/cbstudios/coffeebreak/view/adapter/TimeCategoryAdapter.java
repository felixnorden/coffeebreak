package cbstudios.coffeebreak.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

import static cbstudios.coffeebreak.R.layout.drawer_list_item;

/**
 * Created by elias on 21/04/2017.
 */

public class TimeCategoryAdapter extends ArrayAdapter<ITimeCategory> {

    private final Context context;
    private final List<ITimeCategory> timeCategories;
    private Model model;

    public TimeCategoryAdapter(Context context, List<ITimeCategory> categories, Model model){
        super(context, drawer_list_item, categories);
        this.context = context;
        this.timeCategories = categories;
        this.model = model;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowItem = inflater.inflate(drawer_list_item, parent, false);

        final ITimeCategory timeCategory = getItem(position);
        final TextView etNameView = (TextView) rowItem.findViewById(R.id.nameView);
        TextView categorySize = (TextView) rowItem.findViewById(R.id.textViewNumber);


        if(timeCategory.getName() != null) {
            etNameView.setText(timeCategory.getName());

            List<IAdvancedTask> tasks = model.getToDoDataModule().getTasks();

            for (int j = 0; j < timeCategories.size(); j++) {
                if (timeCategory.equals(timeCategories.get(j))) {
                    if (timeCategory.getTaskCount(tasks) != 0) {
                        categorySize.setText(Integer.toString(timeCategory.getTaskCount(tasks)));
                    }
                }
            }
        }
        return rowItem;

    }
}

