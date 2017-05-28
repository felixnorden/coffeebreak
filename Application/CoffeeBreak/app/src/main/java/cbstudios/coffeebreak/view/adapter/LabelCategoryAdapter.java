package cbstudios.coffeebreak.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.AppCompatEditText;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cbstudios.coffeebreak.R;

import cbstudios.coffeebreak.eventbus.ShowKeyboardEvent;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

/**
 * @author Elias, Felix
 * @version 2.0
 *          <p>Responsibility: Handle all {@link ILabelCategory} instances
 *          and allow for sorting of tasks into the specific category</br >
 *          Uses: {@link Context}, {@link ILabelCategory}</br>
 *          Used by: {@link MergeAdapter}
 *          </p>
 *
 */

public class LabelCategoryAdapter extends ArrayAdapter<ILabelCategory> {

    private final Context context;
    private final List<ILabelCategory> labelCategories;


    public LabelCategoryAdapter(Context context, List<ILabelCategory> categories) {
        super(context, R.layout.drawer_list_item_label, categories);
        this.context = context;
        this.labelCategories = categories;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final View rowItem = inflater.inflate(R.layout.drawer_list_item_label, parent, false);


        final ILabelCategory labelCategory = getItem(position);
        final ViewSwitcher switcher = (ViewSwitcher) rowItem.findViewById(R.id.view_switcher);
        final AppCompatEditText etNameView = (AppCompatEditText) rowItem.findViewById(R.id.name_view_edit);
        final TextView tvNameView = (TextView) rowItem.findViewById(R.id.name_view_uneditable);
        if (position != 0) {
            //LinearLayout llSeparator = (LinearLayout) rowItem.findViewById(R.id.separator);
            //llSeparator.setVisibility(View.INVISIBLE);
            final float scale = getContext().getResources().getDisplayMetrics().density;
            int px = (int) (48 * scale + 0.5f);
            rowItem.getLayoutParams().height = px;
        }
        final ImageView ivCategory = (ImageView) rowItem.findViewById(R.id.imageViewCategoryColor);
        //TextView categorySize = (TextView) rowItem.findViewById(R.id.textViewNumber);



        if (!labelCategory.getName().equalsIgnoreCase("")) {
            tvNameView.setText(labelCategory.getName());
            ivCategory.setColorFilter(Color.parseColor(labelCategory.getColor()), PorterDuff.Mode.MULTIPLY);
            switcher.showNext();



            // updateNumberOfTaskInCategory(labelCategory, categorySize);
        }else{
            EventBus.getDefault().post(new ShowKeyboardEvent(true, etNameView));
            etNameView.requestFocus();
            etNameView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() != KeyEvent.ACTION_DOWN) {
                        String input = etNameView.getText().toString();
                        // Check if input is empty, if so, remove task from database
                        // and update adapter of removal
                        if (input.equalsIgnoreCase("") || input.equalsIgnoreCase(null)) {
                            EventBus.getDefault().post(new ShowKeyboardEvent(false, etNameView));
                            labelCategories.remove(labelCategory);
                            return false;
                        }
                        EventBus.getDefault().post(new ShowKeyboardEvent(false, etNameView));
                        labelCategory.setName(input);
                        tvNameView.setText(labelCategory.getName());
                        ivCategory.setColorFilter(Color.parseColor(labelCategory.getColor()), PorterDuff.Mode.MULTIPLY);
                        switcher.showNext();

                    }
                    return false;
                }
            });
        }

        return rowItem;
    }
}
