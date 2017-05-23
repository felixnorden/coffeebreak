package cbstudios.coffeebreak.view.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

public interface ITaskEditView extends IView {
    String getNameText();

    void setNameText(String text);

    void setNotification(Calendar cal);

    Calendar getNotification();

    void setTitle(String title);

    String getNewLabelText();
    /**
     * Setups the categories with a custom-adapter.
     *
     * @param list List of ILabelCategories from task
     */
    void setupCategories(List<ILabelCategory> list);

    void notifyCategoriesChanged();
}
