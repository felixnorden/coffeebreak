package cbstudios.coffeebreak.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
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
        super(context, R.layout.drawer_list_item, categories);
        this.context = context;
        this.labelCategories = categories;
        this.model = model;
}

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowItem = inflater.inflate(R.layout.drawer_list_item, parent, false);

        final ILabelCategory labelCategory = getItem(position);
        final TextView etNameView = (TextView) rowItem.findViewById(R.id.nameView);
        ImageView ivCategory = (ImageView) rowItem.findViewById(R.id.imageViewCategoryColor);
        TextView categorySize = (TextView) rowItem.findViewById(R.id.textViewNumber);




        if(labelCategory.getName() != null) {

            etNameView.setText(labelCategory.getName());
            ivCategory.setColorFilter(Color.parseColor(labelCategory.getColor()));
            List<IAdvancedTask> tasks = new ArrayList<>();
            tasks = model.getToDoDataModule().getTasks();
            int workNum = 0;
            int homeNum = 0;
            int meetingsNum = 0;
            for (IAdvancedTask task : tasks){
                for (int i = 0; i< task.getLabels().size(); i++) {
                    if (task.getLabels().get(i).getName().equals("Work")) {
                        workNum++;
                    }
                    if (task.getLabels().get(i).getName().equals("Home")) {
                        homeNum++;
                    }
                    if (task.getLabels().get(i).getName().equals("Meetings")) {
                        meetingsNum++;
                    }
                }
            }
            if (labelCategory.getName().equals("Work")) {
                categorySize.setText(Integer.toString(workNum));
            } else if (labelCategory.getName().equals("Home")) {
                categorySize.setText(Integer.toString(homeNum));
            } else if (labelCategory.getName().equals("Meetings")) {
                categorySize.setText(Integer.toString(meetingsNum));
            }

            //categorySize.setText(Integer.toString(labelCategory.getValidTasks(model.getToDoDataModule().getTasks()).size()));
            //String nnn = Integer.toString(labelCategory.getValidTasks(model.getToDoDataModule().getTasks()).size());
            //int nn = labelCategory.getValidTasks(model.getToDoDataModule().getTasks()).size();
            //List<IAdvancedTask> n = new ArrayList<>();
            //n =  (model.getToDoDataModule().getTasks());
            //int nn = labelCategory.getValidTasks(n).size();
        }

        return rowItem;
    }

}
