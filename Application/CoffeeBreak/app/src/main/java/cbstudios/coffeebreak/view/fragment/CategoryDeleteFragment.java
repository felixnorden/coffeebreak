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
 * Created by elias on 26/05/2017.
 */

public class CategoryDeleteFragment extends DialogFragment {

    int position;
    public void setPosition(int position){
        this.position = position;
    }

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

