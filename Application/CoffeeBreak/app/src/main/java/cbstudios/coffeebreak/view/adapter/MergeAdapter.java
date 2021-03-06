package cbstudios.coffeebreak.view.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elias, Felix
 * @version 2.0
 *          <p>Responsibility: Adapter that merges multiple adapters and views into a single
 *          adapter, allows usage of multiple adapters in e.g a ListVIew.</br >
 *          Uses: {@link ListAdapter}</br>
 *          Used by: {@link cbstudios.coffeebreak.view.activity.MainActivity}
 *          </p>
 */

public class MergeAdapter extends BaseAdapter implements SectionIndexer {
    protected List<ListAdapter> pieces = new ArrayList<>();
    protected String noItemsText;

    /**
     * Default constructor.
     */
    public MergeAdapter() {
        super();
    }

    /**
     * Adds a new adapter to the roster of things to appear in the aggregate
     * list.
     *
     * @param adapter Source for row views for this section
     */
    public void addAdapter(ListAdapter adapter) {
        pieces.add(adapter);
        adapter.registerDataSetObserver(new CascadeDataSetObserver());
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want
     */
    public Object getItem(int position) {
        for (ListAdapter piece : pieces) {
            int size = piece.getCount();

            if (position < size) {
                return (piece.getItem(position));
            }

            position -= size;
        }

        return (null);
    }

    /**
     * Get the adapter associated with the specified position in the data set.
     *
     * @param position Position of the item whose adapter we want
     * @return The adapter of the item in the position.
     */
    public ListAdapter getAdapter(int position) {
        for (ListAdapter piece : pieces) {
            int size = piece.getCount();

            if (position < size) {
                return (piece);
            }

            position -= size;
        }

        return (null);
    }

    /**
     * Get count of items in the data set represented by this Adapter.
     */
    public int getCount() {
        int total = 0;

        for (ListAdapter piece : pieces) {
            total += piece.getCount();
        }

        if (total == 0 && noItemsText != null) {
            total = 1;
        }

        return (total);
    }

    /**
     * Returns the number of types of Views that will be created by getView().
     */
    @Override
    public int getViewTypeCount() {
        int total = 0;

        for (ListAdapter piece : pieces) {
            total += piece.getViewTypeCount();
        }

        return (Math.max(total, 1)); // needed for setListAdapter() before
        // content add'
    }

    /**
     * Get the type of View that will be created by getView() for the specified
     * item.
     *
     * @param position Position of the item whose data we want
     */
    @Override
    public int getItemViewType(int position) {
        int typeOffset = 0;
        int result = -1;

        for (ListAdapter piece : pieces) {
            int size = piece.getCount();

            if (position < size) {
                result = typeOffset + piece.getItemViewType(position);
                break;
            }

            position -= size;
            typeOffset += piece.getViewTypeCount();
        }

        return (result);
    }

    /**
     * If true it means all items are
     * selectable and clickable.
     */
    @Override
    public boolean areAllItemsEnabled() {
        return (false);
    }

    /**
     * Returns true if the item at the specified position is not a separator.
     *
     * @param position Position of the item whose data we want
     */
    @Override
    public boolean isEnabled(int position) {
        for (ListAdapter piece : pieces) {
            int size = piece.getCount();

            if (position < size) {
                return (piece.isEnabled(position));
            }

            position -= size;
        }

        return (false);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        for (ListAdapter piece : pieces) {
            int size = piece.getCount();

            if (position < size) {

                return (piece.getView(position, convertView, parent));
            }

            position -= size;
        }

        if (noItemsText != null) {
            TextView text = new TextView(parent.getContext());
            text.setText(noItemsText);
            return text;
        }

        return (null);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position Position of the item whose data we want
     */
    public long getItemId(int position) {
        for (ListAdapter piece : pieces) {
            int size = piece.getCount();

            if (position < size) {
                return (piece.getItemId(position));
            }

            position -= size;
        }

        return (-1);
    }

    public int getPositionForSection(int section) {
        int position = 0;

        for (ListAdapter piece : pieces) {
            if (piece instanceof SectionIndexer) {
                Object[] sections = ((SectionIndexer) piece).getSections();
                int numSections = 0;

                if (sections != null) {
                    numSections = sections.length;
                }

                if (section < numSections) {
                    return (position + ((SectionIndexer) piece)
                            .getPositionForSection(section));
                } else if (sections != null) {
                    section -= numSections;
                }
            }

            position += piece.getCount();
        }

        return (0);
    }

    public int getSectionForPosition(int position) {
        int section = 0;

        for (ListAdapter piece : pieces) {
            int size = piece.getCount();

            if (position < size) {
                if (piece instanceof SectionIndexer) {
                    return (section + ((SectionIndexer) piece)
                            .getSectionForPosition(position));
                }

                return (0);
            } else {
                if (piece instanceof SectionIndexer) {
                    Object[] sections = ((SectionIndexer) piece).getSections();

                    if (sections != null) {
                        section += sections.length;
                    }
                }
            }

            position -= size;
        }

        return (0);
    }

    public Object[] getSections() {
        ArrayList<Object> sections = new ArrayList<Object>();

        for (ListAdapter piece : pieces) {
            if (piece instanceof SectionIndexer) {
                Object[] curSections = ((SectionIndexer) piece).getSections();

                if (curSections != null) {
                    for (Object section : curSections) {
                        sections.add(section);
                    }
                }
            }
        }

        if (sections.size() == 0) {
            return (null);
        }

        return (sections.toArray(new Object[0]));
    }

    /**
     * Elias skrev denna, vid fråga av vad den gör gavs svaret: "Jag vet inte, men det funka inte
     * utan den".
     */
    private class CascadeDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            notifyDataSetChanged();
        }

        @Override
        public void onInvalidated() {
            notifyDataSetInvalidated();
        }
    }
}
