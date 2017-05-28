package cbstudios.coffeebreak.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import org.greenrobot.eventbus.EventBus;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.eventbus.CategoryDeletedEvent;
import cbstudios.coffeebreak.eventbus.SortListEvent;
import cbstudios.coffeebreak.eventbus.TaskDeletedEvent;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

/**
 * @author Felix, Elias
 * @version 2.0
 *          <p>Responsibility: Display deletion option for a selected list item</br >
 *          Used by: {@link android.support.v7.widget.RecyclerView.ViewHolder and {@link android.widget.ListView}}
 *          when binding onLongClick
 *          </p>
 */

public class DeleteFragment extends DialogFragment {
    int position;
    int type;

    public static final int CATEGORY_TYPE = 0;
    public static final int TASK_TYPE = 1;

    /**
     * Set the type of DeleteFragment to create
     * @param type
     */
    public void setType(int type) {this.type = type; }

    /**
     * Set the position of the category which to delete.
     *
     * @param position Position of the category in the list. 0 is top category.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        switch (type){
            case CATEGORY_TYPE: buildCategoryDialog(builder);
                break;
            case TASK_TYPE: buildTaskDialog(builder);
                break;
            default: break;
        }
        return builder.create();
    }

    private void buildCategoryDialog(AlertDialog.Builder builder){
        builder.setTitle(R.string.delete_category)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EventBus.getDefault().post(new CategoryDeletedEvent(which, position));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EventBus.getDefault().post(new CategoryDeletedEvent(which, position));
                    }
                });

    }
    private void buildTaskDialog(AlertDialog.Builder builder){
        builder.setTitle(R.string.delete_task)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EventBus.getDefault().post(new TaskDeletedEvent(which, position));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EventBus.getDefault().post(new TaskDeletedEvent(which, position));
                    }
                });

    }
}

