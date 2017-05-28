package cbstudios.coffeebreak.view.activity;

import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.view.adapter.AchievementAdapter;

/**
 * @author Johan
 * @version 1.0
 *          <p> Abstraction interface of the AchievementActivity </br >
 *          Used by: AchievementActivity, AchievementPresenter, DelegatingPresenter, PresenterFactory
 *          </p>
 */

public interface IAchievementView extends IView {

    void setAchievementAdapter(AchievementAdapter adapter);

    void setToolbar();

}
