package cbstudios.coffeebreak.view.activity;

import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.view.adapter.IAchievementAdapter;

/**
 * Created by johan on 5/25/2017.
 */

public interface IAchievementView extends IView {
    void setCategories(List<ILabelCategory> labelCategories, List<ITimeCategory> timeCategories);

    void setAchievementAdapter(IAchievementAdapter adapter);

    void setNavDrawer();

    void setToolbar();

}
