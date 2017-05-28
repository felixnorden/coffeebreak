package cbstudios.coffeebreak.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;

import static cbstudios.coffeebreak.R.layout.drawer_list_item_label;
import static cbstudios.coffeebreak.R.layout.drawer_list_item_time;

/**
 * @author Elias, Felix
 * @version 2.0
 *          <p>Responsibility: Handle all {@link ITimeCategory} instances
 *          and allow for sorting of tasks into the specific category</br >
 *          Uses: {@link Context}, {@link ITimeCategory}</br>
 *          Used by: {@link MergeAdapter}
 *          </p>
 */

public class TimeCategoryAdapter extends ArrayAdapter<ITimeCategory> {

    private final Context context;

    /**
     * @param context    The context in which the adapter is used.
     * @param categories The list of categories the adapter should display.
     */
    public TimeCategoryAdapter(Context context, List<ITimeCategory> categories) {
        super(context, drawer_list_item_label, categories);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowItem = inflater.inflate(drawer_list_item_time, parent, false);

        final ITimeCategory timeCategory = getItem(position);
        final TextView etNameView = (TextView) rowItem.findViewById(R.id.nameView);
        //TextView categorySize = (TextView) rowItem.findViewById(R.id.textViewNumber);


        if (timeCategory.getName() != null) {
            etNameView.setText(timeCategory.getName());
        }
        return rowItem;

    }
}

