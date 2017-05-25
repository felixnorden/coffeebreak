package cbstudios.coffeebreak.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.controller.IMainPresenter;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

import static cbstudios.coffeebreak.R.layout.drawer_list_item_label;
import static cbstudios.coffeebreak.R.layout.drawer_list_item_time;

/**
 * Created by elias on 21/04/2017.
 *
 */

public class TimeCategoryAdapter extends ArrayAdapter<ITimeCategory> {

    private final Context context;
    private final List<ITimeCategory> timeCategories;
    //private IMainPresenter mainPresenter;

    public TimeCategoryAdapter(Context context, List<ITimeCategory> categories) {
        super(context, drawer_list_item_label, categories);
        this.context = context;
        this.timeCategories = categories;
        //this.mainPresenter = mainPresenter;
    }

    /**
     *{@inheritDoc}
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowItem = inflater.inflate(drawer_list_item_time, parent, false);

        final ITimeCategory timeCategory = getItem(position);
        final TextView etNameView = (TextView) rowItem.findViewById(R.id.nameView);
        TextView categorySize = (TextView) rowItem.findViewById(R.id.textViewNumber);


        if (timeCategory.getName() != null) {
            etNameView.setText(timeCategory.getName());

            //updateNumberOfTaskInCategory(timeCategory, categorySize);
        }
        return rowItem;

    }

    /**
     * * Updates the number that represent number of task in the given category
     *
     * @param timeCategory is the current labelCategory
     * @param categorySize is ID for a TextView field
     */
    /*private void updateNumberOfTaskInCategory(ITimeCategory timeCategory, TextView categorySize) {
        List<IAdvancedTask> tasks = mainPresenter.getTasks();

        for (int j = 0; j < timeCategories.size(); j++) {
            if (timeCategory.equals(timeCategories.get(j))) {
                if (timeCategory.getTaskCount(tasks) != 0) {
                    categorySize.setText(Integer.toString(timeCategory.getTaskCount(tasks)));
                    //Test function
                    //categorySize.setText((Integer.toString(mainPresenter.getModel().getToDoDataModule().getStats().getAchievementList().size())));
                }
            }
        }}*/

}

