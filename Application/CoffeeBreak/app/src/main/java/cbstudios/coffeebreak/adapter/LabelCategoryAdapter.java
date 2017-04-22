package cbstudios.coffeebreak.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategoryList;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

/**
 * Created by elias on 20/04/2017.
 */

public class LabelCategoryAdapter extends ArrayAdapter<ILabelCategory> {

    private final Context context;
    private final List labelCategories;

    public LabelCategoryAdapter(Context context, List<ILabelCategory> categories){
        super(context, R.layout.drawer_list_item, categories);
        this.context = context;
        this.labelCategories = categories;
}

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowItem = inflater.inflate(R.layout.drawer_list_item, parent, false);

        final ILabelCategory labelCategory = getItem(position);
        final TextView etNameView = (TextView) rowItem.findViewById(R.id.nameView);

        rowItem.setBackgroundColor(labelCategory.getColor());

        if(labelCategory.getName() != null) {

            etNameView.setText(labelCategory.getName());

        }

        return rowItem;
    }
}
