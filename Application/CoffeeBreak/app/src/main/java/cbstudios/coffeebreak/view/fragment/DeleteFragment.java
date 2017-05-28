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
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

/**
 * @author Felix, Elias
 * @version 2.0
 *          <p>Responsibility: Display deletion option for a selected list item</br >
 *          Used by: {@link android.support.v7.widget.RecyclerView.ViewHolder and {@link android.widget.ListView}}
 *          when binding onLongClick
 *          </p>
 *
 */

public class DeleteFragment extends DialogFragment {

    int position;
    public void setPosition(int position){
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
        return builder.create();
    }
}

