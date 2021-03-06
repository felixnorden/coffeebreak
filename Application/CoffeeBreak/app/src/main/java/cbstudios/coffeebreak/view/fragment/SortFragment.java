package cbstudios.coffeebreak.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import org.greenrobot.eventbus.EventBus;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.eventbus.SortListEvent;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Show a dialog of sorting options when sorting the list of tasks</br >
 *          Uses: sorting_array</br>
 *          Used by: {@link cbstudios.coffeebreak.view.activity.MainActivity}
 *          </p>
 */

public class SortFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.sorting_order).setItems(R.array.sorting_array, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                EventBus.getDefault().post(new SortListEvent(which));
            }
        });
        return builder.create();
    }
}
