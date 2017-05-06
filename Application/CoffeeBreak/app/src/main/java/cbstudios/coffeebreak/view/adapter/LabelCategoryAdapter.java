package cbstudios.coffeebreak.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * Created by elias on 20/04/2017.
 */

public class LabelCategoryAdapter extends ArrayAdapter<ILabelCategory> {

    private final Context context;
    private final List labelCategories;
    public Model model;


    public LabelCategoryAdapter(Context context, List<ILabelCategory> categories, Model model){
        super(context, R.layout.drawer_list_item_label, categories);
        this.context = context;
        this.labelCategories = categories;
        this.model = model;
}

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowItem = inflater.inflate(R.layout.drawer_list_item_label, parent, false);

        final ILabelCategory labelCategory = getItem(position);
        final TextView etNameView = (TextView) rowItem.findViewById(R.id.nameView);
        ImageView ivCategory = (ImageView) rowItem.findViewById(R.id.imageViewCategoryColor);
        TextView categorySize = (TextView) rowItem.findViewById(R.id.textViewNumber);




        if(labelCategory.getName() != null) {

            etNameView.setText(labelCategory.getName());
            ivCategory.setColorFilter(Color.parseColor(labelCategory.getColor()), PorterDuff.Mode.MULTIPLY);


            List<IAdvancedTask> tasks = model.getToDoDataModule().getTasks();

            for (int j = 0; j < labelCategories.size(); j++) {
                if (labelCategory.equals(labelCategories.get(j))) {
                    if (labelCategory.getTaskCount(tasks)!= 0) {
                        categorySize.setText(Integer.toString(labelCategory.getTaskCount(tasks)));
                    }
                }
            }
        }

        return rowItem;
    }

}
