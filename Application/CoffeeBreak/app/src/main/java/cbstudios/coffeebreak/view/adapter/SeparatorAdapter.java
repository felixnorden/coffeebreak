package cbstudios.coffeebreak.view.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import cbstudios.coffeebreak.R;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Create separators to include into MergeAdapters in lists</br >
 *          Uses: separator_layout, {@link Context}</br>
 *          Used by: {@link MergeAdapter}
 *          </p>
 */

public class SeparatorAdapter extends BaseAdapter {
    public static final int TIME_CATEGORY = 0;
    public static final int LABEL_CATEGORY = 1;

    private Context context;
    private final int type;

    /**
     * @param context The context in which this is used in.
     * @param type Type of category to show.
     */
    public SeparatorAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View separator = inflater.inflate(R.layout.separator_layout, parent, false);

        AppCompatImageView iwIcon = (AppCompatImageView) separator.findViewById(R.id.separator_image);

        switch (type) {
            case TIME_CATEGORY:
                iwIcon.setImageResource(R.drawable.ic_alarm_white_24dp);
                break;
            case LABEL_CATEGORY:
                iwIcon.setImageResource(R.drawable.ic_label_outline_black_24dp);
                break;
        }
        ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{R.color.colorPrimaryDark});
        iwIcon.setImageTintList(csl);
        separator.setClickable(false);
        separator.setFocusable(false);

        return separator;
    }
}
