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

import static cbstudios.coffeebreak.R.layout.drawer_list_item;

/**
 * Created by elias on 21/04/2017.
 */

public class TimeCategoryAdapter extends ArrayAdapter<ITimeCategory> {

    private final Context context;
    private final List<ITimeCategory> timeCategories;

    public TimeCategoryAdapter(Context context, List<ITimeCategory> categories){
        super(context, drawer_list_item, categories);
        this.context = context;
        this.timeCategories = categories;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowItem = inflater.inflate(drawer_list_item, parent, false);

        final ITimeCategory timeCategory = getItem(position);
        final TextView etNameView = (TextView) rowItem.findViewById(R.id.nameView);

        if(timeCategory.getName() != null) {
            etNameView.setText(timeCategory.getName());

        }
        return rowItem;

    }
}

